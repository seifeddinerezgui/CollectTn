/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collectitn.entites;
import java.time.LocalDate;



/**
 *
 * @author AzizB
 */
public class Enchere {
   private int id;
   private Pieces p; 
   private float prix_actuel ;
   private LocalDate date_debut;
   private LocalDate date_fin;
   private int Etat ;
   private int idUser;

    public Enchere() {
    }

    public Enchere(int id) {
        this.id = id;
    }

 
    public Enchere(int id, Pieces p, float prix_actuel , LocalDate date_debut, LocalDate date_fin, int Etat) {
        this.id = id;
        this.p = p;
        this.prix_actuel  = prix_actuel ;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Etat = Etat ;
        
    }

      public Enchere( int id,float prix_actuel , LocalDate date_debut, LocalDate date_fin) {
     this.id = id;
        this.prix_actuel  = prix_actuel ;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
      
    public Enchere(Pieces p, float prix_actuel , LocalDate date_debut, LocalDate date_fin, int Etat) {
        this.p = p;
        this.prix_actuel  = prix_actuel ;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Etat = Etat ;
    }

//    public Enchere(Pieces pi, int prix_depart, Date date_debut, Date date_fin) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   /* public void setIdP(int id){
        this.getP().setId_piece(id);
    }*/

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
    public void setP(Pieces p){
    this.p=p;
    }
    
    public Pieces getP(){
        return p;
    }
    

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public float getPrix_actuel() {
        return prix_actuel;
    }

    public void setPrix_actuel(float prix_actuel) {
        this.prix_actuel = prix_actuel;
    }

    public int getEtat() {
        return Etat;
    }

    public void setEtat(int Etat) {
        this.Etat = Etat;
    }

    @Override
    public String toString() {
        return "Enchere{" + "id=" + id + ", p=" + p + ", prix_actuel =" + prix_actuel  + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

   

   

  

 
   
}
