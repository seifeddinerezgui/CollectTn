/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;

import collectitn.entites.Enchere;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class ModelGridEnchereArchiveController implements Initializable {

    @FXML
    private Label namePiece;
    @FXML
    private Label dateDebutPiece;
    @FXML
    private Label dateFinPiece;
    @FXML
    private Label prixDepartPiece;
    @FXML
    private ImageView pieceImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public void setEncheree(Enchere e) {
         
         System.out.println("vghgbjnk,"+"collectitn/tool/images/"+e.getP().getImg());
         System.out.println("gridmoe"+e.toString());
          String imgName = e.getP().getImg().substring(e.getP().getImg().lastIndexOf("\\") + 1);
         pieceImg.setImage(new Image("collectitn/tool/images/"+imgName));
        namePiece.setText(e.getP().getNom_piece());
        dateDebutPiece.setText(e.getDate_debut().toString());
       dateFinPiece.setText(e.getDate_fin().toString());
        prixDepartPiece.setText(String.valueOf(e.getPrix_actuel()));
    }    
    
}
