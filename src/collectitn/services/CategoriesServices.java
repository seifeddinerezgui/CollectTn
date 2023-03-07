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
import java.util.logging.Level;
import java.util.logging.Logger;
import collectitn.entites.Pieces;
import collectitn.entites.Categories;
import collectitn.tool.Maconnection;

/**
 *
 * @author acer
 */

   

public class CategoriesServices implements ICategoriesServices {

    Connection cnx;

    public CategoriesServices() {
        cnx = Maconnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Categories c) {
try {
            String sql = "insert into categories(id_cat,nom_cat)"
                    + "values (?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, c.getIdCat());
            ste.setString(2, c.getNomCat());
            ste.executeUpdate();
            System.out.println("categorie ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Categories> getAll() {
   List<Categories> Categories = new ArrayList<>();
        try {
            String sql = "select * from categories";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Categories p = new Categories(s.getInt(1),
                        s.getString("nom_cat"));
                Categories.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Categories;    }

    @Override
    public List<Categories> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void  modifierCategorie(String nom_cat,Categories p){
 String sql = "update categories set nom_cat=? where id_cat=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom_cat);
            ste.setInt(2,p.getIdCat());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCategorie(Categories p) {
        String sql = "delete from categories where id_cat=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getIdCat());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}

}