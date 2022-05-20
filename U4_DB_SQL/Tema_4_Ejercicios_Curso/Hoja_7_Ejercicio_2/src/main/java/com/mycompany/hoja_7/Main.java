/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author usuario
 */

//Ejercicio_2
public class Main
{
    public static void main(String[] args)
    {
        // TODO code application logic here
        Path fichero = Paths.get("datos_coches.sql");
        ejecutarScript(fichero);
    }
    public static void ejecutarScript(Path fichero)
    {
        String script = leerScript(fichero);
        System.out.println(script);
        Connection conexion = Conexion.getInstance().getConnection();
        try
        {
            conexion.setAutoCommit(false);
            
            //Ejecutar el script
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(script);

            //confirmar la transancción
            conexion.commit();
            System.out.println("Se ha realizado terminado la ejecución del script");
            sentencia.close();
        }
        catch (SQLException ex)
        {
            try
            {
                // Algo ha fallado en la ejecución del script, se anula la transacción
                conexion.rollback();
            }
            catch (SQLException ex1)
            {
                System.err.println("Error al hacer el rollback");
            }
            System.err.println("Error al ejecutar el script " + ex.getMessage());
        }
    }
    private static String leerScript(Path fichero)
    {
        String contenido = "";
        try
        {
            contenido = new String(Files.readAllBytes(fichero));
        }
        catch (IOException ex)
        {
            System.err.println("Error al leer el fichero " + ex.getMessage());
        }
        return contenido;
    }
}
