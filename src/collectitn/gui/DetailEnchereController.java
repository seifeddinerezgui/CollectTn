/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;


import collectitn.entites.Enchere;
import collectitn.services.EnchereService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class DetailEnchereController implements Initializable {

    @FXML
    private ImageView imagePiece;
    @FXML
    private Label nomPiece;
    @FXML
    private Label descPiece;
    @FXML
    private Button encherer;

    private int enchereId;
    private float prixEnchere;

       Connection cnx;
    @FXML
    private Spinner<Integer> prix;
    @FXML
    private Button retour;
    @FXML
    private Label dateDebut;
    @FXML
    private Label dateFin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ModelGridViewController a= new ModelGridViewController();
         Enchere e=a.publicEnchere;
         imagePiece.setImage(new Image("collectitn/tool/images/"+e.getP().getImg()));
         nomPiece.setText(e.getP().getNom_piece());
         descPiece.setText(e.getP().getDescription());
         dateDebut.setText(e.getDate_debut().toString());
         dateFin.setText(e.getDate_fin().toString());
         prixEnchere=e.getPrix_actuel();
         
         
        final int initialValue =  Math.round(prixEnchere);
        int increment=(int) Math.round(initialValue*0.1);
       

        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(initialValue, 100000, initialValue,increment);

        prix.setValueFactory(valueFactory);
        

         

         
         
         

    }    
    public void get(){
        
    }

    @FXML
    private void encherer(ActionEvent event) {
        ModelGridViewController a= new ModelGridViewController();
        Enchere e=a.publicEnchere;
        e.setPrix_actuel(prix.getValue());
        e.setIdUser(1);
         EnchereService sr = new EnchereService();
         sr.updateEnchere(e, e.getId());
        
    }

    @FXML
    private void retour(ActionEvent event) {
          try {
            
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("AfficherEnchere.fxml"));
            Stage stage =new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Collecti");
            stage.show();
        } catch (IOException ex) {
               System.out.println(ex.getMessage());
        }
    }
}
