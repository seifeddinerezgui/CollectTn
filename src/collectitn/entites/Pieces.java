/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collecti.entity;
import collecti.entity.Categories;
import java.sql.Blob;

/**
 *
 * @author acer
 */
public class Pieces {
    private int id_piece;
    private String nom_piece;
    private String description;
    private String id_maison;
    private int prix_depart;
    private int cat;
    private String img ;
    private int etat;

    public Pieces(int id_piece, String nom_piece, String description, String id_maison, int prix_depart, int cat, String img, int etat) {
        this.id_piece = id_piece;
        this.nom_piece = nom_piece;
        this.description = description;
        this.id_maison = id_maison;
        this.prix_depart = prix_depart;
        this.cat = cat;
        this.img = img;
        this.etat = etat;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Pieces() {
    }
 public Pieces(String nom_piece, String description, String id_maison, int prix_depart, int cat ,String img) {
        this.nom_piece = nom_piece;
        this.description = description;
        this.id_maison = id_maison;
        this.prix_depart = prix_depart;
        this.cat = cat;
        this.img = img ;
    }
    public Pieces(int id_piece, String nom_piece, String description, String id_maison, int prix_depart, int cat,String img) {
        this.id_piece = id_piece;
        this.nom_piece = nom_piece;
        this.description = description;
        this.id_maison = id_maison;
        this.prix_depart = prix_depart;
        this.cat = cat;
        this.img = img ;
    }

    public int getId_piece() {
        return id_piece;
    }

    public void setId_piece(int id_piece) {
        this.id_piece = id_piece;
    }

    public String getNom_piece() {
        return nom_piece;
    }

    public void setNom_piece(String nom_piece) {
        this.nom_piece = nom_piece;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_maison() {
        return id_maison;
    }

    public void setId_maison(String id_maison) {
        this.id_maison = id_maison;
    }

    public int getPrix_depart() {
        return prix_depart;
    }

    public void setPrix_depart(int prix_depart) {
        this.prix_depart = prix_depart;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Pieces{" +
                "id_piece=" + id_piece +
                ", nom_piece='" + nom_piece + '\'' +
                ", description='" + description + '\'' +
                ", id_maison=" + id_maison +
                ", prix_depart=" + prix_depart +
                ", cat=" + cat +
                ",img=" + img +
                '}';
    }
    
}

