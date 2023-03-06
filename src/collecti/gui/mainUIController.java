/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collecti.gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class mainUIController implements Initializable {

    @FXML
    private TextField tf;
    @FXML
    private JFXButton open;
    @FXML
    private Hyperlink hl;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EnterPressed(KeyEvent event) {
    }

    @FXML
    private void openLink(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
    }
    
}
