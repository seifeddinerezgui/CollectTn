/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collecti.services;

/**
 *
 * @author acer
 */
import collecti.entity.Pieces;
import java.util.List;

public interface IPiecesServices  {
    public void ajouter(Pieces p);
    public List<Pieces> getAll();
    public List<Pieces> findById(int id);
    public void update(Pieces p);
    public void delete(Pieces p);
}
