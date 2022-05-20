package util;

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
    private String url = "jdbc:postgresql://localhost:5432/concursomusicacompleto";
    private String username = "postgres";
    private String password = "PassWd!10";
    
    private Conexion() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver " + ex.toString());
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la DB " + ex.toString());
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }
    
    public static Conexion getInstance() {
        try {
            if(instance == null) instance = new Conexion();
            else if(instance.getConnection().isClosed()) instance = new Conexion();
        } catch (SQLException ex) {
            System.out.println("Error al retunear la instancia " + ex.toString());
        }
        return instance;
    }
}
