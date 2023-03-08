/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;


import collectitn.entites.Categories;
import collectitn.services.CategoriesServices;
import collectitn.tool.Maconnection;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    ObservableList<Categories> listcategories =FXCollections.observableArrayList();
    @FXML
    private Button btnretour;
    @FXML
    private JFXListView<Categories> listcat;
    Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

try {
            Connection cnx = Maconnection.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("select * from categories");
            while (rs.next()) {
                listcategories.add(new Categories(
                        rs.getInt("id_cat"),
                        rs.getString("nom_cat")
                ));

            }

        } catch (Exception ex) {
            Logger.getLogger(AjouterCatController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        listcat.setItems(listcategories);

        listcat.setCellFactory(new Callback<ListView<Categories>, ListCell<Categories>>() {
            @Override
            public ListCell<Categories> call(ListView<Categories> listView) {
                return new ListCell<Categories>() {
                    @Override
                    protected void updateItem(Categories item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getIdCat()+item.getNomCat());
                        }
                    }
                };
            }
        });
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
        stage.show(); // show the window
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
    

