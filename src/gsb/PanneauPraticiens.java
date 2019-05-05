/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author etudiant
 */
public class PanneauPraticiens extends Pane{
    
    public Integer CRITERE_COEF = 1;
    public Integer CRITERE_NOTO = 2;
    public Integer CRITERE_DATE = 3;
    
    
    private Integer critereTri = CRITERE_COEF;
    
    private TableView<Praticien> tablePra;
    
    public PanneauPraticiens() {
    
        this.setStyle("-fx-alignment: center; -fx-background-color: white;");
        
        VBox vb = new VBox();
        
        
        
        Label lblPraticiens = new Label("Sélectionner un critère de tri :");
        
        RadioButton conf = new RadioButton("Confiance");
        RadioButton noto = new RadioButton("Notoriété");
        RadioButton date = new RadioButton("Date Visite");
        
        ToggleGroup group = new ToggleGroup();
        
        
        conf.setToggleGroup(group);
        noto.setToggleGroup(group);
        date.setToggleGroup(group);
        
        GridPane grid = new GridPane();
        
        grid.getChildren().addAll(conf,noto,date);
        
        grid.setColumnIndex(conf, 0);
        grid.setColumnIndex(noto, 1);
        grid.setColumnIndex(date, 2);

        conf.setSelected(true);
        
        grid.setStyle("-fx-alignment: center");
        
        tablePra = new TableView();
        
        TableColumn <Praticien,Integer> colNumero = new TableColumn<Praticien, Integer>("Numéro");
        TableColumn <Praticien,String> colNom = new TableColumn<Praticien, String>("Nom");
        TableColumn <Praticien,String> colVille = new TableColumn<Praticien, String>("Ville");
        
        tablePra.getColumns().addAll(colNumero, colNom, colVille);
        
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        
        
        
        
        grid.setHgap(10);
        grid.setVgap(10);
        
        vb.setSpacing(10);
 
        
        
        vb.getChildren().add(lblPraticiens);
        vb.getChildren().add(grid);
        this.getChildren().add(vb);
        
        
        try{

            List<Praticien> praticiens = (List<Praticien>) ModeleGsbRv.getPraticiensHesitants();

            Collections.sort(praticiens, new ComparateurCoefConfiance()); 
            
            ObservableList<Praticien> list = FXCollections.observableArrayList();

            for(Praticien unPraticien : praticiens){
                list.add(unPraticien);
                
               
            }
            System.out.println("\n");
            
            

            
        
            tablePra.setItems(list);
        }
        catch(Exception e){

        }
        
        vb.getChildren().add(tablePra);
        
        
        
        

                    
        conf.setOnAction(( ActionEvent event )->{

                    critereTri = CRITERE_COEF;
                    this.rafraichir();
        
   
        
                }
            
        );
        
        noto.setOnAction(( ActionEvent event )->{
                    critereTri = CRITERE_NOTO;
                    this.rafraichir();
                    }
        );
        
        date.setOnAction(( ActionEvent event )->{
                    critereTri = CRITERE_DATE;
                    this.rafraichir();
                    }
        );
    
    
    }
    
    public void rafraichir() {
        
        List<Praticien> pra;
        try{
            pra = ModeleGsbRv.getPraticiensHesitants();
            ObservableList<Praticien> ol = FXCollections.observableArrayList(pra);
            
            Collections.sort(ol,new ComparateurCoefConfiance());

            if(critereTri == 1){
                tablePra.setItems(ol);
            }
            else if(critereTri == 2){
                Collections.sort(ol, new ComparateurCoefNotoriete());
                Collections.reverse(ol);
                tablePra.setItems(ol);
            }
            else if(critereTri == 3){
                Collections.sort(ol, new ComparateurDateVisite());
                Collections.reverse(ol);
                tablePra.setItems(ol);
            }
        }
        catch( Exception e ){
        }
    }
    

                
        
    
}
