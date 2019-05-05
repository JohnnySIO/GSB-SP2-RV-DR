/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.Mois;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author etudiant
 */
public class PanneauRapports extends Pane{
    private ComboBox<Visiteur> cbVisiteurs = new ComboBox<Visiteur>();
    private ComboBox<Mois> cbMois = new ComboBox<Mois>();
    private ComboBox<Integer> cbAnnee = new ComboBox<Integer>();
    
    private TableView tabRapports = new TableView();
    
    public PanneauRapports() {

        this.setStyle("-fx-alignment: center; -fx-background-color: white;");
    
        VBox vb = new VBox();
        
        
        
        GridPane grid = new GridPane();
        
        grid.getChildren().addAll(cbVisiteurs,cbMois,cbAnnee);
        
        grid.setColumnIndex(cbVisiteurs, 0);
        grid.setColumnIndex(cbMois, 1);
        grid.setColumnIndex(cbAnnee, 2);
        
        vb.getChildren().add(grid);
        
        
        
        
        
        
        
        
        
        Button btnValider = new Button("Valider");
        
        vb.getChildren().add(btnValider);
        
        btnValider.setOnAction(( ActionEvent event )->{
            this.rafraichir();
        }
        );
        
        
        
        
        TableColumn <RapportVisite,Integer> colNumero = new TableColumn<RapportVisite, Integer>("Numéro");
        
        TableColumn <RapportVisite,String> colNom = new TableColumn <RapportVisite,String>("Praticien");
        TableColumn <RapportVisite,String> colVille = new TableColumn <RapportVisite,String>("Ville");
        
        
        TableColumn <RapportVisite,LocalDate> colVisite = new TableColumn<RapportVisite, LocalDate>("Visite");
        TableColumn <RapportVisite,LocalDate> colReda = new TableColumn<RapportVisite, LocalDate>("Rédaction");
        
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        

        
        colNom.setCellValueFactory(
            new Callback<CellDataFeatures<RapportVisite,String>,ObservableValue<String>>(){
                
                @Override
                public ObservableValue<String> call(CellDataFeatures<RapportVisite,String> param){
                    String nom = param.getValue().getLePraticien().getNom();
                    return new SimpleStringProperty(nom);
                }
            }
        ); 
        
        
        colVille.setCellValueFactory(
            new Callback<CellDataFeatures<RapportVisite,String>,ObservableValue<String>>(){
                
                @Override
                public ObservableValue<String> call(CellDataFeatures<RapportVisite,String> param){
                    String ville = param.getValue().getLePraticien().getVille();
                    return new SimpleStringProperty(ville);
                }
            }
        ); 
        
        colVisite.setCellValueFactory(new PropertyValueFactory<>("dateVisite"));
        colVisite.setCellFactory(
                
                colonne -> {
                    return new TableCell<RapportVisite,LocalDate>(){
                        @Override
                        protected void updateItem(LocalDate item, boolean empty){
                            super.updateItem(item,empty);
                            
                            if(empty){
                                setText("");
                            }
                            
                            else{
                                DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                setText(item.format(formateur));
                            }
                            
                        }
                    };
                }
                
        );
        
        
        
        
        
        
        
        colReda.setCellValueFactory(new PropertyValueFactory<>("dateRedaction"));
        colReda.setCellFactory(
                
                colonne -> {
                    return new TableCell<RapportVisite,LocalDate>(){
                        @Override
                        protected void updateItem(LocalDate item, boolean empty){
                            super.updateItem(item,empty);
                            
                            if(empty){
                                setText("");
                            }
                            
                            else{
                                DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                setText(item.format(formateur));
                            }
                            
                        }
                    };
                }
                
        );
        
        
        tabRapports.getColumns().addAll(colNumero, colNom,colVille, colVisite,colReda);
        
        tabRapports.setRowFactory(
                ligne -> {
                    return new TableRow<RapportVisite>(){
                    @Override
                    protected void updateItem(RapportVisite item, boolean empty){
                        super.updateItem(item,empty);
                        
                        if(item != null){
                            if(item.isLu()){
                                setStyle("-fx-background-color: #00BFFF");
                            }                        
                            else{
                                setStyle("-fx-background-color: #ADD8E6");
                            }
                        }
                        
                    }
                };
                }
        );
        
        
        tabRapports.setOnMouseClicked(
          (MouseEvent event) -> {
            if(event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2){
                int indiceRapport = tabRapports.getSelectionModel().getSelectedIndex();
                RapportVisite rv = (RapportVisite)tabRapports.getSelectionModel().getSelectedItem();
                
                String matricule = cbVisiteurs.getSelectionModel().getSelectedItem().getMatricule();
                int numero = rv.getNumero();
                
                try {
                    ModeleGsbRv.setRapportsVisiteLu(matricule, numero);
                }
                catch(Exception e){
                    
                }
                    
                this.rafraichir();
                
                rv.getLeVisiteur().setMatricule(matricule);
                
                VueRapport rapport = new VueRapport(rv);
                Optional<Pair<String,String>> reponse = rapport.showAndWait();
                
            }
            else{
                
            }
        }
        );
               
        vb.getChildren().add(tabRapports);
        

        this.getChildren().add(vb);
        
        
        
        
        
        
        
        
        
        
        
        try{

            List<Visiteur> visiteurs = (List<Visiteur>) ModeleGsbRv.getVisiteurs();


            for(Visiteur unVisiteur : visiteurs){
                cbVisiteurs.getItems().add(unVisiteur);
                
                
                
                
                
                
               
            }
  
        }
        catch(Exception e){

        }
        
       
        
        for(Mois unMois : Mois.values()){
            cbMois.getItems().add(unMois);
        }
        
        LocalDate adj = LocalDate.now();
        int anneeCourante = adj.getYear();
        
        cbAnnee.getItems().add(anneeCourante);
        
        for(int i = 1;i < 5;i++){
            cbAnnee.getItems().add(anneeCourante - i);
        }
       
        
        
        
        try{
            List<RapportVisite> rv = (List<RapportVisite>) ModeleGsbRv.getRapportsVisite("a131", 3, 2018);

            for(RapportVisite unRv : rv){
                System.out.println(unRv);
                
               
            }
            System.out.println("\n");
            
            

            
       }
       catch(Exception e){
        }




        //try{

//            ModeleGsbRv.setRapportsVisiteLu("a131", 5);
            
//        }
//        catch(Exception e){

//        }

    }
    
    public void rafraichir() {
        if(cbVisiteurs.getSelectionModel().isEmpty() || cbMois.getSelectionModel().isEmpty() || cbAnnee.getSelectionModel().isEmpty()) {
            Alert alertIncomplet = new Alert(Alert.AlertType.CONFIRMATION);
            alertIncomplet.setTitle("Saisie incomplète");
            alertIncomplet.setHeaderText("Vous n'avez pas rentré tout les paramètres requis.");
            String param = "";

            if(cbVisiteurs.getSelectionModel().isEmpty()){
                param = param + "Visiteur ";
            }
            if(cbMois.getSelectionModel().isEmpty()){
                param = param + "Mois ";
            }
            if(cbAnnee.getSelectionModel().isEmpty()){
                param = param + "Annee ";
            }
            
            alertIncomplet.setContentText(param + "manquant.");
            
            
            

            ButtonType btnOk = new ButtonType("OK");



            alertIncomplet.getButtonTypes().setAll(btnOk);
            Optional<ButtonType> reponse = alertIncomplet.showAndWait();
            if(reponse.get() == btnOk){
                alertIncomplet.close();
            }
            else {

            };
        }
        else {
            System.out.println(cbVisiteurs);
            String matricule = cbVisiteurs.getSelectionModel().selectedItemProperty().getValue().getMatricule();
            int mois = cbMois.getSelectionModel().getSelectedIndex() + 1;
            int annee = cbAnnee.getSelectionModel().getSelectedItem();
            
            try{

                List<RapportVisite> rapports = (List<RapportVisite>) ModeleGsbRv.getRapportsVisite(matricule, mois, annee);


                ObservableList<RapportVisite> list = FXCollections.observableArrayList();

            for(RapportVisite unRapport : rapports){
                list.add(unRapport);
    
            }

            tabRapports.setItems(list);
        }
        catch(Exception e){

        }
        }
    }
    
    
}
