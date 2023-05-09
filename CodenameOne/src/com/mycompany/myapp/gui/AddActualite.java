package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Actualite;
import com.mycompany.myapp.services.ServiceActualite;

public class AddActualite extends Form {
   private Form previous;

    public AddActualite(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new product");

        TextField tfDescription = new TextField("", "description");
        TextField tfTitre = new TextField("", "Titre"); 
        Button addActualiteButton = new Button("Add");

        addActualiteButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfDescription.getText().isEmpty() ||  tfTitre.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Actualite actualite = new Actualite(tfDescription.getText(),tfTitre.getText());
                        if (ServiceActualite.getInstance().addActualite(actualite)) {
                            Dialog.show("Success", "Actuality added", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfDescription, tfTitre, addActualiteButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }
}
