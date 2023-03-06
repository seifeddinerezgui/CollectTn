/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;


import collecti.entity.Pieces;
import collecti.tools.Maconnection;
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
//import java.time.Duration;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AjouterPieceController implements Initializable {

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
    private Button btnajouter;
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
    //Notifications no;
    String erreur;
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
            /*   List<Categories> listcat;
            listcat = cs.getAll();
            //System.out.print(listcat);
            List<String> listTest= new ArrayList();
            for  (int i=0 ; i<=listcat.size();i++) {
            String nomc = listcat.get(i).getNomCat().toString();
            listTest.add(nomc);
            
            }
            ObservableList<String> CategorieCombo = FXCollections.observableArrayList(listTest);
            menucat.setItems(CategorieCombo);
             */
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
    private void ajouterPiece(ActionEvent event) {

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
            ctrlnom.setText("");
            ctrlidm.setText("");
            ctrlpd.setText("");
            ctrldesc.setText("");
            ctrlcat.setText("");
            Pieces p = new Pieces();
            p.setNom_piece(tfnom.getText());
            p.setDescription(tfdesc.getText());
            String Boxcat = menucat.getValue().toString();
            p.setId_maison(tfidm.getText());
            p.setPrix_depart(Integer.parseInt(tfpd.getText()));
            

            
            String sql = "select categories.id_cat as idc from categories where nom_cat='" + Boxcat + "'";
            ObservableList<Integer> CategorieCombo = FXCollections.observableArrayList();
            cnx = Maconnection.getInstance().getCnx();
            PreparedStatement psc;
            try {
                psc = cnx.prepareStatement(sql);

                ResultSet rs = psc.executeQuery(sql);
                while (rs.next()) {

                    int idcat = rs.getInt("idc");
                    CategorieCombo.add(idcat);
                    //new Categories(rs.getInt("cat"))

                    //CategorieCombo.add(s);
                }
                int id = CategorieCombo.get(0);
                p.setCat(id);
                p.setImg(importPhoto.getText());
                ps.ajouter(p);
                reset();
            } catch (SQLException ex) {
                Logger.getLogger(AjouterPieceController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void Back(ActionEvent event) {
        try {

            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherPiece.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("My Window Title"); // set the title of the window
        stage.setResizable(false); // make the window not resizable
        Region root = (Region) loader.lookup("#root"); // get the root node of the scene
        root.setPrefSize(800, 600); // set the preferred size of the root node
        stage.show(); // show the window
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
