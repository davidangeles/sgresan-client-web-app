/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Joel
 */
public class AccesoPrepareStatement {
    Connection con;
    public AccesoPrepareStatement()
    {
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sgresan","root","1234");
            System.out.println("exito");
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println("ffallo");
        }
        catch(SQLException sql)
        {
            System.out.println("fallo");
        }
        
    }
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
