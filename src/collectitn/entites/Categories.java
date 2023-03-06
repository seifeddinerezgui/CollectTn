/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.entites;

import java.util.List;

/**
 *
 * @author acer
 */
public class Categories {
    private int id_cat;
    private String nom_cat;
    /*affiche list per categorie*/
    private List<Pieces> listPiece ;
    
    public Categories(){
    }

    
    public Categories(int id_cat ) {
        this.id_cat = id_cat;
    }    
    public Categories(int id_cat, String nom_cat) {
        this.id_cat = id_cat;
        this.nom_cat = nom_cat;
    }
    
    public int getIdCat() {
        return id_cat;
    }
    
    public void setIdCat(int id_cat) {
        this.id_cat = id_cat;
    }
    
    public String getNomCat() {
        return nom_cat;
    }
    
    public void setNomCat(String nom_cat) {
        this.nom_cat = nom_cat;
    }
    @Override
    public String toString() {
        return "Categories{" + "id_cat=" + id_cat + ", nom_cat=" + nom_cat + '}';
    }
}
