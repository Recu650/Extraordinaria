package com.mycompany.hoja_4_ejercicio_2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
public class GestorDB
{
    private Connection conexion;

    public GestorDB()
    {
        this.conexion = Conexion.getInstance().getConnection();
    }

    public List<Grupo> listadoGrupos()
    {
        List<Grupo> grupos = new ArrayList();
        String sql = "SELECT * FROM grupos ORDER BY codgrupo ASC";

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

    public List<Grupo> listadoGruposCanciones()
    {
        List<Grupo> grupos = new ArrayList();

        try
        {
            String sqlGrupo = "SELECT * FROM grupos ORDER BY nombre ASC";
            Statement stGrupo = conexion.createStatement();
            ResultSet resultGrupo = stGrupo.executeQuery(sqlGrupo);

            while (resultGrupo.next())
            {
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

                String sqlCancion = "SELECT * FROM canciones WHERE grupo = " + codGrupo + ";";
                Statement stCancion = conexion.createStatement();
                ResultSet resultCancion = stCancion.executeQuery(sqlCancion);

                while (resultCancion.next())
                {
                    Cancion cancion = new Cancion();

                    cancion.setDuracion(resultCancion.getTime("duracion"));
                    cancion.setGrupo(grupo);
                    cancion.setNumcancion(resultCancion.getInt("numcancion"));
                    cancion.setTitulo(resultCancion.getString("titulo"));
                    cancion.setTotal_votos(resultCancion.getInt("total_votos"));

                    grupo.addCancion(cancion);
                }
                grupos.add(grupo);
            }
            return grupos;
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return grupos;
    }

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
                Cancion cancion = new Cancion();

                cancion.setNumcancion(result.getInt("numcancion"));
                cancion.setDuracion(result.getTime("duracion"));
                cancion.setTitulo(result.getString("titulo"));

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
                    + "ORDER BY total_votos DESC LIMIT 5";

            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);

            while (result.next())
            {
                Cancion cancion = new Cancion();
                Grupo grupo = new Grupo();

                cancion.setTotal_votos(result.getInt("total_votos"));
                cancion.setTitulo(result.getString("titulo"));
                grupo.setNombre(result.getString("nombre"));

                cancion.setGrupo(grupo);

                canciones.add(cancion);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return canciones;
    }

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
                Cancion cancion = new Cancion();
                Grupo grupo = new Grupo();
                Voto voto = new Voto();

                voto.setFecha(result.getDate("fecha"));
                cancion.setTitulo(result.getString("titulo"));
                grupo.setNombre(result.getString("nombre"));

                cancion.setGrupo(grupo);
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
            String sqlVotos = "DELETE FROM votos "
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

    public void modificarGrupo(String nombreGrupo)
    {
        try
        {
            String sqlSelect = "SELECT * FROM grupos WHERE nombre = '" + nombreGrupo + "';";

            Statement st = conexion.createStatement();
            ResultSet result = st.executeQuery(sqlSelect);
            result.next();

            Grupo grupo = new Grupo();
            grupo.setCodgrupo(result.getBigDecimal("codgrupo"));
            grupo.setNombre(result.getString("nombre"));
            grupo.setLocalidad(result.getString("localidad"));
            grupo.setEstilo(result.getString("estilo"));
            grupo.setEsgrupo(result.getBoolean("esgrupo"));
            grupo.setAnnograb(result.getInt("annograb"));
            grupo.setFechaestreno(result.getDate("fechaestreno"));
            grupo.setCompania(result.getString("compania"));

            System.out.println("codgrupo: " + grupo.getCodgrupo());
            System.out.println("nombre: " + grupo.getNombre());
            System.out.println("localidad: " + grupo.getLocalidad());
            System.out.println("estilo: " + grupo.getEstilo());
            System.out.println("esgrupo: " + grupo.isEsgrupo());
            System.out.println("annograb: " + grupo.getAnnograb());
            System.out.println("fechaestreno: " + grupo.getFechaestreno());
            System.out.println("compania: " + grupo.getCompania());

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
            select = Teclado.introInt("");
            System.out.println("|------------------------|");
            switch (select)
            {
                case 1:
                    BigDecimal codigo = BigDecimal.valueOf(Teclado.introInt("Nuevo codigo del grupo: "));
                    grupo.setCodgrupo(codigo);
                    break;
                case 2:
                    grupo.setNombre(Teclado.introString("Nuevo nombre del grupo: "));
                    break;
                case 3:
                    grupo.setLocalidad(Teclado.introString("Nueva localidad del grupo: "));
                    break;
                case 4:
                    grupo.setEstilo(Teclado.introString("Nuevo estilo del grupo: "));
                    break;
                case 5:
                    grupo.setEsgrupo(Teclado.introBoolean("Es grupo? "));
                    break;
                case 6:
                    grupo.setAnnograb(Teclado.introInt("Nuevo anio de grabacion: "));
                    break;
                case 7:
                    grupo.setFechaestreno(Date.valueOf(Teclado.introFecha("Nueva fecha de estreno")));
                    break;
                case 8:
                    grupo.setCompania(Teclado.introString("Nueva compania del grupo: "));
                    break;
            }          

            String sqlUpdate = "UPDATE grupos SET codgrupo = " + grupo.getCodgrupo() + ", "
                    + "nombre = '" + grupo.getNombre() + "', "
                    + "localidad = '" + grupo.getLocalidad() + "', "
                    + "estilo = '" + grupo.getEstilo() + "', "
                    + "esgrupo = " + grupo.isEsgrupo() + ", "
                    + "annograb = " + grupo.getAnnograb() + ", "
                    + "fechaestreno = '" + grupo.getFechaestreno().toString() + "', "
                    + "compania = '" + grupo.getCompania() + "' "
                    + "WHERE nombre = '" + nombreGrupo + "';";

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
