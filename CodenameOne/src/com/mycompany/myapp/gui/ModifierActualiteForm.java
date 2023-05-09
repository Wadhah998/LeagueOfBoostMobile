package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Actualite;
import com.mycompany.myapp.gui.ListActualiteForm;
import com.mycompany.myapp.services.ServiceActualite;

public class ModifierActualiteForm extends Form {
    private Form previous;
    private Actualite actualite;
     private TextField tfTitre;

    private TextField tfDescription;
 

    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierActualiteForm(Form previous, Actualite actualite) {
        super("Modifier actualite", BoxLayout.y());
        this.previous = previous;
        this.actualite = actualite;
        
        tfTitre = new TextField(actualite.getTitre(), "Titre");
        tfDescription = new TextField(actualite.getDescription(), "Description");
       
           

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            actualite.setTitre(tfTitre.getText());
            actualite.setDescription(tfDescription.getText());
           

            if (ServiceActualite.getInstance().modifierActualite (actualite)) {
                Dialog.show("actualite modifiée", "Votre actualite a été modifiée avec succès", "OK", null);
                new ListActualiteForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListActualiteForm(previous).show());
        addAll(tfTitre,tfDescription,  btnModifier, btnAnnuler);

    }

    public void show() {
        super.show();
    }
}
