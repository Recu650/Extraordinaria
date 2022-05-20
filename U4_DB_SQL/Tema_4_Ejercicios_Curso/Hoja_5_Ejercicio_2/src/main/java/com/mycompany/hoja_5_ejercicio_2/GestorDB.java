package com.mycompany.hoja_5_ejercicio_2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */

public class GestorDB
{
    private Connection conexion;

    public GestorDB()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1/concursomusicacompleto",
                    "postgres", "PassWd!10");
        } catch (ClassNotFoundException ex)
        {
            System.out.println(ex.toString());
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    public List<Voto> listadoCincoVotos()
    {
        List<Voto> votos = new ArrayList();
        String sql = "SELECT usuario, fecha, cancion FROM votos ORDER BY fecha DESC LIMIT 5;";

        try
        {
            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);

            while (result.next())
            {
                Voto voto = new Voto();
                
                voto.setUsuario(result.getString("usuario"));
                voto.setFecha(result.getDate("fecha"));
                
                Cancion cancion = new Cancion();
                cancion.setNumcancion(result.getInt("cancion"));
                voto.setCancion(cancion);
                
                votos.add(voto);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return votos;
    }

    public void ModificarAliasComponentes()
    {

        String sql = "SELECT usuario, fecha, cancion FROM votos "
                + "INNER JOIN canciones ON votos.cancion = canciones.numcancion "
                + "INNER JOIN usuarios ON votos.usuario = usuarios.user "
                + "ORDER BY fecha DESC LIMIT 5;"; 

        try
        {
            Statement st = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = st.executeQuery(sql);

            while (result.next())
            {
                System.out.println(result.getString("usuario") + " - " +
                        result.getDate("fecha") + " - " + 
                        result.getInt("cancion"));
                        
                boolean modificar = Teclado.introBoolean("Modificar Usuario[S] o Eliminar voto[N]");

                if (modificar == true)
                {
                    String nuevoUsu = Teclado.introString("Nuevo Usuario:");
                    result.updateString("user", nuevoUsu);
                    result.updateString("usuario", nuevoUsu);
                    result.updateRow();
                }else {
                    result.deleteRow();
                }
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }

    }
}
