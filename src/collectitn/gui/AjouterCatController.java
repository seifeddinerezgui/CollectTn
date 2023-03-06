/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;


import collectitn.entites.Categories;
import collectitn.services.CategoriesServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.jfoenix.controls.JFXListView;
import static java.awt.SystemColor.control;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AjouterCatController implements Initializable {

    @FXML
    private TextField tfcat;
    @FXML
    private Button btncat;
    @FXML
    private Label ctrlcat;

    CategoriesServices cs = new CategoriesServices();
    @FXML
    private Button btnretour;
    @FXML
    private JFXListView<?> listcat;
    Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        List<String> listcat = new ArrayList<String>();
//        try {
//            
//
//            // Connect to the database
//            Connection cnx = Maconnection.getInstance().getCnx();
//            
//            // Execute a query to retrieve all categories from the database
//            Statement stmt = cnx.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT nom_cat FROM categories");
//
//            // Loop through the result set and add each category to the ListView
//            while (rs.next()) {
//                String category = rs.getString("nom_cat");
//                listcat.add(category);
//            }
//
//            // Close the result set, statement, and connection
////            rs.close();
////            stmt.close();
////            cnx.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }   
    private void reset() {
    ctrlcat.setText("");
}

    @FXML
    private void ajouterCat(ActionEvent event) {
        
        if (tfcat.getText().isEmpty()){
        ctrlcat.setText("field is empty");
        }else{
        
        Categories c = new Categories();
        c.setNomCat(tfcat.getText());
        cs.ajouter(c);
        reset();
    }
    }

    @FXML
    private void BackHome(ActionEvent event) {
        try {
            //navigation
               Parent loader = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home"); // set the title of the window
        stage.setResizable(false); // make the window not resizable
        Region root = (Region) loader.lookup("#root"); // get the root node of the scene
        root.setPrefSize(800, 600); // set the preferred size of the root node
        stage.show(); // show the window
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
    

