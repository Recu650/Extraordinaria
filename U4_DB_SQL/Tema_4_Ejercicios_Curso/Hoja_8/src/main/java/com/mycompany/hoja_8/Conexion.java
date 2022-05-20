package com.mycompany.hoja_8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Conexion {
    private static Conexion instance;
    private Connection connection;
    private String url = "jdbc:mariadb://localhost:3310";
    private String username = "root";
    private String password = "root";
    
    private Conexion(){
        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
                    } catch (ClassNotFoundException ex)
        {
            System.out.println(ex.toString());
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public static Conexion getInstance(){
        try{
            if(instance == null){
                instance = new Conexion();
            }else if(instance.getConnection().isClosed()){
                instance = new Conexion();
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return instance;
    }

}
