/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;


import java.time.LocalDate;
import Alert.AlertDialog;
import collectitn.entites.Enchere;
import collectitn.services.EnchereService;
import collectitn.entites.Pieces;
import java.net.URL;

import java.io.IOException;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rihab
 */
public class AfficherEncherePartnerController implements Initializable {

    @FXML
    private ListView<Enchere> idList;
    @FXML
    private DatePicker DateFin;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private TextField prix;
    @FXML
    private Button EModifier;
    @FXML
    private Button ESupprimer;

    /**
     * Initializes the controller class.
     */
    ObservableList<Enchere> enchere=FXCollections.observableArrayList();
    @FXML
    private TextField rech;
    @FXML
    private Button EnchereArchivedid;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            afficher();
            //afficherID();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEncherePartnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    
      public void afficher() throws SQLException{
          EnchereService sr = new EnchereService();
        enchere=FXCollections.observableArrayList(sr.afficherEnchere2());
        idList.setItems(enchere);
    }


    @FXML
    private void ModifierEnchere(ActionEvent event) throws SQLException {
        Pieces p = new Pieces();
         Enchere e = new Enchere();
         EnchereService sr = new EnchereService();
                e.setDate_debut(dateDebut.getValue());
               e.setDate_fin(DateFin.getValue());
                e.setPrix_actuel(Float.parseFloat(prix.getText()));
              
               
                sr.updateEnchere(e, idList.getSelectionModel().getSelectedItem().getId());
                AlertDialog.showNotification("modifier","avec succees", AlertDialog.image_checked);
                afficher();
    }

    @FXML
    private void SupprimerEnchere(ActionEvent event) throws SQLException {
          EnchereService sr = new EnchereService();
        Enchere p = new Enchere();
        sr.supprimerEnchere(p,idList.getSelectionModel().getSelectedItem().getId());
        AlertDialog.showNotification("l'enchere"," Enchere a été archivé avec succee", AlertDialog.image_checked);
        afficher();

    }

    @FXML
    private void fill(MouseEvent event) {
        Enchere p= idList.getSelectionModel().getSelectedItem();
     //   Piece.setText(p.getP().getNom_piece());
         dateDebut.setValue(p.getDate_debut());
        DateFin.setValue(p.getDate_fin());
        prix.setText(String.valueOf(p.getPrix_actuel()));
       
    }

    @FXML
    private void goToAjout(ActionEvent event) {
         try {
            
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("EncherePartner.fxml"));
            Stage stage =new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Collecti");
            stage.show();
        } catch (IOException ex) {
               System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
         EnchereService ps = new EnchereService();
    List<Enchere> Reservation = ps.Recherche (rech.getText());
    idList.getItems().clear();
    idList.getItems().removeAll(Reservation);
    idList.getItems().addAll(Reservation);
    }

    @FXML
    private void goEnchereArchiveed(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Archiver.fxml"));
            EnchereArchivedid.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
