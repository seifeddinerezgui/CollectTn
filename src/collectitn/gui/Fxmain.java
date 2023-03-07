/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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

    public void start(Stage primaryStage) throws Exception  {
          Parent roothome = FXMLLoader.load(getClass().getResource("Home.fxml"));

        primaryStage.setTitle("collecti.tn");
        primaryStage.setScene(new Scene(roothome));
        primaryStage.sizeToScene();
//        primaryStage.setResizable(false);
        primaryStage.show();
    


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
