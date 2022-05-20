package com.mycompany.hoja_5_ejercicio_1;

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

    public List<Componente> listadoComponentes(int idGrupo)
    {
        List<Componente> componentes = new ArrayList();
        String sql = "SELECT * FROM componentes WHERE grupo = '" + idGrupo + "' ORDER BY idcomp;";

        try
        {
            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);

            while (result.next())
            {
                Componente componente = new Componente();

                componente.setIdcomp(result.getBigDecimal("idcomp"));
                componente.setNombre(result.getString("nombre"));
                componente.setApellido(result.getString("apellido"));
                componente.setAlias(result.getString("alias"));

                Grupo grupo = new Grupo();
                grupo.setCodgrupo(BigDecimal.valueOf(result.getInt("grupo")));
                componente.setGrupo(grupo);

                componentes.add(componente);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return componentes;
    }

    public void ModificarAliasComponentes(int idGrupo)
    {

        String sql = "SELECT * FROM componentes WHERE grupo = '" + idGrupo + "';";

        try
        {
            Statement st = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = st.executeQuery(sql);

            while (result.next())
            {

                System.out.println(result.getBigDecimal("idcomp") + " - "
                        + result.getString("nombre") + " - "
                        + result.getString("apellido") + " - "
                        + result.getString("alias") + " - "
                        + result.getInt("grupo"));

                boolean modificar = Teclado.introBoolean("Modificar Alias?");

                if (modificar == true)
                {
                    String nuevoAlias = Teclado.introString("Nuevo Alias:");
                    result.updateString("alias", nuevoAlias);
                    result.updateRow();
                }
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }

    }
}
