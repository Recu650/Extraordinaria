package com.mycompany.hoja_8_ejercicio_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ivan
 */
public class Conexion
{
    private static Conexion instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/concursomusicacompleto";
    private String username = "postgres";
    private String password = "PassWd!10";

    private Conexion() 
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Error al cargar del driver de la base de datos: " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            System.err.println("Error al conectarse a la base de datos");
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    public static Conexion getInstance()
    {
        try
        {
            if (instance == null)
            {
                instance = new Conexion();
            }
            else if (instance.getConnection().isClosed())
            {
                instance = new Conexion();
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Error al devolver la instancia de la base de datos");
        }
        return instance;
    }
}
