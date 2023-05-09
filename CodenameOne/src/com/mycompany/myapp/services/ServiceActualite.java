package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.Actualite;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceActualite {
    private static ServiceActualite instance;
    private ConnectionRequest req;

    private ServiceActualite() {
        req = new ConnectionRequest();
    }

    public static ServiceActualite getInstance() {
        if (instance == null) {
            instance = new ServiceActualite();
        }
        return instance;
    }

    public boolean addActualite(Actualite l) {
        String url = Statics.BASE_URL + "addj?titre=" + l.getTitre() +   "&description=" + l.getDescription() ;
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }

public ArrayList<Actualite> parseActualite(String jsonText) throws ParseException {
    if (jsonText == null || jsonText.isEmpty()) {
        return new ArrayList<>();
    }
    System.out.println("Début parsing");
    ArrayList<Actualite> actualites = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> actualitesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) actualitesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Actualite p = new Actualite();
            if (obj.containsKey("id")) {
                int id = (int) Double.parseDouble(obj.get("id").toString());
                p.setId(id);
            }
              if (obj.containsKey("Titre")) {
                p.setTitre(obj.get("Titre").toString());
            }
            if (obj.containsKey("Description")) {
                p.setDescription(obj.get("Description").toString());
            }
        

            actualites.add(p);
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return actualites;
}


    public ArrayList<Actualite> getAllActualites() {
        String url = Statics.BASE_URL + "Afficherj";
        System.out.println(url);
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        String response = new String(req.getResponseData());
        try {
            return parseActualite(response);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
    public boolean deleteActualite(int id) {
        String url = Statics.BASE_URL + "Supprimeract/" + id;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueueAndWait(request);
        return request.getResponseCode() == 200;
    }

    public boolean modifierActualite(Actualite l) {

     
String url = Statics.BASE_URL + "updateeact/" + l.getId() + "?titre=" + l.getTitre()+ "?&description=" + l.getDescription() ;
       // req.setUrl(url);
    req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            private boolean resultOK;
            

            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
}