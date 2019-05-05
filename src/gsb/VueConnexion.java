/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb;


import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;
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
 * @author etudiant
 */
public class VueConnexion extends Dialog<Pair<String,String>>{
    public VueConnexion() {
        this.setTitle("Authentification");
        this.setHeaderText("Entrez vos identifiants de connexion.");
        
        Label lblMat = new Label("Matricule : ");
        Label lblMdp = new Label("Mot de passe : ");
        
        TextField txtMat = new TextField();
        PasswordField txtMdp = new PasswordField();
        
        
        
        VBox vb = new VBox();
        
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        
        hb1.getChildren().addAll(lblMat,txtMat);
        hb2.getChildren().addAll(lblMdp,txtMdp);
        
        
        
        vb.getChildren().addAll(hb1,hb2);
        
        
        getDialogPane().setContent(vb);
        
        ButtonType btnValider = new ButtonType("Valider",OK_DONE);
        ButtonType btnAnnuler = new ButtonType("Annuler",CANCEL_CLOSE);
        
        
        getDialogPane().getButtonTypes().addAll(btnAnnuler,btnValider);
        
  
        setResultConverter(
        new Callback<ButtonType, Pair<String,String>>() {
        @Override
            public Pair<String,String> call(ButtonType typeBouton) {
                if (typeBouton == btnValider) {

                    return new Pair<String,String>(txtMat.getText(),txtMdp.getText());
                  
                }
                return null;
            }


        }
        );
        

        
    }
    
}
