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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class ArchiverController implements Initializable {

    @FXML
    private GridPane GridArchived;
    EnchereService Es = new EnchereService();
    @FXML
    private Button backbtnid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            try {
             Es.checkDate();
            List<Enchere> enchere = Es.afficherEnchere3();
            int row = 0;
            int column = 0;
            for (int i = 0; i < enchere.size(); i++) {
                //chargement dynamique d'une interface
                Enchere e=enchere.get(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModelGridEnchereArchive.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                ModelGridEnchereArchiveController controller = loader.getController();
                controller.setEncheree(e);
                GridArchived.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException ex ) {
             System.out.println(ex.getMessage());        }
      
    }    

    @FXML
    private void backbtn(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("EncherePartner.fxml"));
            backbtnid.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
