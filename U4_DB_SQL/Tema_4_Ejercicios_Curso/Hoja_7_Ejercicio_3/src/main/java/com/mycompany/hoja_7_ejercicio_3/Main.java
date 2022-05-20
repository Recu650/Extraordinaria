/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_7_ejercicio_3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
        // TODO code application logic here
        int ultimaJornada = obtenerUltimaJornada();
        ejecutarPrecedimiento(ultimaJornada);
        System.out.println("Se ha generado la jornada " + (ultimaJornada+1));
        mostrarPartidosJornada(ultimaJornada+1);
    }

    private static int obtenerUltimaJornada()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void ejecutarPrecedimiento(int ultimaJornada)
    {
        try
        {
            Connection conexion = Conexion.getInstance().getConnection();
            String sql="{call siguienteJornada(?)}";
            CallableStatement sentencia=conexion.prepareCall(sql);
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    private static void mostrarPartidosJornada(int i)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
