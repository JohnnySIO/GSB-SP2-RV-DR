/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb;

import java.awt.Image;
import java.io.InputStream;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author etudiant
 */
public class PanneauAccueil extends Pane{
    public PanneauAccueil() {
        this.setStyle("-fx-alignment: center; -fx-background-color: white;");
        ImageView img = new ImageView("file:src/logo.png");
        
        img.setFitHeight(225);
        img.setFitWidth(300);
        img.setX(150);
        img.setY(125);
        this.getChildren().add(img);
        

    }
    
}
