/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;

import Alert.AlertDialog;
import collectitn.entites.Enchere;
import collectitn.entites.Pieces;
import collectitn.services.EnchereService;
import collectitn.tool.Maconnection;
import java.util.Date;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class EncherePartnerController implements Initializable {

    EnchereService Es = new EnchereService();
    @FXML
    private TextField prixDepartField;
    @FXML
    private DatePicker DateDebutField;
    @FXML
    private DatePicker DateFinField;
    @FXML
    private Label PieceCntrlSaisie;
 
    @FXML
    private Label PrixDepartCntrlSaisie;
 
    @FXML
    private ComboBox<String> PieceFields;
    Connection cnx;

    String PieceName;
    @FXML
    private Button afficherBtn;
    @FXML
    private ImageView background;
    @FXML
    private ImageView logoImg;
    @FXML
    private ImageView imgPiece;
    @FXML
    private Label errorLabel;
    private boolean verification1Date;
     private boolean verification2Date;
    @FXML
    private Label DateFinCntrlSaisie;
    @FXML
    private Label DateDebutCntrlSaisie;
    

    /**
     * Initializes the controller class.
     */
    public EncherePartnerController() {
        cnx = Maconnection.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<String> ListeN = FXCollections.observableArrayList();
            Statement ste = cnx.createStatement();
            String sqlGetNamePiece = "SELECT nom_piece FROM `pieces` WHERE etat=0 ";
            ResultSet N = ste.executeQuery(sqlGetNamePiece);
            while (N.next()) {
                String name = N.getString("nom_piece");
                String n1 = new String(name);
                ListeN.add(n1);
            }
            PieceFields.setItems(ListeN);
            

        } catch (SQLException ex) {
            Logger.getLogger(EncherePartnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterEnchere(ActionEvent event) {
        if (DateDebutField.getValue() == null && DateFinField.getValue() == null )  {
            DateDebutCntrlSaisie.setText("Date Debut ne peut pas etre vide");
            DateFinCntrlSaisie. setText("Date Fin ne peut pas etre vide");

        } else if (DateDebutField.getValue() == null) {
            DateDebutCntrlSaisie.setText("Date Debut ne peut pas etre vide");
            PrixDepartCntrlSaisie.setText("");
            DateFinCntrlSaisie.setText("");

        } else if (DateFinField.getValue() == null ) {
            DateFinCntrlSaisie.setText("Date Fin ne peut pas etre vide");
            PrixDepartCntrlSaisie.setText("");
            DateDebutCntrlSaisie.setText("");

        }
        else if (DateDebutField.getValue().isBefore(java.time.LocalDate.now()) ) {
            DateFinCntrlSaisie.setText("Date Debut doit etre apres date d'aujourdhui");
            PrixDepartCntrlSaisie.setText("");
            DateDebutCntrlSaisie.setText("");

        }
        else if (DateFinField.getValue().isBefore(DateDebutField.getValue()) ) {
            DateDebutCntrlSaisie.setText("Date Fin doit etre apres date debut");
            PrixDepartCntrlSaisie.setText("");
            DateDebutCntrlSaisie.setText("");

        }
        else {
            
        PrixDepartCntrlSaisie.setText("");
        DateDebutCntrlSaisie.setText("");
        DateFinCntrlSaisie.setText("");
        System.out.println(DateFinField.getValue());
        Enchere e = new Enchere();

        try {
            Statement ste = cnx.createStatement();

            PieceName = PieceFields.getValue();
            String Reqpieces = "select * from pieces where '" + PieceName + "' = nom_piece";
            ResultSet rs = ste.executeQuery(Reqpieces);

            if (rs.next()) {
                int id_piece = rs.getInt(1);
                String nom_piece = rs.getString("nom_piece");
                String description = rs.getString("description");
                String id_maison = rs.getString("id_maison");
                int prix_depart = rs.getInt("prix_depart");
                int cat = rs.getInt("cat");
                String img =rs.getString("img");
             PreparedStatement ps;
               String reqChangeEtatPiece = "UPDATE pieces SET `etat`=1 WHERE `id_piece`="+id_piece;
              
       
            ps = cnx.prepareStatement(reqChangeEtatPiece);
         
       
            
            ps.execute();
               

                // create an object using the data from the query
                Pieces myObject = new Pieces(id_piece,prix_depart);

                // do something with the object
                System.out.println(myObject.toString());

                e.setP(myObject);
                e.setDate_debut(DateDebutField.getValue());
                e.setDate_fin(DateFinField.getValue());
                Es.ajouterEnchere(e);
                AlertDialog.showNotification("ajout","avec succee", AlertDialog.image_checked);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
          }
    }

    @FXML
    private void ActionDepImage(ActionEvent event) {
        PieceName = PieceFields.getValue().toString();
        //System.out.print(PieceName);
        ObservableList<String> ListeP = FXCollections.observableArrayList();
        ObservableList<String> ListePp = FXCollections.observableArrayList();
        try {
            String SqlGetPrix = "select prix_depart,img from pieces where '" + PieceName + "' = nom_piece";

            Statement ste = cnx.createStatement();
            ResultSet P = ste.executeQuery(SqlGetPrix);
            while (P.next()) {
                int prix = P.getInt("prix_depart");
                String img=P.getString("img");
               // String path = P.getString(7);
               String pll=new String(img);
                Integer p1 = new Integer(prix);
                ListeP.add(p1.toString());
                ListePp.add(pll);
             //   Image img = new Image(getClass().getResourceAsStream(path));
            }
            prixDepartField.setText(ListeP.get(0));
//            System.out.println("collectitn/tool/images/"+ListePp.get(0));
//        imgPiece.setImage(new Image("collectitn/tool/images/"+ListePp.get(0)));
        String imgName = ListePp.get(0).substring(ListePp.get(0).lastIndexOf("\\") + 1);
         imgPiece.setImage(new Image("collectitn/tool/images/"+imgName));
            //imgPrieceId.setImage(img);
        } catch (SQLException ex) {
            Logger.getLogger(EncherePartnerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void AfficherEnchere(ActionEvent event) {
        try {
            
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("AfficherEncherePartner.fxml"));
            Stage stage =new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Collecti");
            stage.show();
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    private Boolean testDate( ) {
          LocalDate now= LocalDate.now();
        LocalDate date =DateDebutField.getValue();
        
        if(date.isAfter(now)){
           
            DateDebutCntrlSaisie.setText("Date valide");
            
           return verification1Date = true;
        }else{
           
              DateDebutCntrlSaisie.setText("Date non valide");
             return verification1Date = false;
        
        }
    }
     private Boolean testDate2( ) {
          LocalDate datedebut= DateDebutField.getValue();
        LocalDate datefin =DateFinField.getValue();
        
        if(datefin.isBefore(datedebut)){
           
            DateFinCntrlSaisie.setText("Date valide");
            
           return verification2Date = true;
        }else{
           
              DateFinCntrlSaisie.setText("Date non valide");
             return verification2Date = false;
        
        }
    }

    @FXML
    private void backtohome(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Home.fxml"));
            logoImg.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
