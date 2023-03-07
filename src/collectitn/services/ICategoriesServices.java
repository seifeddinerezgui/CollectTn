/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn.services;

import collectitn.entites.Categories;
import java.util.List;

/**
 *
 * @author acer
 */
public interface ICategoriesServices {
     public void ajouter(Categories c);
    public List<Categories> getAll();
    public List<Categories> findById(int id);
    public void modifierCategorie(String nom_cat,Categories p);
public void supprimerCategorie(Categories p);
        }
