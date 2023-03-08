/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectitn;

import collectitn.services.EnchereService;

/**
 *
 * @author acer
 */
public class COLLECTITN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
             EnchereService Es = new EnchereService();
            System.out.println( Es.afficherEnchere2());

    }
    
}
