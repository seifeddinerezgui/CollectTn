/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author acer
 */
public class Fxmain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException  {
     Parent root = FXMLLoader.load(getClass().getResource("AfficherEnchere.FXML"));
        
        
        primaryStage.setTitle("Enchere Partner");
        Scene Scene = new Scene(root);
        primaryStage.setScene(Scene );
        primaryStage.show();
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
