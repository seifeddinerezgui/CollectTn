/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;



import collecti.tools.Maconnection;
import collectitn.entites.Pieces;
import collectitn.services.CategoriesServices;
import collectitn.services.PiecesServices;
import static java.awt.SystemColor.control;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ModifierPieceController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfidm;
    @FXML
    private TextField tfpd;
    @FXML
    private TextField tfdesc;
    @FXML
    private ComboBox<String> menucat;
    @FXML
    private Button btnmodifier;
    PiecesServices ps = new PiecesServices();
    CategoriesServices cs = new CategoriesServices();
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

    Connection cnx;
    private FileChooser fileChooser = new FileChooser();
    @FXML
    private Button btnbrowse;
    @FXML
    private Label importPhoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            String sql = "SELECT categories.nom_cat as nomcat FROM categories ";
            ObservableList<String> CategorieCombo = FXCollections.observableArrayList();

            cnx = Maconnection.getInstance().getCnx();
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {

                String cat = rs.getString("nomcat");
                String s = new String(cat);
                //new Categories(rs.getInt("cat"))

                CategorieCombo.add(s);
            }
            menucat.setItems(CategorieCombo);

        } catch (SQLException ex) {
            Logger.getLogger(AjouterPieceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reset() {
        ctrlnom.setText("");
        ctrlidm.setText("");
        ctrlpd.setText("");
        ctrldesc.setText("");
        ctrlcat.setText("");
    }

    @FXML
    private void UpdatePiece(ActionEvent event) {

         // Check if any required fields are empty
    if (tfnom.getText().isEmpty() || tfidm.getText().isEmpty() || tfpd.getText().isEmpty() || tfdesc.getText().isEmpty() || menucat.getValue() == null) {
        // Display error message for each empty field
        if (tfnom.getText().isEmpty()) {
            ctrlnom.setText("field is empty");
        } else {
            ctrlnom.setText("");
        }
        if (tfdesc.getText().isEmpty()) {
            ctrldesc.setText("field is empty");
        } else {
            ctrldesc.setText("");
        }
        if (menucat.getValue() == null) {
            ctrlcat.setText("field is empty");
        } else {
            ctrlcat.setText("");
        }
        if (tfidm.getText().isEmpty()) {
            ctrlidm.setText("field is empty");
        } else {
            ctrlidm.setText("");
        }
        if (tfpd.getText().isEmpty()) {
            ctrlpd.setText("field is empty");
        } else {
            ctrlpd.setText("");
        }

    } else {
        // All required fields are filled, so create a new Pieces object and set its attributes
        Pieces p = new Pieces();
        p.setNom_piece(tfnom.getText());
        p.setDescription(tfdesc.getText());
        String Boxcat = menucat.getValue().toString();
        p.setId_maison(tfidm.getText());
        p.setPrix_depart(Integer.parseInt(tfpd.getText()));

        // Get the ID of the selected category from the database
        String sql = "SELECT id_cat FROM categories WHERE nom_cat=?";
        cnx = Maconnection.getInstance().getCnx();
        PreparedStatement psc;
        try {
            psc = cnx.prepareStatement(sql);
            psc.setString(1, Boxcat);
            ResultSet rs = psc.executeQuery();
            if (rs.next()) {
                int idcat = rs.getInt("id_cat");
                p.setCat(idcat);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AjouterPieceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set the image path for the Pieces object
        p.setImg(importPhoto.getText());

        // Call the update method from the PiecesService class to update the database with the new Pieces object
        PiecesServices ps = new PiecesServices();
        ps.update(p);

        // Reset all input fields and error messages
        reset();
    }
    }

    @FXML
    private void Back(ActionEvent event) {
        try {

            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherPiece.fxml"));
            btnretour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ImportPhoto(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(new Stage());
        importPhoto.setText(file.toString());
        System.out.println(file.toString());
    }

}
