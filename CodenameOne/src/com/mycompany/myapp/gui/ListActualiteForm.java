/*
 * Pour modifier cet en-tête de licence, choisissez License Headers dans les propriétés du projet.
 * Pour modifier ce fichier modèle, choisissez Outils | Modèles
 * et ouvrez le modèle dans l'éditeur.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entites.Actualite;
import com.mycompany.myapp.services.ServiceActualite;
import java.util.ArrayList;

/**
 *
 * @author imtinen
 */
public class ListActualiteForm extends Form {

    private Form previous;

    public ListActualiteForm(Form previous) {
        this.previous = previous;
        setTitle("Liste des Actualites");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Actualite> actualites = ServiceActualite.getInstance().getAllActualites();

        for (Actualite actualite : actualites) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur
            Label TitreLabel = new Label("Titre: " + actualite.getTitre());
            Label DescriptionLabel = new Label("Description: " + actualite.getDescription());
            Button buttonSupprimer = new Button("Supprimer");
            buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
            buttonSupprimer.addActionListener(e -> {
                if (Dialog.show("Confirmation", "Voulez-vous supprimer cette actualite ?", "Oui", "Non")) {
                    if (ServiceActualite.getInstance().deleteActualite(actualite.getId())) {
                        container.remove();
                        refreshTheme();
                    } else {
                        Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'actualite", "Ok", null);
                    }
                }
            });

            Button buttonModifier = new Button("Modifier");
            buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
            buttonModifier.addActionListener(e -> new ModifierActualiteForm(this, actualite).show());

            container.getStyle().setPadding(10, 10, 10, 10);
            container.getStyle().setBorder(Border.createLineBorder(2));
            container.getStyle().setBgTransparency(255);
            container.getStyle().setBgColor(0xffffff);
            container.add(TitreLabel);
            container.add(DescriptionLabel);
            container.add(buttonSupprimer);
            container.add(buttonModifier);

            add(container);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Accueil", null, ev -> new AddActualite(this).show());
    }
}
