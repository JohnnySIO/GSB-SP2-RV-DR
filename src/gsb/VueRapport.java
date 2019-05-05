/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb;

import fr.gsb.rv.dr.entites.RapportVisite;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author root
 */

import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;

public class VueRapport extends Dialog<Pair<String,String>>{
    public VueRapport(RapportVisite rv) {
        
        
        
        
        LocalDate dateVis = rv.getDateVisite();
        LocalDate dateReda = rv.getDateRedaction();

        
        String dateVisFr = dateVis.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String dateRedaFr = dateReda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        this.setTitle("Rapport "+rv.getNumero());
        this.setHeaderText("Rédigé par "+rv.getLeVisiteur().getMatricule()+"\n le "+dateRedaFr);
        
        Label lblPra = new Label("Praticien : "+rv.getLePraticien().getNom());
        Label lblDateVis = new Label("Visité le : "+dateVisFr);
        Label lblBilan = new Label("Bilan : "+rv.getBilan());
        Label lblMotif = new Label("Motif : "+rv.getMotif());
        Label lblConf = new Label("Coefficient de confiance : "+rv.getCoefConfiance());
        
        
        

        VBox vb = new VBox();
        
        
        vb.getChildren().addAll(lblPra,lblDateVis,lblBilan,lblMotif,lblConf);
        
        getDialogPane().setContent(vb);

        ButtonType btnLu = new ButtonType("Lu");
        
        
        getDialogPane().getButtonTypes().addAll(btnLu);
        
  
        setResultConverter(
        new Callback<ButtonType, Pair<String,String>>() {
        @Override
            public Pair<String,String> call(ButtonType typeBouton) {
                if (typeBouton == btnLu) {

                    return null;

                    
                }

               
                return null;
            }


        }
        );
        

        
    }
    
}
