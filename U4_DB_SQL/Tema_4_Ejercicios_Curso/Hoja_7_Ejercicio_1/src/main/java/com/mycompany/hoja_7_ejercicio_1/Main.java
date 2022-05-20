/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_7_ejercicio_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Connection conexion = Conexion.getInstance().getConnection();
        try
        {     
            conexion.setAutoCommit(false);
            Path fichero = Paths.get("datos_coches.sql");
            
            BufferedReader lector = Files.newBufferedReader(fichero);
            String linea;
            while((linea = lector.readLine()) != null){
                Statement st = conexion.createStatement();
                if(!linea.equals("")){
                    st.executeUpdate(linea);
                }
                st.close();
            }
            conexion.commit();
            System.out.println("Se ha realizado terminado la ejecución del script");
            
        } catch (SQLException ex)
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
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
}
