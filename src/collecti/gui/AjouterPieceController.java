/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collecti.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AjouterPieceController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdesc;
    @FXML
    private ComboBox<?> menucat;
    @FXML
    private TextField tfidm;
    @FXML
    private TextField tfpd;
    @FXML
    private Button btnbrowse;
    @FXML
    private Button btnajouter;
    @FXML
    private Label ctrlnom;
    @FXML
    private Label ctrldesc;
    @FXML
    private Label ctrlcat;
    @FXML
    private Label ctrlidm;
    @FXML
    private Label ctrlpd;
    @FXML
    private Button btnretour;
    @FXML
    private Label importPhoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ImportPhoto(ActionEvent event) {
    }

    @FXML
    private void ajouterPiece(ActionEvent event) {
    }

    @FXML
    private void Back(ActionEvent event) {
    }
    
}
