package com.mycompany.hoja_5_ejercicio_1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GestorDB {
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
    
    public List<Componente> listadoComponentes(int idGrupo) {
        List<Componente> componentes = new ArrayList();
        try{
            String sql = "SELECT * FROM componentes WHERE grupo = '" + idGrupo + "' ORDER BY idcomp;";
            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while(result.next()){
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
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return componentes;
    }
    
    public boolean modificarAlias(BigDecimal idcomp) {
        boolean modificado = false;
        try{
            String nuevoAlias = Teclado.introString("Nuevo Alias: ");
            String sql = "UPDATE componentes "
                    + "SET alias = " + nuevoAlias + " WHERE idcomp = " + idcomp + ";";
            
            Statement st = conexion.createStatement();
            int resultUpdate = st.executeUpdate(sql);
            
            if(resultUpdate > 0) modificado = true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return modificado;
    }
}
