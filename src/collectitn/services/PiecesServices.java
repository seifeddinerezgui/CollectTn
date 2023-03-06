/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import collecti.tools.Maconnection;
import collectitn.entites.Pieces;
import java.awt.HeadlessException;
import java.io.File;
import java.nio.file.Path;
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class PiecesServices implements IPiecesServices {

    Connection cnx;

    public PiecesServices() {
        cnx = Maconnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Pieces p) {
        System.out.println("test" + p.toString());
        try {

            String sql = "insert into pieces(nom_piece, description, id_maison, prix_depart,cat ,img) values ( ?, ?, ?, ?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(sql);

            ps.setString(1, p.getNom_piece());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getId_maison());
            ps.setInt(4, p.getPrix_depart());
            ps.setInt(5, p.getCat());
            ps.setString(6,p.getImg());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "piece ajouter");
            System.out.println("La pièce " + p.getNom_piece() + " a été ajoutée avec succès !");
        } catch (HeadlessException | SQLException ex) {
            //System.out.println(ex.getMessage());
            Logger.getLogger(PiecesServices.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null, "echec");
        }
    }

    @Override
    public List<Pieces> getAll() {
        List<Pieces> pieces = new ArrayList<>();
        try {
            String sql = "select * from pieces";
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Pieces p = new Pieces(
                        rs.getInt("id_piece"),
                        rs.getString("nom_piece"),
                        rs.getString("description"),
                        rs.getString("id_maison"),
                        rs.getInt("prix_depart"),
                        rs.getInt("cat"),
                        rs.getString("img")
                //new Categories(rs.getInt("cat"))
                );
                pieces.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pieces;
    }

    @Override
    public List<Pieces> findById(int id) {
        List<Pieces> pieces = new ArrayList<>();
        try {
            String sql = "select * from pieces where id_piece=?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pieces p = new Pieces(
                        rs.getInt("id_piece"),
                        rs.getString("nom_piece"),
                        rs.getString("description"),
                        rs.getString("id_maison"),
                        rs.getInt("prix_depart"),
                        rs.getInt("cat"),
                        rs.getString("img")
                //new Categories(rs.getInt("id_cat"), rs.getString("nom_cat"))
                );
                pieces.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pieces;
    }
@Override
    public void update( Pieces p) {
        String sql = "update pieces set nom_piece=?, description=?, id_maison=?, prix_depart=?, cat=?,img=? where id_piece=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getNom_piece());
            ste.setString(2, p.getDescription());
            ste.setString(3, p.getId_maison());
            ste.setInt(4, p.getPrix_depart());
            ste.setInt(5, p.getCat());
            ste.setString(6, p.getImg());
            ste.executeUpdate();
            //System.out.println("La pièce " + p.getNom_piece() + " a été modifiée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Supprimer(int id) {
        try {
            String sql = "DELETE FROM pieces WHERE id_piece="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("Piece Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void delete(Pieces p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
