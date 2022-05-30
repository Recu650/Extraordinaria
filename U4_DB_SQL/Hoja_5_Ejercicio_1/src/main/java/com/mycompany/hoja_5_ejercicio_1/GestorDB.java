package com.mycompany.hoja_5_ejercicio_1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

                while (resultCancion.next()) {
                    cancion.setNumcancion(resultCancion.getInt("id"));
                    cancion.setDuracion(resultCancion.getTime("duracion"));
                    cancion.setTitulo(resultCancion.getString("titulo"));
                    cancion.setTotal_votos(resultCancion.getInt("total_votos"));

                    voto.setCancion(cancion);
                }

                String sqlUsuario = "SELECT * FROM usuarios WHERE user = '" + resultVoto.getString("usuario") + "';";
                Statement stUsuario = this.conexion.createStatement();
                ResultSet resultUsuario = stUsuario.executeQuery(sqlUsuario);

                while (resultUsuario.next()) {
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

    public boolean ModificarUsuario(Usuario usuarioNuevo, String user) {
        boolean modificado = false;
        try {
            String sql = "UPDATE usuarios SET \"user\" = ?, contraseña = md5(?), "
                    + "nombre = ?, apellidos = ?, fechanac = ? "
                    + "WHERE user = ?;";
            PreparedStatement pst = this.conexion.prepareStatement(sql);

            pst.setString(1, usuarioNuevo.getUsuario());
            pst.setString(2, usuarioNuevo.getPassword());
            pst.setString(3, usuarioNuevo.getNombre());
            pst.setString(4, usuarioNuevo.getApellidos());
            pst.setDate(5, (Date) usuarioNuevo.getFechanacimiento());
            pst.setString(6, user);

            int n = pst.executeUpdate();
            if (n == 1) {
                System.out.println("Modificado Correctamente");
                modificado = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return modificado;
    }

    public boolean EliminarVoto(Voto voto) {
        boolean eliminado = false;
        try {
            String sqlVotos = "DELETE FROM votos USING votos "
                    + "WHERE votos.usuario = ? "
                    + "AND votos.fecha = ? "
                    + "AND votos.cancion = ?;";
            PreparedStatement pst = this.conexion.prepareStatement(sqlVotos);

            pst.setString(1, voto.getUsuario().getUsuario());
            pst.setDate(2, (Date) voto.getFecha());
            pst.setInt(3, voto.getCancion().getNumcancion());

            int n = pst.executeUpdate();
            if (n == 1) {
                System.out.println("Eliminado Correctamente");
                eliminado = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return eliminado;
    }

    public List<Voto> votosUsuario(String user) {
        List<Voto> votos = new ArrayList();
        try {
            String sql = "SELECT * FROM votos WHERE usuario = '" + user + "';";
            Statement st = this.conexion.createStatement();
            ResultSet resultVoto = st.executeQuery(sql);
            
            while(resultVoto.next()){
                Voto voto = new Voto();
                Cancion cancion = new Cancion();
                Usuario usuario = new Usuario();

                voto.setFecha(resultVoto.getDate("fecha"));

                String sqlCancion = "SELECT * FROM canciones WHERE id = " + resultVoto.getInt("cancion") + ";";
                Statement stCancion = this.conexion.createStatement();
                ResultSet resultCancion = stCancion.executeQuery(sqlCancion);

                while (resultCancion.next()) {
                    cancion.setNumcancion(resultCancion.getInt("id"));
                    cancion.setDuracion(resultCancion.getTime("duracion"));
                    cancion.setTitulo(resultCancion.getString("titulo"));
                    cancion.setTotal_votos(resultCancion.getInt("total_votos"));

                    voto.setCancion(cancion);
                }

                String sqlUsuario = "SELECT * FROM usuarios WHERE user = '" + resultVoto.getString("usuario") + "';";
                Statement stUsuario = this.conexion.createStatement();
                ResultSet resultUsuario = stUsuario.executeQuery(sqlUsuario);

                while (resultUsuario.next()) {
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
    
    public boolean rectificarVoto(Voto voto) {
        boolean rectificado = false;
        try{
            String sql = "UPDATE votos SET fechanac = ? WHERE usuario = ? AND fechanac = ? AND cancion = ?;";
            PreparedStatement pst = this.conexion.prepareStatement(sql);
            
            pst.setDate(1, Date.valueOf(LocalDate.now()));
            pst.setString(2, voto.getUsuario().getUsuario());
            pst.setDate(3, (Date) voto.getFecha());
            pst.setInt(4, voto.getCancion().getNumcancion());
            
            int n = pst.executeUpdate();
            if (n == 1) {
                System.out.println("Insertado Correctamente");
                rectificado = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return rectificado;
    }

}
