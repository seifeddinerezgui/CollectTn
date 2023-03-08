/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class HomeUsrController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoEnchere(ActionEvent event) {
         try {
            //navigation
              Parent loader = FXMLLoader.load(getClass().getResource("AfficherEnchere.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Enchere"); // set the title of the window
        stage.setResizable(false); // make the window not resizable
        Region root = (Region) loader.lookup("#root"); // get the root node of the scene
     
        stage.show(); // show the window
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
