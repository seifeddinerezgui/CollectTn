/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collectitn.services;

import Alert.AlertDialog;
import collectitn.entites.Enchere;
import collectitn.entites.Pieces;
import collectitn.entites.Utilisateur;
import collectitn.tool.Maconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 *
 * @author AzizB
 */
public class EnchereService implements IEnchereService {

    Connection cnx;
    Statement stm;

    public EnchereService() {
        cnx = Maconnection.getInstance().getCnx();
    }
    //   Enchere p = new Enchere(pi,1,date,date1,true);

    @Override
    public void ajouterEnchere(Enchere e) {
        Connection connection = Maconnection.getInstance().getCnx();
         Pieces p = new Pieces();
        try {
            Statement ste = cnx.createStatement();
            String req = "INSERT INTO `enchere` (`idPiece`,`prix_actuel`,`date_debut`,`date_fin`,`Etat`) VALUES ('" + e.getP().getId_piece() + "','" + e.getP().getPrix_depart() + "','" + e.getDate_debut() + "','" + e.getDate_fin() + "','" + e.getEtat() + "');";
            ste.executeUpdate(req);

            List<Pieces> piece = new ArrayList<>();//instansiation
            String req1 = "SELECT * FROM `pieces` WHERE id_piece ='" + e.getP().getId_piece() + "';";
            ResultSet rs = ste.executeQuery(req1);/// exe req
            while (rs.next()) {//donner elet and next 
               
                p.setDescription(rs.getString("description"));
                p.setPrix_depart(rs.getInt("prix_depart"));
                p.setNom_piece(rs.getString("nom_piece"));
                p.setId_maison(rs.getString("Id_maison"));

                piece.add(p);
 
            }
           
           
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

    }
    
     public List<Enchere> Recherche(String nompiece) throws SQLException {

        return afficherEnchere2().stream().filter(a -> a.getP().getNom_piece().equals(nompiece)).collect(Collectors.toList());

    }

    @Override
    public void updateEnchere(Enchere e, int id) {
         PreparedStatement ps;
        String query = "UPDATE enchere SET `prix_actuel`=?,`date_debut`=?,`date_fin`=?,`idUser`=? WHERE `ID`="+id;
        try {
            
            ps = cnx.prepareStatement(query);
         
           // ps.setInt(1, e.getP().getId_piece());
            ps.setFloat(1,e.getPrix_actuel());
            ps.setDate(2, java.sql.Date.valueOf(e.getDate_debut()));
                ps.setDate(3, java.sql.Date.valueOf(e.getDate_fin()));
                 ps.setInt(4, e.getIdUser());
           //  ps.setInt(5, e.getEtat());
            
            
            
            ps.execute();
   

        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        
        
    }
     @Override
    public List<Enchere> afficherEnchere() throws SQLException {
    List<Enchere> list = FXCollections.observableArrayList();
        String requete = "select * from `enchere` WHERE Etat=0";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
         list.add(new Enchere(rs.getInt("id"), rs.getFloat("prix_actuel"), rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin").toLocalDate()));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    
    }
   public List<Enchere> afficherEnchere2() {
        List<Enchere> enchere = new ArrayList<>();//instansiation
        
        try {
            String s = "select * from enchere WHERE Etat=0";// req
            Statement st = cnx.createStatement();// gestionnaire des req
            ResultSet rs;/// exe req
            rs = st.executeQuery(s);

            while (rs.next()) {
Enchere p = new Enchere();
                //donner elet and next
                p.setId(rs.getInt("id"));
                p.setP(getPiece(rs.getInt("idPiece")));
                p.setPrix_actuel(rs.getFloat(3));
                p.setDate_debut(rs.getDate("date_debut").toLocalDate());
                p.setDate_fin(rs.getDate("date_fin").toLocalDate());
                p.setIdUser(rs.getInt("idUser"));
                enchere.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EnchereService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(enchere );
        return enchere;
    }
    @Override
    public void supprimerEnchere(Enchere e, int id) {
 PreparedStatement ps;

        String query = "UPDATE enchere SET Etat=2 WHERE id ='"+id+"'";
  
        try {
            ps = cnx.prepareStatement(query);

//            ps.setInt(1,p.getEtat());
            //ps.setInt(2,r.getId());
            ps.execute();

            System.out.println("suppression de l'enchere");
         } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
     public void terminerEnchere(Enchere e, int id) {
 PreparedStatement ps;

        String query = "UPDATE enchere SET Etat=1 WHERE id ='"+id+"'";
  
        try {
            ps = cnx.prepareStatement(query);

//            ps.setInt(1,p.getEtat());
            //ps.setInt(2,r.getId());
            ps.execute();

            System.out.println("la date de fin est depassee");
         } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    /**
     *
     */
    public void checkDate(){
        
    List<Enchere> liste=afficherEnchere2();
    for (Enchere enchere : liste) {
      if(enchere.getDate_fin().isBefore(java.time.LocalDate.now())){
       
          System.out.println(enchere.getId());
       terminerEnchere(enchere, enchere.getId());
          sendMail(enchere);
        
      }
             
    }
    }
    public Pieces getPiece(int id) {
        Connection connection = Maconnection.getInstance().getCnx();
        Pieces pi = new Pieces();
        try {
            Statement ste = cnx.createStatement();

            String req1 = "SELECT * FROM `pieces` WHERE id_piece ='" + id + "';";
            ResultSet rs = ste.executeQuery(req1);/// exe req
            while (rs.next()) {//donner elet and next 

                pi.setDescription(rs.getString("description"));
                pi.setPrix_depart(rs.getInt("prix_depart"));
                pi.setNom_piece(rs.getString("nom_piece"));
                pi.setId_maison(rs.getString("Id_maison"));
                pi.setId_piece(rs.getInt(1));
                pi.setImg(rs.getString("img"));
            }

        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return pi;
    }

    public static Enchere getEnchereById(int enchereId) {
        List<Enchere> enchereList = new ArrayList<>();
        // loop through the enchereList and return the Enchere object with the specified ID
        for (Enchere enchere : enchereList) {
            if (enchere.getId() == enchereId) {
                return enchere;
            }
        }
        return null; // no Enchere with the specified ID found
    }
    public String getUserMailById(int id){
          Connection connection = Maconnection.getInstance().getCnx();
        String email=""; 
        try {
            Statement ste = cnx.createStatement();

            String req1 = "SELECT `e-mail` FROM `Utilisateur` WHERE id_user ='" + id + "';";
            ResultSet rs = ste.executeQuery(req1);/// exe req
            while (rs.next()) {//donner elet and next 

              email= rs.getString("e-mail");
                
            }

        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return email;
    }
public void sendMail(Enchere e){
    
    String mail = getUserMailById(e.getIdUser());

    String to = mail; // reciver
            String from = "showapp.app@outlook.com";
            final String username = "showapp.app@outlook.com";//your Gmail username 
            final String password = "Ghassen1998";//your Gmail password

            String host = "smtp.outlook.com";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

// Get the Session object
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            try {
                // Create a default MimeMessage object
                Message message = new MimeMessage(session);

                message.setFrom(new InternetAddress(from));

                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));

                // Set Subject
               
                
                message.setSubject("Vous avez gagner");

                // Put the content of your message
                message.setText("l'enchere consernant la piece "+e.getP().getNom_piece()+" est terminee,et vous avez gagner");

// Send message
                Transport.send(message);

                System.out.println("Sent message successfully....");

            } catch (MessagingException me) {
                throw new RuntimeException(me);
            }
}

 public List<Enchere> afficherEnchere3() {
        List<Enchere> enchere = new ArrayList<>();//instansiation
        
        try {
            String s = "select * from enchere WHERE Etat=2";// req
            Statement st = cnx.createStatement();// gestionnaire des req
            ResultSet rs;/// exe req
            rs = st.executeQuery(s);

            while (rs.next()) {
Enchere p = new Enchere();
                //donner elet and next
                p.setId(rs.getInt("id"));
                p.setP(getPiece(rs.getInt("idPiece")));
                p.setPrix_actuel(rs.getFloat(3));
                p.setDate_debut(rs.getDate("date_debut").toLocalDate());
                p.setDate_fin(rs.getDate("date_fin").toLocalDate());
                p.setIdUser(rs.getInt("idUser"));
                enchere.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EnchereService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(enchere );
        return enchere;
    }
}
