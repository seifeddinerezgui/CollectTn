/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;

import collectitn.entites.Enchere;
import collectitn.entites.Pieces;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class ModelGridViewController implements Initializable {

        Connection cnx;

    @FXML
    private ImageView pieceImg;
    @FXML
    private Label namePiece;
    @FXML
    private Label dateDebutPiece;
    @FXML
    private Label dateFinPiece;
    @FXML
    private Label prixDepartPiece;
    @FXML
    private Button detailsbtn;
    @FXML
    private Label EnchereId;
    public static Enchere publicEnchere;
    public  Enchere ee;

                                
 

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setEnchere(Enchere e) {
         ee=e;
         System.out.println("vghgbjnk,"+"collectitn/tool/images/"+e.getP().getImg());
         System.out.println("gridmoe"+e.toString());
          String imgName = e.getP().getImg().substring(e.getP().getImg().lastIndexOf("\\") + 1);
         pieceImg.setImage(new Image("collectitn/tool/images/"+imgName));
        namePiece.setText(e.getP().getNom_piece());
        dateDebutPiece.setText(e.getDate_debut().toString());
       dateFinPiece.setText(e.getDate_fin().toString());
        prixDepartPiece.setText(String.valueOf(e.getPrix_actuel()));
    }

    @FXML
    private void terst(KeyEvent event) {
        System.out.println("fdes");
    }

    @FXML
    private void detailsAction(ActionEvent event) {
        
       try {
            //navigation
            
            publicEnchere=ee;
           
            
            Parent loader = FXMLLoader.load(getClass().getResource("DetailEnchere.fxml"));
            detailsbtn.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
   }
    
}
