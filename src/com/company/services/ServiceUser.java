/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;


import com.codename1.io.ConnectionRequest;

import com.codename1.io.NetworkManager;

import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.company.utils.statics;
import com.codename1.io.CharArrayReader;
import java.util.Map;
import com.mycompany.myapp.SessionManager;
import com.codename1.io.JSONParser;
import com.codename1.uikit.cleanmodern.NewsfeedForm;
import com.codename1.uikit.cleanmodern.SignInForm;


/**
 *
 * @author Lenovo
 */
public class ServiceUser {
    
    
  //singleton 
    public static ServiceUser instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUser getInstance() {
        if(instance == null )
            instance = new ServiceUser();
        return instance ;
    }
    
    
    
    public ServiceUser() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
  // Signup
public void signup(TextField firstname,TextField lastname ,TextField username, TextField plainPassword, TextField email, Resources res) {
    String url = statics.BASE_URL + "registerJson?username=" + username.getText() + "&email=" + email.getText()
            + "&plainPassword=" + plainPassword.getText()+ "&firstname="+firstname.getText()+"&lastname="+ lastname.getText()+ "&agreeTerms="+ 1 ;
    req.setUrl(url);

    
    if (username.getText().equals("") || plainPassword.getText().equals("") || email.getText().equals("")) {
        Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);
        return;
    }

    
    req.addResponseListener((e) -> {
        
        byte[] data = (byte[]) e.getMetaData(); 
        String responseData = new String(data); 
        System.out.println("data ===>" + responseData);
        if (responseData.equals("Account is created")) {
            // Registration succeeded, show the sign in form
            new SignInForm(res).show();}
    });

    
    NetworkManager.getInstance().addToQueueAndWait(req);
}

   public void signin(TextField username,TextField password, Resources rs ) {
        
        
        String url = statics.BASE_URL+"loginJson?username="+username.getText()+"&password="+password.getText();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                boolean success = Boolean.parseBoolean(user.get("success").toString());
                 String message = user.get("message").toString();
                if (success && message.equals("User authenticated")) {
        // Afficher la nouvelle page
                 NewsfeedForm newsForm = new NewsfeedForm(rs);
                  newsForm.show();}
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                
                
                    
                
                //photo 
                
                              
                
      
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}

// SignIn
  