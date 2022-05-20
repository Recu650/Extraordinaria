/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_3_ejercicio_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Main_H3E1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //CREO LA CONEXION A LA BASE DE DATOS-----------------------------------------------------//
        Connection conexion;
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/concursomusica",
                    "postgres", "PassWd!10");
            System.out.println("Conexion OK con concursomusica.db");

            int select;
            do {
                System.out.println("|----------MENU----------|");
                System.out.println("| 1.Add Votos            |");
                System.out.println("| 2.Add Canciones Grupo  |");
                System.out.println("| 0.Salir                |");
                System.out.println("|------------------------|");
                System.out.println("| Selecciona opcion:     |");
                select = Teclado.introInt("");
                System.out.println("|------------------------|");
                switch (select) {
                    case 1:
                        aniadirVotos(conexion);
                        break;
                    case 2:
                        insertarCanciones(conexion);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } while (select != 0);
            
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        //----------------------------------------------------------------------------------------//
       
    }

    public static void aniadirVotos(Connection conexion) {
        int cancion = Teclado.introInt("Numero de Cancion [0 para salir]:");
        while (cancion != 0) {
            String sql = "UPDATE canciones "
                    + "SET votos = votos + 1 "
                    + "WHERE numcancion = ?;";
            try {
                PreparedStatement consultaPreparada = conexion.prepareStatement(sql);
                consultaPreparada.setInt(1, cancion);

                if (consultaPreparada.executeUpdate() > 0) {
                    System.out.println("Cancion numero " + cancion + " actualizada.");
                } else {
                    System.out.println("Error al actualizar.");
                }
                cancion = Teclado.introInt("Numero de Cancion [0 para salir]:");
            } catch (SQLException ex) {
                System.out.println("Error del PreparedStatement");
                System.out.println(ex.toString());
            }
        }
    }
    
    public static void insertarCanciones(Connection conexion){
        int numGrupo = Teclado.introInt("Numero del grupo:");
        
        String sql = "INSERT INTO canciones (numcancion, titulo, duracion, grupo, votos) "
                + "VALUES (?,?,?,?,0);";
        
        while(true){
            try{
                PreparedStatement consultaPreparada = conexion.prepareStatement(sql);
                
                consultaPreparada.setInt(1, Teclado.introInt("Numero Cancion: "));
                
                String titulo = Teclado.introString("Titulo Cancion[Intro en blanco para salir]: ");
                if (titulo.length() == 0) break;
                
                consultaPreparada.setString(2, titulo);
                consultaPreparada.setInt(3, Teclado.introInt("Duracion [seg]: "));
                consultaPreparada.setInt(4, numGrupo);
                
                consultaPreparada.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

}
