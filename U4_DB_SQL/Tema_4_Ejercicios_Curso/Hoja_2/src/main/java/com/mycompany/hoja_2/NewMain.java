/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
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
        Connection conexion;
        try
        {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:concursomusica.db");
            System.out.println("Conexion OK con concursomusica.db");
            //------------------------------------------------------------------------------------//

            consultarGrupos(conexion);
            System.out.println("-----------------------------------------------------------------");
            //insertarVariosGrupos2(conexion);
            consultarCancionesGrupo(conexion);
            System.out.println("-----------------------------------------------------------------");
            consultarGrupos(conexion);

            //------------------------------------------------------------------------------------//
        } catch (ClassNotFoundException ex)
        {
            System.out.println("Error al cargar el driver " + ex.toString());
        } catch (SQLException ex)
        {
            System.out.println("Error al crear la conexion " + ex.toString());
        }
    }

    private static void consultarGrupos(Connection conexion)
    {
        try
        {
            //Creamos un objeto Statement para enviar instrucciones SQL sin parámetros
            Statement st = conexion.createStatement();
            //Crear instrucción SQL
            String txtSQL = "SELECT nombregrupo, localidad FROM grupos";
            //Devuelve un conjunto de resultados
            ResultSet result = st.executeQuery(txtSQL);
            //Procesar ResultSet...
            while (result.next())
            {
                // Saca el valor de la columna nombregrupo y de la segunda columna
                String nombre = result.getString("nombregrupo");
                String localidad = result.getString(2);
                System.out.println("NOMBRE: " + nombre + ",  LOCALIDAD: " + localidad);
            }
        } catch (SQLException e)
        {
            System.err.println("Error en el método consultarGrupos");
        }
    }

    private static void insertarGrupo(Connection conexion)
    {
        //Recoger datos (lo ideal sería haber recibido un objeto Grupo por parámetro)
        Scanner teclado = new Scanner(System.in);
        //Crear texto de consulta con parámetros sustituibles
        System.out.println("Nombre");
        String nombre = teclado.nextLine();
        System.out.println("Localidad");
        String localidad = teclado.nextLine();
        System.out.println("¿Es grupo? 0/1");
        int esGrupo = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Fecha");
        String fecha = teclado.nextLine();
        System.out.println("Año");
        int año = teclado.nextInt();

        //Crear texto de consulta con parámetros sustituibles
        String sql = "INSERT INTO grupos (nombregrupo,localidad,"
                + "esgrupo,fechaPrimerConcierto,annoPrimerDisco) "
                + "VALUES (?,?,?,?,?);";

        //Construir un PreparedStatement para sustituir valores en consulta parametrizada
        try ( PreparedStatement consultaPreparada = conexion.prepareStatement(sql))
        {
            // Sustituye la ? primera por el contenido de nombre
            consultaPreparada.setString(1, nombre);
            // Sustituye la ? segunda por el contenido de localidad
            consultaPreparada.setString(2, localidad);
            consultaPreparada.setInt(3, esGrupo);
            consultaPreparada.setString(4, fecha);
            consultaPreparada.setInt(5, año);

            int numeroGruposInsertados = consultaPreparada.executeUpdate();
            System.out.println("Se han insertado " + numeroGruposInsertados + " grupos");
        } catch (SQLException ex)
        {
            System.err.println("Error al insertar un grupo");
        }
    }

    private static void insertarVariosGrupos(Connection conexion)
    {
        boolean continuar = true;
        do
        {
            //Recoger datos (lo ideal sería haber recibido un objeto Grupo por parámetro)

            //Crear texto de consulta con parámetros sustituibles
            String nombre = Teclado.introString("Nombre");
            String localidad = Teclado.introString("Localidad");
            int esGrupo = Teclado.introInt("¿Es grupo? 0/1");
            String fecha = Teclado.introString("Fecha");
            int año = Teclado.introInt("Año");

            //Crear texto de consulta con parámetros sustituibles
            String sql = "INSERT INTO grupos (nombregrupo,localidad,"
                    + "esgrupo,fechaPrimerConcierto,annoPrimerDisco) "
                    + "VALUES (?,?,?,?,?);";

            //Construir un PreparedStatement para sustituir valores en consulta parametrizada
            try ( PreparedStatement consultaPreparada = conexion.prepareStatement(sql))
            {
                // Sustituye la ? primera por el contenido de nombre
                consultaPreparada.setString(1, nombre);
                // Sustituye la ? segunda por el contenido de localidad
                consultaPreparada.setString(2, localidad);
                consultaPreparada.setInt(3, esGrupo);
                consultaPreparada.setString(4, fecha);
                consultaPreparada.setInt(5, año);

                int numeroGruposInsertados = consultaPreparada.executeUpdate();
                System.out.println("Se han insertado " + numeroGruposInsertados + " grupos");
            } catch (SQLException ex)
            {
                System.err.println("Error al insertar un grupo");
            }

            continuar = Teclado.introBoolean("Insertar otro grupo?");
        } while (continuar == true);
    }

    private static void insertarVariosGrupos2(Connection conexion)
    {
        boolean continuar = true;
        int numeroGruposInsertados = 0;
        //Crear texto de consulta con parámetros sustituibles
        String sql = "INSERT INTO grupos (nombregrupo,localidad,"
                + "esgrupo,fechaPrimerConcierto,annoPrimerDisco) "
                + "VALUES (?,?,?,?,?);";

        //Construir un PreparedStatement para sustituir valores en consulta parametrizada
        try ( PreparedStatement consultaPreparada = conexion.prepareStatement(sql))
        {
            do
            {
                //Recoger datos (lo ideal sería haber recibido un objeto Grupo por parámetro)
                //Crear texto de consulta con parámetros sustituibles
                String nombre = Teclado.introString("Nombre");
                String localidad = Teclado.introString("Localidad");
                int esGrupo = Teclado.introInt("¿Es grupo? 0/1");
                String fecha = Teclado.introString("Fecha");
                int año = Teclado.introInt("Año");

                // Sustituye la ? primera por el contenido de nombre
                consultaPreparada.setString(1, nombre);
                // Sustituye la ? segunda por el contenido de localidad
                consultaPreparada.setString(2, localidad);
                consultaPreparada.setInt(3, esGrupo);
                consultaPreparada.setString(4, fecha);
                consultaPreparada.setInt(5, año);
                consultaPreparada.executeUpdate();
                numeroGruposInsertados++;

                continuar = Teclado.introBoolean("Insertar otro grupo?");
            } while (continuar == true);

            System.out.println("Se han insertado " + numeroGruposInsertados + " grupos");
        } catch (SQLException ex)
        {
            System.err.println("Error al insertar un grupo");
        }
    }

    private static void consultarCancionesGrupo(Connection conexion)
    {
        String grupo = Teclado.introString("Nombre del grupo");
        try
        {
            //Creamos un objeto Statement para enviar instrucciones SQL sin parámetros
            Statement st = conexion.createStatement();
            //Crear instrucción SQL
            String txtSQL = "SELECT titulo, duracion "
                    + "FROM canciones INNER JOIN grupos "
                    + "ON canciones.numgrupo = grupos.numgrupo "
                    + "WHERE grupos.nombregrupo = '" + grupo + "';";
            //Devuelve un conjunto de resultados
            ResultSet result = st.executeQuery(txtSQL);
            //Procesar ResultSet...
            while (result.next())
            {
                // Saca el valor de la columna nombregrupo y de la segunda columna
                String titulo = result.getString("titulo");
                int duracion = Integer.parseInt(result.getString("duracion"));
                System.out.println("TITULO: "+titulo+",  DURACION: "+calcularTiempo(duracion));
            }
        } catch (SQLException e)
        {
            System.err.println("Error en el método consultarGrupos");
        }
    }

    private static String calcularTiempo(int tsegundos)
    {
        int minutos = tsegundos / 60;
        int segundos = tsegundos - (minutos * 60);
        return minutos + " min " + segundos + " seg";
    }
}
