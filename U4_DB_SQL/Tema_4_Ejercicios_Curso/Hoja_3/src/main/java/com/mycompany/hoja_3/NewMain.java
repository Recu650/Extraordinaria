/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_3;

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
public class NewMain
{

    public static void main(String[] args)
    {
        // CREO LA CONEXION A LA BASE DE DATOS
        Connection conexion;
        try
        {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/concursomusica",
                    "postgres", "PassWd!10");
            System.out.println("Conexion OK con concursomusica.db");

//            aniadirVotos(conexion);
//            insertarCancionesGrupo(conexion);

        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    private static void aniadirVotos(Connection conexion)
    {
        int valor;
        valor = Teclado.introInt("Numero de cancion [0 para salir]:");
        while (valor != 0)
        {

            String sql = "UPDATE canciones "
                    + "SET votos = votos + 1 "
                    + "WHERE numcancion = ?;";

            try ( PreparedStatement consultaPreparada = conexion.prepareStatement(sql))
            {
                consultaPreparada.setInt(1, valor);
                if (consultaPreparada.executeUpdate() > 0)
                {
                    System.out.println("Cancion numero " + valor + " actualizada.");
                } else
                {
                    System.out.println("Error al actualizar.");
                }
                valor = Teclado.introInt("Numero de cancion [0 para salir]:");
            } catch (SQLException ex)
            {
                System.out.println("Error del PreparedStatement");
                System.out.println(ex.toString());
            }
        }
    }

    private static void insertarCancionesGrupo(Connection conexion)
    {
        int numeroGrupo = Teclado.introInt("Numero de grupo:");

        String sql = "INSERT INTO canciones (numcancion, titulo, duracion, numgrupo, votos)"
                + "VALUES (?,?,?,?,0)";

        while (true)
        try ( PreparedStatement consultaPreparada = conexion.prepareStatement(sql))
        {
            consultaPreparada.setInt(1, Teclado.introInt("Numero Cancion:"));
            String titulo = Teclado.introString("Titulo Cancion:");
            if (titulo.length() == 0)
                break;
            consultaPreparada.setString(2, titulo);
            consultaPreparada.setInt(3, Teclado.introInt("Duracion (seg):"));
            consultaPreparada.setInt(4, numeroGrupo);

            consultaPreparada.executeUpdate();
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }
}
