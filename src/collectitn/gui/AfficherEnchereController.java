/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;


import collectitn.services.EnchereService;
import collectitn.entites.Enchere;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class AfficherEnchereController implements Initializable {
    
    EnchereService Es = new EnchereService();
    @FXML
    private GridPane grid;
    @FXML
    private ImageView logoImg;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // TODO
          try {
             Es.checkDate();
            List<Enchere> enchere = Es.afficherEnchere2();
            int row = 0;
            int column = 0;
            for (int i = 0; i < enchere.size(); i++) {
                //chargement dynamique d'une interface
                Enchere e=enchere.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModelGridView.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                ModelGridViewController controller = loader.getController();
                controller.setEnchere(e);
                grid.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException ex ) {
             System.out.println(ex.getMessage());        }
        
}

    private void test(TouchEvent event) {
   try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("EncherePartner.fxml"));
            grid.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }    }

    @FXML
    private void navigatetohome(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Home.fxml"));
            logoImg.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


   
}