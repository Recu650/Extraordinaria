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

    public GestorDB() {
        this.conexion = Conexion.getInstance().getConnection();
    }

    public List<Componente> listadoComponentes(int idGrupo) {
        List<Componente> componentes = new ArrayList();
        try {
            String sql = "SELECT * FROM componentes WHERE grupo = '" + idGrupo + "' ORDER BY idcomp;";
            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
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
        try {
            String nuevoAlias = Teclado.introString("Nuevo Alias: ");
            String sql = "UPDATE componentes "
                    + "SET alias = " + nuevoAlias + " WHERE idcomp = " + idcomp + ";";

            Statement st = conexion.createStatement();
            int resultUpdate = st.executeUpdate(sql);

            if (resultUpdate > 0)
                modificado = true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return modificado;
    }

    public List<Voto> cincoUltimosVotos() {
        List<Voto> votos = new ArrayList();

        try {
            String sqlVoto = "SELECT usuario, fecha, cancion FROM votos ORDER BY fecha DESC LIMIT 5;";
            Statement stVoto = this.conexion.createStatement();
            ResultSet resultVoto = stVoto.executeQuery(sqlVoto);

            while (resultVoto.next()) {
                Voto voto = new Voto();
                Cancion cancion = new Cancion();
                Usuario usuario = new Usuario();
               
                voto.setFecha(resultVoto.getDate("fecha"));

                String sqlCancion = "SELECT * FROM canciones WHERE id = " + resultVoto.getInt("cancion") + ";";
                Statement stCancion = this.conexion.createStatement();
                ResultSet resultCancion = stCancion.executeQuery(sqlCancion);
                
                while(resultCancion.next()){
                    cancion.setNumcancion(resultCancion.getInt("id"));
                    cancion.setDuracion(resultCancion.getTime("duracion"));
                    cancion.setTitulo(resultCancion.getString("titulo"));
                    cancion.setTotal_votos(resultCancion.getInt("total_votos"));
                    
                    voto.setCancion(cancion);
                }
                
                String sqlUsuario = "SELECT * FROM usuarios WHERE user = '" + resultVoto.getString("usuario") + "';";
                Statement stUsuario = this.conexion.createStatement();
                ResultSet resultUsuario = stUsuario.executeQuery(sqlUsuario);
                
                while(resultUsuario.next()){
                    usuario.setUsuario(resultUsuario.getString("user"));
                    usuario.setPassword(resultUsuario.getString("contraseña"));
                    usuario.setNombre(resultUsuario.getString("nombre"));
                    usuario.setApellidos(resultUsuario.getString("apellidos"));
                    usuario.setFechanacimiento(resultUsuario.getDate("fechanac"));
                    
                    voto.setUsuario(usuario);
                }

                votos.add(voto);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return votos;
    }
    
    public void ModificarUsuario(Usuario usuarioNuevo String)
    
}
