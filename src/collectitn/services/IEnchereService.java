/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package collectitn.services;

import collectitn.entites.Enchere;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author AzizB
 */
public interface IEnchereService {
    void ajouterEnchere(Enchere e);
    void updateEnchere(Enchere e,int id);
    List<Enchere> afficherEnchere()throws SQLException ;
    void supprimerEnchere(Enchere e, int id);
}
