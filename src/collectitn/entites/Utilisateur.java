/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collectitn.entites;
import collectitn.entites.Role;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author aziz
 */
public class Utilisateur  {
    
    

    public static Utilisateur valueOf(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String pwd;
    private String profilePic;
    private int numTel;
    private Date dateNaissance;
    private int solde;
    private boolean verifiedEmail;
    private  boolean verifiedPhone;
    private Role role;
    
  
    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String email, String pwd, String profilePic, int numTel, Date dateNaissance, int solde, boolean verifiedEmail, boolean verifiedPhone, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.profilePic = profilePic;
        this.numTel = numTel;
        this.dateNaissance = dateNaissance;
        this.solde = solde;
        this.verifiedEmail = verifiedEmail;
        this.verifiedPhone = verifiedPhone;
        this.role = role;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String pwd, String profilePic, int numTel, Date dateNaissance, int solde, boolean verifiedEmail, boolean verifiedPhone, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.profilePic = profilePic;
        this.numTel = numTel;
        this.dateNaissance = dateNaissance;
        this.solde = solde;
        this.verifiedEmail = verifiedEmail;
        this.verifiedPhone = verifiedPhone;
        this.role = role;
    }

    public Utilisateur(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur(String username, String email, String role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur(int id, String newUsername, String newEmail, String newRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur(String nom, String prenom, String email, String pwd, String profilePic, int numTel, Date dateNaissance, int solde, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur(String nom, String prenom, String email, String pwd, String profilePic, int numTel, Date dateNaissance, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur(String nom, String prenom, String email, String pwd, int numTel, Date dateNaissance, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }

    public void setVerifiedEmail(boolean verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }

    public boolean isVerifiedPhone() {
        return verifiedPhone;
    }

    public void setVerifiedPhone(boolean verifiedPhone) {
        this.verifiedPhone = verifiedPhone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", pwd=" + pwd + ", profilePic=" + profilePic + ", numTel=" + numTel + ", dateNaissance=" + dateNaissance + ", solde=" + solde + ", verifiedEmail=" + verifiedEmail + ", verifiedPhone=" + verifiedPhone + ", role=" + role + '}';
    }
}

