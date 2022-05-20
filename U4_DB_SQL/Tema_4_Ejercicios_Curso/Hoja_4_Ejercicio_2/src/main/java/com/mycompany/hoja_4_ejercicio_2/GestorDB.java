package com.mycompany.hoja_4_ejercicio_2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;

/**
 *
 * @author usuario
 */

public class GestorDB
{
    private Connection conexion;
    public GestorDB()
    {
        try{
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1/concursomusicacompleto",
                    "postgres", "PassWd!10");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public List<Grupo> listadoGrupos()
    {
        List<Grupo> grupos = new ArrayList();
        String sql = "SELECT * "
                + "FROM grupos ORDER BY codgrupo ASC";

        try
        {
            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);

            while (result.next())
            {
                Grupo grupo = new Grupo();

                grupo.setCodgrupo(result.getBigDecimal("codgrupo"));
                grupo.setNombre(result.getString("nombre"));
                grupo.setLocalidad(result.getString("localidad"));
                grupo.setEstilo(result.getString("estilo"));
                grupo.setAnnograb(result.getInt("annograb"));
                grupo.setCompania(result.getString("compania"));
                grupo.setEsgrupo(result.getBoolean("esgrupo"));
                grupo.setFechaestreno(result.getDate("fechaestreno"));

                grupos.add(grupo);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return grupos;
    }

    public List<Grupo> listadoGruposCanciones() {
        List<Grupo> grupos = new ArrayList();
        try {
            String sql_grupo = "SELECT * FROM grupos ORDER BY nombre ASC";
            Statement st = conexion.createStatement();
            ResultSet resultGrupo = st.executeQuery(sql_grupo);
            while (resultGrupo.next()) {
                int codGrupo = resultGrupo.getInt("codgrupo");

                Grupo grupo = new Grupo();

                grupo.setCodgrupo(resultGrupo.getBigDecimal("codgrupo"));
                grupo.setNombre(resultGrupo.getString("nombre"));
                grupo.setLocalidad(resultGrupo.getString("localidad"));
                grupo.setEstilo(resultGrupo.getString("estilo"));
                grupo.setAnnograb(resultGrupo.getInt("annograb"));
                grupo.setCompania(resultGrupo.getString("compania"));
                grupo.setEsgrupo(resultGrupo.getBoolean("esgrupo"));
                grupo.setFechaestreno(resultGrupo.getDate("fechaestreno"));

                String sql_cancion = "SELECT * FROM canciones "
                        + "WHERE grupo = " + codGrupo + ";";
                Statement st2 = conexion.createStatement();
                ResultSet resultCancion = st2.executeQuery(sql_cancion);

                while (resultCancion.next()) {
                    Cancion cancion = new Cancion();

                    cancion.setDuracion(resultCancion.getTime("duracion"));
                    cancion.setGrupo(resultCancion.getInt("grupo"));
                    cancion.setNumcancion(resultCancion.getInt("numcancion"));
                    cancion.setTitulo(resultCancion.getString("titulo"));
                    cancion.setTotal_votos(resultCancion.getInt("total_votos"));

                    grupo.addCancion(cancion);
                }
                grupos.add(grupo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return grupos;
    }

//    Lo hacemos con el metodo anterior haciendo un grupo.getCanciones().size
//    public void numCancionesGrupo()
//    {
//        try
//        {
//            String sqlGrupo = "SELECT codgrupo, nombre FROM grupos ORDER BY codgrupo ASC";
//            Statement st = conexion.createStatement();
//            ResultSet resultGrupo = st.executeQuery(sqlGrupo);
//
//            while (resultGrupo.next())
//            {
//                String nombreGrupo = resultGrupo.getString("nombre");
//                int codGrupo = resultGrupo.getInt("codgrupo");
//                System.out.print(nombreGrupo + " ");
//
//                String sqlCancion = "SELECT count(*) FROM canciones WHERE grupo =" + codGrupo + ";";
//                Statement st2 = conexion.createStatement();
//                ResultSet resultCancion = st2.executeQuery(sqlCancion);
//
//                while (resultCancion.next())
//                {
//                    System.out.println("tiene " + resultCancion.getInt(1) + " canciones");
//                }
//            }
//        } catch (SQLException ex)
//        {
//            System.out.println(ex.toString());
//        }
//    }
    
//    Se hace con el anterior y un filtro if en el main
//    No, Es mas lento, mejor filtrar en la consulta.
    
    public List<Cancion> cancionesUnGrupo(String grupo)
    {
        List<Cancion> canciones = new ArrayList();
        try
        {
            String sql = "SELECT numcancion, duracion, titulo FROM canciones "
                    + "INNER JOIN grupos ON canciones.grupo = grupos.codgrupo "
                    + "WHERE grupos.nombre = '" + grupo + "';";

            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);

            while (result.next())
            {
                int numCancion = result.getInt("numcancion");
                Time duracion = result.getTime("duracion");
                String titulo = result.getString("titulo");

                Cancion cancion = new Cancion();

                cancion.setNumcancion(numCancion);
                cancion.setDuracion(duracion);
                cancion.setTitulo(titulo);

                canciones.add(cancion);
            }

        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return canciones;
    }

    public List<Cancion> cancionesMasVotadas()
    {
        List<Cancion> canciones = new ArrayList();
        try
        {
            String sql = "SELECT canciones.total_votos, canciones.titulo, grupos.nombre FROM canciones "
                    + "INNER JOIN grupos ON canciones.grupo = grupos.codgrupo "
                    + "ORDER BY total_votos DESC";

            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);

            while (result.next())
            {
                Cancion cancion = new Cancion();
                Grupo grupo = new Grupo();

                cancion.setTotal_votos(result.getInt("total_votos"));
                cancion.setTitulo(result.getString("titulo"));
                grupo.setNombre(result.getString("nombre"));
                cancion.setGrupoObjeto(grupo);

                canciones.add(cancion);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return canciones;
    }
//    Se usa el metodo del apartado 2 que saca grupos y canciones
//    y se filtra por el size de la lista del canciones de cada grupo.

//    public void gruposSinCancion() {
//        try{
//            List<Grupo> grupos = new ArrayList();
//            String sql = "SELECT * FROM grupos "
//                    + "INNER JOIN canciones ON canciones.grupo = grupos.codgrupo "
//                    + "WHERE "; //Completar la condicion
//            Statement st = conexion.createStatement();
//            ResultSet result = st.executeQuery(sql);
//            
//            while(result.next()) {
//                Grupo grupo = new Grupo();
//            }
//        }catch(SQLException ex){
//            System.out.println(ex.toString());
//        }
//    }
    
    public List<Voto> votosMasRecientes()
    {
        List<Voto> votos = new ArrayList();
        try
        {
            String sql = "SELECT canciones.titulo, votos.fecha, grupos.nombre FROM canciones "
                    + "INNER JOIN votos ON canciones.numcancion = votos.cancion "
                    + "INNER JOIN grupos ON canciones.grupo = grupos.codgrupo "
                    + "ORDER BY votos.fecha DESC LIMIT 5";
            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);

            while (result.next())
            {
                String nombreGrupo = result.getString("nombre");
                String titulo = result.getString("titulo");
                Date fecha = result.getDate("fecha");

                Cancion cancion = new Cancion();
                Grupo grupo = new Grupo();
                Voto voto = new Voto();

                voto.setFecha(fecha);
                cancion.setTitulo(titulo);
                grupo.setNombre(nombreGrupo);

                cancion.setGrupoObjeto(grupo);
                voto.setCancion(cancion);
                //Voto[Cancion(Grupo)]

                votos.add(voto);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return votos;
    }

    public int eliminarCancionesGrupo(String nombreGrupo)
    {
        int resultCanciones = 0;
        try
        {
            String sqlVotos = "DELETE from votos "
                    + "USING canciones, grupos "
                    + "WHERE votos.cancion = canciones.numcancion "
                    + "AND canciones.grupo = grupos.codgrupo "
                    + "AND grupos.nombre = '" + nombreGrupo + "';";
            
            String sqlCanciones = "DELETE FROM canciones "
                    + "WHERE grupo IN "
                    + "(SELECT codgrupo FROM grupos WHERE nombre = '" + nombreGrupo + "');";
            
            //Primero hay que borrar los votos de las canciones que queremos borrar
            //Porque el DELETE no está en cascada
            
            Statement stVotos = conexion.createStatement();
            int resultVotos = stVotos.executeUpdate(sqlVotos); 
            //Result es el numero de filas afectadas por Update
            
            //Una vez borrados los votos ya podemos borrar las canciones.
            
            Statement stCanciones = conexion.createStatement();
            resultCanciones = stCanciones.executeUpdate(sqlCanciones); 
            //Result es el numero de filas afectadas por Update
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return resultCanciones;
    }
    
    //Mejor hacerlo con un objeto, asi solo hay que hacer una sola SQL.
    
    public void modificarGrupo(String nombreGrupo)
    {
        try
        {
            String sql = "SELECT * FROM grupos WHERE nombre = '" + nombreGrupo + "'";

            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            result.next();

            int codgrupo = result.getInt("codgrupo");
            String nombre = result.getString("nombre");
            String localidad = result.getString("localidad");
            String estilo = result.getString("estilo");
            boolean esgrupo = result.getBoolean("esgrupo");
            int annograb = result.getInt("annograb");
            Date fechaestreno = result.getDate("fechaestreno");
            String compania = result.getString("compania");

            System.out.println("codgrupo: " + codgrupo);
            System.out.println("nombre: " + nombre);
            System.out.println("localidad: " + localidad);
            System.out.println("estilo: " + estilo);
            System.out.println("esgrupo: " + esgrupo);
            System.out.println("annograb: " + annograb);
            System.out.println("fechaestreno: " + fechaestreno);
            System.out.println("compania: " + compania);

            String sqlUpdate = "";

            Scanner teclado = new Scanner(System.in);
            int select;

            System.out.println("|----------MENU----------|");
            System.out.println("| 1.Modificar codgrupo   |");
            System.out.println("| 2.Modificar nombre     |");
            System.out.println("| 3.Modificar localidad  |");
            System.out.println("| 4.Modificar estilo     |");
            System.out.println("| 5.Modificar esgrupo    |");
            System.out.println("| 6.Modificar annograb   |");
            System.out.println("| 7.Modificar fechaestren|");
            System.out.println("| 8.Modificar compania   |");
            System.out.println("|------------------------|");
            System.out.println("| Selecciona opcion:     |");
            select = teclado.nextInt();
            System.out.println("|------------------------|");
            switch (select)
            {
                case 1:
                    int nuevoCodgrupo = Teclado.introInt("nuevo codigo de grupo: ");
                    sqlUpdate = "UPDATE grupos SET codgrupo = " + nuevoCodgrupo + " "
                            + "WHERE codgrupo = " + codgrupo + ";";
                    break;
                case 2:
                    String nuevoNombre = Teclado.introString("nuevo nombre del grupo");
                    sqlUpdate = "UPDATE grupos SET nombre = '" + nuevoNombre + "' "
                            + "WHERE nombre = '" + nombre + "';";
                    break;
                case 3:
                    String nuevaLocalidad = Teclado.introString("nueva localidad del grupo:");
                    sqlUpdate = "UPDATE grupos SET localidad = '" + nuevaLocalidad + "' "
                            + "WHERE localidad = '" + localidad + "';";
                    break;
                case 4:
                    String nuevoEstilo = Teclado.introString("nuevo estilo del grupo:");
                    sqlUpdate = "UPDATE grupos SET estilo = '" + nuevoEstilo + "' "
                            + "WHERE estilo = '" + estilo + "';";
                    break;
                case 5:
                    boolean nuevoEsgrupo = Teclado.introBoolean("Es grupo?");
                    sqlUpdate = "UPDATE grupos SET esgrupo = " + nuevoEsgrupo + " "
                            + "WHERE esgrupo = " + esgrupo + ";";
                    break;
                case 6:
                    int nuevoAnnograb = Teclado.introInt("nuevo annograb del grupo: ");
                    sqlUpdate = "UPDATE grupos SET annograb = " + nuevoAnnograb + " "
                            + "WHERE annograb = " + annograb + ";";
                    break;
                case 7:
                    String nuevaFechaestreno = Teclado.introString("nueva fechaestreno del grupo:");
                    sqlUpdate = "UPDATE grupos SET fechaestreno = '" + nuevaFechaestreno + "' "
                            + "WHERE fechaestreno = '" + fechaestreno + "';";
                    break;
                case 8:
                    String nuevaCompania = Teclado.introString("nueva compania del grupo:");
                    sqlUpdate = "UPDATE grupos SET compania = '" + nuevaCompania + "' "
                            + "WHERE compania = '" + compania + "';";
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

            Statement stUpdate = conexion.createStatement();
            int resultUpdate = stUpdate.executeUpdate(sqlUpdate); 
            //Result es el numero de filas afectadas por Update

            System.out.println("Se modificó " + resultUpdate + " datos");
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }

    }

}
