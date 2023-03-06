/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collecti.gui;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AjouterCatController implements Initializable {

    @FXML
    private TextField tfcat;
    @FXML
    private Button btncat;
    @FXML
    private Label ctrlcat;
    @FXML
    private Button btnretour;
    @FXML
    private JFXListView<?> listcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterCat(ActionEvent event) {
    }

    @FXML
    private void BackHome(ActionEvent event) {
    }
    
}
