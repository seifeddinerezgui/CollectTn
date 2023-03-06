/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collecti.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AfficherPieceController implements Initializable {

    @FXML
    private Button btnretour;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private TableView<?> TablePiece;
    @FXML
    private TableColumn<?, ?> tblid;
    @FXML
    private TableColumn<?, ?> tblpiece;
    @FXML
    private TableColumn<?, ?> tblnom;
    @FXML
    private TableColumn<?, ?> tblcat;
    @FXML
    private TableColumn<?, ?> tblmv;
    @FXML
    private TableColumn<?, ?> tblpd;
    @FXML
    private TableColumn<?, ?> tbldesc;
    @FXML
    private JFXTextField tfsearch;
    @FXML
    private JFXComboBox<?> menucat;
    @FXML
    private Button btnexel;
    @FXML
    private Button btnrefresh;
    @FXML
    private Button itemview;
    @FXML
    private ImageView imgview;
    @FXML
    private Button qr_code;
    @FXML
    private ImageView qrimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Back(ActionEvent event) {
    }

    @FXML
    private void AjouterPiece(MouseEvent event) {
    }

    @FXML
    private void DeletePiece(MouseEvent event) {
    }

    @FXML
    private void ModifierPiece(MouseEvent event) {
    }

    @FXML
    private void UpdatePiece(ActionEvent event) {
    }

    @FXML
    private void recherche_avance(KeyEvent event) {
    }

    @FXML
    private void exel(MouseEvent event) {
    }

    @FXML
    private void refreshList(ActionEvent event) {
    }

    @FXML
    private void onClick(MouseEvent event) {
    }

    @FXML
    private void generateQRCode(ActionEvent event) {
    }
    
}
