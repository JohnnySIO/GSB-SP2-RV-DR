/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.Connexion;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.swing.JOptionPane;

/**
 *
 * @author etudiant
 */
public class GSB extends Application {
    PanneauPraticiens vuePraticiens = new PanneauPraticiens();
    PanneauRapports vueRapports = new PanneauRapports();
    
    @Override
    public void start(Stage primaryStage) {
        
        
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 500);
        
        primaryStage.setTitle("GSB-RV-DR");
        
        primaryStage.setResizable(false);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        PanneauAccueil vueAccueil = new PanneauAccueil();
        
        
        StackPane stack = new StackPane();
        
        
        stack.getChildren().addAll(vueAccueil,vuePraticiens,vueRapports);
        
        vueAccueil.toFront();
        
        
        
        root.setCenter(stack);
       
       
        
        

        
        
       
   
        
 
       
        
        
        
        
        
        MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        
        MenuItem itemSeConnecter = new MenuItem("Se connecter");
        
        
        
        
        
        MenuItem itemSeDeconnecter = new MenuItem("Se déconnecter");
        itemSeDeconnecter.setDisable(true);
        
        SeparatorMenuItem separatorFichier = new SeparatorMenuItem();
        MenuItem itemQuitter = new MenuItem("Quitter");
        
        menuFichier.getItems().add(itemSeConnecter);
        
             
        menuFichier.getItems().add(itemSeDeconnecter);
        menuFichier.getItems().add(separatorFichier);
        menuFichier.getItems().add(itemQuitter);
        itemQuitter.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle( ActionEvent event ) {
                    Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                    alertQuitter.setTitle("Quitter");
                    alertQuitter.setHeaderText("Demande de confirmation");
                    alertQuitter.setContentText("Voulez-vous quitter l'application ?");
                    ButtonType btnOui = new ButtonType("Oui");
               
           

                    ButtonType btnNon = new ButtonType("Non");
          
    

                    alertQuitter.getButtonTypes().setAll(btnOui,btnNon);
                    Optional<ButtonType> reponse = alertQuitter.showAndWait();
                    if(reponse.get() == btnOui){
                        Platform.exit();
                    }
                    else {
                        alertQuitter.close();
                    };
                    
                }
            }
        );
        itemQuitter.setAccelerator(new KeyCodeCombination(KeyCode.X,KeyCombination.CONTROL_DOWN));
        
        
        barreMenus.getMenus().add(menuFichier);
        
        
        
        
        Menu menuRapports = new Menu("Rapports");
         menuRapports.setDisable(true);
        
        MenuItem itemConsulter = new MenuItem("Consulter");
        
        menuRapports.getItems().add(itemConsulter);
        barreMenus.getMenus().add(menuRapports);
        
        
        
         Menu menuPraticiens = new Menu("Praticiens");
         menuPraticiens.setDisable(true);
        MenuItem itemHesitant = new MenuItem("Hésitants");
        
        menuPraticiens.getItems().add(itemHesitant);
        barreMenus.getMenus().add(menuPraticiens);
        
        
        itemSeConnecter.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle( ActionEvent event ) {
                    itemSeConnecter.setDisable(true);
                    menuRapports.setDisable(true);
        
   
        
                }
            }
        );
        
        
        root.setTop(barreMenus);
        
        itemSeConnecter.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle( ActionEvent event ) {
                   
                    
//                    try {
   
//                       Connection conn= (Connection) ConnexionBD.getConnexion();
//                       Statement stmt =(Statement) conn.createStatement();
//                        ResultSet rst=stmt.executeQuery("select v.vis_matricule\n" +
//                        "from Visiteur v inner join Travailler t\n" +
//                        "where v.vis_matricule = t.vis_matricule\n" +
//                       "and t.tra_role = 'Délégué';");
                        
//                       while(rst.next()) {
//                            System.out.println(rst.getString("vis_matricule"));
//                        }
//                    }
//                   catch(Exception e){
//                       JOptionPane.showMessageDialog(null, "Erreur de connexion");
//                    }


//                    try {
//                        Visiteur vis = ModeleGsbRv.seConnecter("c14", "azerty");
//                        System.out.println(vis.toString());
//                    } catch (ConnexionException ex) {
//                        JOptionPane.showMessageDialog(null, "Erreur de connexion");
//                   }
                    
                    
                    
                    try {    
                        VueConnexion vue = new VueConnexion();
                        Optional<Pair<String,String>> reponse = vue.showAndWait();
                        if(reponse.isPresent()){
                            Visiteur vis = ModeleGsbRv.seConnecter(reponse.get().getKey(),reponse.get().getValue() );
                            if(vis != null){
                               
                               Session.ouvrir(vis);
                               

                               primaryStage.setTitle(vis.getNom()+" - Appli-RV");
                               
                               menuRapports.setDisable(false);
                               menuPraticiens.setDisable(false);
                               itemSeConnecter.setDisable(true);
                               itemSeDeconnecter.setDisable(false);
                           }
                           else {
                            JOptionPane.showMessageDialog(null, "Erreur de connexion");
                        }
                           
                            
                        } 
                        
                   }
                    catch (ConnexionException ex) {
                        
                    }
                    

                    
                }
            }
        );
        
        itemSeDeconnecter.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle( ActionEvent event ) {
                    itemSeConnecter.setDisable(false);
                    itemSeDeconnecter.setDisable(true);
                    menuRapports.setDisable(true);
                    menuPraticiens.setDisable(true);

                    
                    Session.fermer();
                    primaryStage.setTitle("Appli-RV");
                    vuePraticiens = new PanneauPraticiens();
                    vueRapports = new PanneauRapports();
                    
                    stack.getChildren().addAll(vuePraticiens,vueRapports);
                    vueAccueil.toFront();
   
        
                }
            }
        );
        

        itemConsulter.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle( ActionEvent event ) {
                    vueRapports.toFront();
   
        
                }
            }
        );

        itemHesitant.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle( ActionEvent event ) {
                    vuePraticiens.toFront();
                    
                    
   
        
                }
            }
        );
        

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
        
        
        
        
        
    }
}
 
