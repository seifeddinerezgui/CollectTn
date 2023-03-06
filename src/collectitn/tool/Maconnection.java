/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collecti.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fayechi
 */
public class Maconnection {
       private Connection cnx;
        String url = "jdbc:mysql://localhost:3306/collecti";
        String user = "root";
        String pwd = "";
        public static Maconnection ct;

    private Maconnection() {
        try {
            cnx = DriverManager.getConnection(url,user,pwd);
            System.out.println("Cnx etablie ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static Maconnection getInstance(){
        if(ct ==null)
            ct= new Maconnection();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }

   
        
}
