/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.gui;

import collectitn.tool.Maconnection;
import collectitn.entites.Pieces;
import collectitn.services.PiecesServices;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import static java.awt.SystemColor.control;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AfficherPieceController implements Initializable {

    @FXML
    private Button btnretour;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    Connection cnx;
    //Pieces p = null;
    //@FXML
    //private GridPane gridall;
    @FXML
    private JFXTextField tfsearch;
    private JFXButton btnsearch;
    @FXML
    private JFXComboBox<String> menucat;
    @FXML
    private TableView<Pieces> TablePiece;
    @FXML
    private TableColumn<?, ?> tblid;
    @FXML
    private TableColumn<?, ?> tblpiece;
    @FXML
    private TableColumn<?, ?> tblnom;
    @FXML
    private TableColumn<?, ?> tblcat;
    @FXML
    private TableColumn<?, ?> tblmv;
    @FXML
    private TableColumn<?, ?> tbldesc;
    ObservableList<Pieces> PiecesList = FXCollections.observableArrayList();
    ObservableList<Pieces> data = FXCollections.observableArrayList();
    PiecesServices ps = new PiecesServices();
    Pieces pieces = null;
    @FXML
    private TableColumn<?, ?> tblpd;
    @FXML
    private Button btnrefresh;
    @FXML
    private Button itemview;
    @FXML
    private ImageView imgview;
    @FXML
    private Button qr_code;
    private Hyperlink hl;
    private String url;
    @FXML
    private ImageView qrimg;
    @FXML
    private Button btnexel;

    public AfficherPieceController() {
        cnx = Maconnection.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Pieces> p = ps.getAll();
        ObservableList<Pieces> listPers = FXCollections.observableArrayList(p);// convertir list to ObservableList fiha iterator
        tblid.setCellValueFactory(new PropertyValueFactory<>("id_piece"));
        tblpiece.setCellValueFactory(new PropertyValueFactory<>("img"));
        tblnom.setCellValueFactory(new PropertyValueFactory<>("nom_piece"));
        tblcat.setCellValueFactory(new PropertyValueFactory<>("cat"));
        tblmv.setCellValueFactory(new PropertyValueFactory<>("id_maison"));
        tblpd.setCellValueFactory(new PropertyValueFactory<>("prix_depart"));

        tbldesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        TablePiece.setItems(listPers);
        try {
            Connection cnx = Maconnection.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("select * from pieces");

            while (rs.next()) {
                PiecesList.add(new Pieces(
                        rs.getInt("id_piece"),
                        rs.getString("nom_piece"),
                        rs.getString("description"),
                        rs.getString("id_maison"),
                        rs.getInt("prix_depart"),
                        rs.getInt("cat"),
                        rs.getString("img")
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AfficherPieceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            /*   
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
//            menucat.setOnAction((event) -> {
//                String category = menucat.getValue();
//                ObservableList<Pieces> filteredPieces = PiecesList.filtered(piece -> String.valueOf(piece.getCat()).equals(category));
//                TablePiece.setItems(filteredPieces);
//
//            });
//            filterPiecesByCategory(null);

        } catch (SQLException ex) {
            Logger.getLogger(AjouterPieceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDate();
        TablePiece.setItems(PiecesList);

    }

//    private void filterPiecesByCategory(String category) {
//        if (category == null || category.isEmpty()) {
//            // If no category is selected, show all pieces
//            TablePiece.setItems(PiecesList);
//        } else {
//            // Filter the pieces by category
//            ObservableList<Pieces> filteredList = FXCollections.observableArrayList();
//            for (Pieces piece : PiecesList) {
//                if (piece.getCat().equals(category)) {
//                    filteredList.add(piece);
//                }
//            }
//            TablePiece.setItems(filteredList);
//        }
//    }

    private void loadDate() {
        tblnom.setCellValueFactory(new PropertyValueFactory<>("nom_piece"));
        tblpiece.setCellValueFactory(new PropertyValueFactory<>("img"));
        tbldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblpd.setCellValueFactory(new PropertyValueFactory<>("prix_depart"));
        tblmv.setCellValueFactory(new PropertyValueFactory<>("id_maison"));
        tblcat.setCellValueFactory(new PropertyValueFactory<>("cat"));
        tblid.setCellValueFactory(new PropertyValueFactory<>("id_piece"));

    }

    @FXML
    private void Back(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Home.fxml"));
            btnretour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AjouterPiece(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("ajouterPiece.fxml"));
            btnajouter.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void DeletePiece(MouseEvent event) {
        pieces = TablePiece.getSelectionModel().getSelectedItem();
        PiecesServices ps = new PiecesServices();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer !?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            ps.Supprimer(pieces.getId_piece());

        } else if (input == 1) {

        }

    }

    @FXML
    private void UpdatePiece(ActionEvent event) {
        try {

            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("ModifierPiece.fxml"));
            btnretour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void MainScreen_txtSearch_KeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            btnsearch.fire();
        }
    }

    @FXML
    private void recherche_avance(KeyEvent event) {
        System.out.println("*******************");

        //System.out.println(id.departement);
        FilteredList<Pieces> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(tfsearch.getText());
        tfsearch.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(pieces -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (String.valueOf(pieces.getId_piece()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (pieces.getImg().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (pieces.getDescription().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (pieces.getNom_piece().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (pieces.getId_maison().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(pieces.getCat()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(pieces.getPrix_depart()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<Pieces> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(TablePiece.comparatorProperty());
        TablePiece.setItems(filtereddata);

    }

    @FXML
    private void ModifierPiece(MouseEvent event) {
    }

    @FXML
    private void refreshList(ActionEvent event) {
        data.clear();
        data = FXCollections.observableArrayList(ps.getAll());
        tblnom.setCellValueFactory(new PropertyValueFactory<>("nom_piece"));
        tblpiece.setCellValueFactory(new PropertyValueFactory<>("img"));
        tblmv.setCellValueFactory(new PropertyValueFactory<>("id_maison"));
        tblpd.setCellValueFactory(new PropertyValueFactory<>("prix_depart"));
        tblcat.setCellValueFactory(new PropertyValueFactory<>("cat"));
        tblid.setCellValueFactory(new PropertyValueFactory<>("id_piece"));
        tbldesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        TablePiece.setItems(data);
    }

    @FXML
    private void onClick(MouseEvent event) {
        pieces = TablePiece.getSelectionModel().getSelectedItem();
        try {
            File file = new File(pieces.getImg());
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage javafxImage = SwingFXUtils.toFXImage(bufferedImage, null);
            imgview.setImage(javafxImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void navigateitemview(ActionEvent event) {
    }

    @FXML
    private void generateQRCode(ActionEvent event) throws WriterException, IOException {
        Pieces selectedPiece = TablePiece.getSelectionModel().getSelectedItem();
        if (selectedPiece != null) {
            String qrText = selectedPiece.getId_maison();
            createQR(qrText);
        } else {
            alert("Please select a row.");
        }
    }

    private void createQR(String qrText) {
        try {
            String path = System.getProperty("user.home") + File.separatorChar + "Desktop" + File.separatorChar + "qr_code.png";
            BitMatrix matrix = new MultiFormatWriter().encode(qrText, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
            alert("QR Code Created");
            setQRImage(path);
            hl.setVisible(false);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }

    private void setQRImage(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            Image image = new Image(stream);
            qrimg.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void exel(MouseEvent event) {
        WritableWorkbook wworkbook;
        try {
            wworkbook = Workbook.createWorkbook(new File("C:\\Users\\Aziz\\Desktop\\pieceexel.xls"));

            String query = "select id_piece,nom_piece,description,id_maison,prix_depart,cat,img from pieces";
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
            jxl.write.Label label = new jxl.write.Label(0, 2, "A label record");
            wsheet.addCell(label);
            int i = 0;

            int j = 1;
            while (rs.next()) {

                i = 0;

                label = new jxl.write.Label(i++, j, j + "");
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("nom_piece"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("description"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("id_maison"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("prix_depart"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("cat"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("img"));
                wsheet.addCell(label);
                label = new jxl.write.Label(i++, j, rs.getString("id_piece"));
                wsheet.addCell(label);

                j++;
            }

            wworkbook.write();
            wworkbook.close();
            System.out.println("fineshed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    

}
