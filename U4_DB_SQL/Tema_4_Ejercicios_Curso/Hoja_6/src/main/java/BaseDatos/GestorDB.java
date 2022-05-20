package BaseDatos;

import Modelo.Pregunta;
import Modelo.Respuesta;
import Modelo.Usuario;
import Util.Teclado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GestorDB {
    //Ejercicio_1: Registrar un usuario nuevo en la base de datos-----------------------------------
    public boolean registrarUsusario(Usuario usuario) {
        boolean respuesta = false;

        Connection conexion = Conexion.getInstance().getConnection();
        String sql = "INSERT INTO usuarios (nombre, apellidos, fecha_nacimiento, usuario, password)"
                + " VALUES (?,?,?,?,md5(?))";

        try ( PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setString(1, usuario.getNombre());
            consulta.setString(2, usuario.getApellidos());
            consulta.setDate(3, usuario.getFecha_nacimiento());
            consulta.setString(4, usuario.getUsuario());
            consulta.setString(5, usuario.getPassword());

            int resul = consulta.executeUpdate();
            if (resul != 0)
                respuesta = true;
            //El metodo returnea true si se registra correctamente o false si no se registra.
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return respuesta;
    }

    //Ejercicio_2: Aniadir una lista de preguntas leida de un CSV a la base de datos----------------
    public int aniadirPreguntas(List<Pregunta> preguntas) {
        int contadorCorrectos = 0;

        Connection conexion = Conexion.getInstance().getConnection();
        String sql = "INSERT INTO preguntas (enunciado, categoria, nivel) VALUES (?,?,?)";

        for (Pregunta pregunta : preguntas) {
            try ( PreparedStatement consulta = conexion.prepareStatement(sql)) {
                consulta.setString(1, pregunta.getEnunciado());
                consulta.setString(2, pregunta.getCategoria());
                consulta.setInt(3, pregunta.getNivel());

                int resul = consulta.executeUpdate();
                contadorCorrectos = contadorCorrectos + resul;
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        return contadorCorrectos;
    }

    //Ejercicio_3: Visualizar preguntas de cada categoria-------------------------------------------
    public void preguntasPorCategorias(String categoria) {
        Connection conexion = Conexion.getInstance().getConnection();
        String sqlCategoria = "SELECT id, enunciado FROM preguntas WHERE categoria=?";
        String sqlCategoriaAleatoria
                = "SELECT id, enunciado FROM preguntas WHERE categoria=? ORDER BY RAND() LIMIT 5";
        String sqlRespuesta = "SELECT texto FROM respuestas WHERE pregunta_id=?";

        try ( PreparedStatement consultaCategoria = conexion.prepareStatement(sqlCategoria)) {
            consultaCategoria.setString(1, categoria);
            ResultSet result = consultaCategoria.executeQuery();

            result.last();  //Obtenemos el ultimo registro
            int cantidadRegistros = result.getRow();  //y comprobamos la cantidad de preguntas.
            result.beforeFirst(); //Volvemos a la primera posicion.

            if (cantidadRegistros > 5) {
                PreparedStatement consultaCategoriaAleatoria
                        = conexion.prepareStatement(sqlCategoriaAleatoria);
                consultaCategoriaAleatoria.setString(1, categoria);
                result = consultaCategoriaAleatoria.executeQuery();
            }
            while (result.next()) {
                int id = result.getInt("id");
                String enunciado = result.getString("enunciado");

                System.out.printf("%15s %-15s\n", id, enunciado);
                //Recorremos un nuevo resultset para obtener las respuestas a partir de una pregunta
                PreparedStatement consultaRespuestas = conexion.prepareStatement(sqlRespuesta);
                consultaRespuestas.setInt(1, id);
                ResultSet resultRespuesta = consultaRespuestas.executeQuery();

                while (resultRespuesta.next()) {
                    String texto = resultRespuesta.getString("texto");
                    System.out.println(texto);
                }

                System.out.println("[===========================================================]");
                System.out.println("");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    //Ejercicio_3: Visualizar preguntas de cada categoria-------------------------------------------
    public List<Pregunta> preguntasPorCategorias2(String categoria) {
        Connection conexion = Conexion.getInstance().getConnection();
        String sqlCategoria = "SELECT * FROM preguntas WHERE categoria=?";
        String sqlCategoriaAleatoria
                = "SELECT * FROM preguntas WHERE categoria=? ORDER BY RAND() LIMIT 5";
        String sqlRespuesta = "SELECT * FROM respuestas WHERE pregunta_id=?";

        List<Pregunta> preguntas = new ArrayList();

        try ( PreparedStatement consultaCategoria = conexion.prepareStatement(sqlCategoria)) {
            consultaCategoria.setString(1, categoria);
            ResultSet result = consultaCategoria.executeQuery();

            result.last();  //Obtenemos el ultimo registro
            int cantidadRegistros = result.getRow();  //y comprobamos la cantidad de preguntas.
            result.beforeFirst(); //Volvemos a la primera posicion.

            if (cantidadRegistros > 5) {
                PreparedStatement consultaCategoriaAleatoria
                        = conexion.prepareStatement(sqlCategoriaAleatoria);
                consultaCategoriaAleatoria.setString(1, categoria);
                result = consultaCategoriaAleatoria.executeQuery();
            }
            while (result.next()) {
                Pregunta pregunta = new Pregunta();

                pregunta.setId(result.getInt("id"));
                pregunta.setEnunciado(result.getString("enunciado"));
                pregunta.setCategoria(result.getString("categoria"));
                pregunta.setNivel(result.getInt("nivel"));
                pregunta.setFoto(result.getString("foto"));
                pregunta.setVeces_formulada(result.getInt("veces_formulada"));
                pregunta.setVeces_acertada(result.getInt("veces_acertada"));

                //Recorremos un nuevo resultset para obtener las respuestas a partir de una pregunta
                PreparedStatement consultaRespuestas = conexion.prepareStatement(sqlRespuesta);
                consultaRespuestas.setInt(1, pregunta.getId());
                ResultSet resultRespuesta = consultaRespuestas.executeQuery();

                while (resultRespuesta.next()) {
                    Respuesta respuesta = new Respuesta();

                    respuesta.setId(resultRespuesta.getInt("id"));
                    respuesta.setPregunta(pregunta);
                    respuesta.setTexto(resultRespuesta.getString("texto"));
                    respuesta.setCorrecta(resultRespuesta.getBoolean("correcta"));
                    respuesta.setFoto(resultRespuesta.getString("foto"));
                    respuesta.setVeces_respondida(resultRespuesta.getInt("veces_respondida"));

                    pregunta.addRespuesta(respuesta);
                }

                preguntas.add(pregunta);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return preguntas;
    }

    //Ejercicio_4: Jugar----------------------------------------------------------------------------
    public void jugar(String nombreUsuario, String password) {
        Connection conexion = Conexion.getInstance().getConnection();

        String sqlAcceso = "SELECT * FROM usuarios WHERE usuario=? AND password=md5(?)";

        try ( PreparedStatement consultaAcceso = conexion.prepareStatement(sqlAcceso)) {
            consultaAcceso.setString(1, nombreUsuario);
            consultaAcceso.setString(2, password);

            ResultSet resultAcceso = consultaAcceso.executeQuery();
            if (resultAcceso.next()) {
                String usuario = resultAcceso.getString("usuario");
                String nombre = resultAcceso.getString("nombre");
                String apellidos = resultAcceso.getString("apellidos");
                int num_accesos = resultAcceso.getInt("num_accesos");
                int test_realizados = resultAcceso.getInt("test_realizados");

                System.out.print("Se ha iniciado sesion con:");
                System.out.printf("%-10s Nombre: %-10s Apellidos: %-25s\n", 
                        usuario, nombre, apellidos);
                System.out.println("");

                this.contabilizarAcceso((num_accesos + 1), usuario, conexion);
                this.aumentarTestRealizados((test_realizados + 1), usuario, conexion);
                this.preguntarUsuario(conexion);
            } else
                System.out.println("ERROR AL INICIAR SESION");

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    //Aumenta el numero de accesos de un usuario cada vez que inicia sesion
    private void contabilizarAcceso(int numeroAccesos, String usuario, Connection conexion) {
        try {
            String sql = "UPDATE usuarios SET num_accesos = ? WHERE usuario = ?";

            PreparedStatement update = conexion.prepareStatement(sql);
            update.setInt(1, numeroAccesos);
            update.setString(2, usuario);

            int numeroRegistrosModificados = update.executeUpdate();
            System.out.println("//contabilizarAcceso " + numeroRegistrosModificados + 
                    " registros modificados");
        } catch (SQLException ex) {
            System.out.println("ERROR contabilizarAcceso: " + ex.toString());
        }
    }

    //Aumenta el numero de test realizados de un usuario cuando realiza un test
    private void aumentarTestRealizados(int test_realizados, String usuario, Connection conexion) {
        try {
            String sql = "UPDATE usuarios SET test_realizados = ? WHERE usuario = ?";

            PreparedStatement update = conexion.prepareStatement(sql);
            update.setInt(1, test_realizados);
            update.setString(2, usuario);
            int numeroRegistrosModificados = update.executeUpdate();
            System.out.println("//aumentarTestRealizados " + numeroRegistrosModificados + 
                    " registros modificados");
        } catch (SQLException ex) {
            System.out.println("ERROR aumentarTestRealizados: " + ex.toString());
        }
    }

    //Ofrece 4 preguntas aleatorias al usuario
    private void preguntarUsuario(Connection conexion) {
        try {
            String sqlPreguntas = "SELECT * FROM preguntas ORDER BY RAND() LIMIT 4";

            PreparedStatement consultaPreguntas = conexion.prepareStatement(sqlPreguntas);
            ResultSet resultPreguntas = consultaPreguntas.executeQuery();
            System.out.println("\nQUE COMIENCE EL JUEGO !\n");
            while (resultPreguntas.next()) {
                Pregunta p = new Pregunta();
                p.setId(resultPreguntas.getInt("id"));
                p.setEnunciado(resultPreguntas.getString("enunciado"));
                p.setCategoria(resultPreguntas.getString("categoria"));
                p.setNivel(resultPreguntas.getInt("nivel"));
                p.setFoto(resultPreguntas.getString("foto"));
                p.setVeces_formulada(resultPreguntas.getInt("veces_formulada"));
                p.setVeces_acertada(resultPreguntas.getInt("veces_acertada"));

                System.out.printf("%-5s %-10s\n", p.getId(), p.getEnunciado());
                List<Respuesta> respuestas = ObtenerRespuestas(p, conexion);
                p.setRespuestas(respuestas);
                
                int numeradorRespuestas = 1;
                for(Respuesta respuesta: p.getRespuestas()){
                    System.out.println(numeradorRespuestas + " - " + respuesta.getTexto());
                    numeradorRespuestas ++;
                }
                System.out.println("");
                //El usuario responde 1/2/3/4. Se comprueba si es correcto y se contabiliza.
                int respuestaUsuario = Teclado.introInt("Introduzca su respuesta: (1/2/3/4)");
                if(p.getRespuestas().get(respuestaUsuario - 1).isCorrecta()){
                    System.out.println("Correcto!");
                    this.aumentarPreguntaAcertada((p.getVeces_acertada() + 1), p.getId(), conexion);
                }else{
                    System.out.println("Error!");
                }
                System.out.println("");
                this.aumentarRespuestaRespondida(
                        p.getRespuestas().get(respuestaUsuario - 1).getVeces_respondida(), 
                        p.getRespuestas().get(respuestaUsuario - 1).getId(), 
                        conexion);
                this.aumentarPreguntaFormulada((p.getVeces_formulada() + 1), p.getId(), conexion);
                System.out.println("");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR preguntarUsuario: " + ex.toString());
        }
    }
    //Saca las respuestas de una pregunta
    private List<Respuesta> ObtenerRespuestas(Pregunta p, Connection conexion) {
        List<Respuesta> respuestas = new ArrayList();
        try {
            String sqlRespuestas = "SELECT * FROM respuestas WHERE pregunta_id = ?";

            PreparedStatement consultaRespuestas = conexion.prepareStatement(sqlRespuestas);
            consultaRespuestas.setInt(1, p.getId());
            ResultSet resultRespuestas = consultaRespuestas.executeQuery();

            while (resultRespuestas.next()) {
                Respuesta r = new Respuesta();
                r.setId(resultRespuestas.getInt("id"));
                r.setPregunta(p);
                r.setTexto(resultRespuestas.getString("texto"));
                r.setCorrecta(resultRespuestas.getBoolean("correcta"));
                r.setFoto(resultRespuestas.getString("foto"));
                r.setVeces_respondida(resultRespuestas.getInt("veces_respondida"));
                respuestas.add(r);
            }
            
        } catch (SQLException ex) {
            System.out.println("ERROR ObtenerRespuestas: " + ex.toString());
        }
        return respuestas;
    }
    //Aumenta el numero de veces que una pregunta es formulada
    private void aumentarPreguntaFormulada(int veces_formulada, int id, Connection conexion){
        try {
            String sql = "UPDATE preguntas SET veces_formulada = ? WHERE id = ?";
            PreparedStatement update = conexion.prepareStatement(sql);
            update.setInt(1, veces_formulada);
            update.setInt(2, id);
            int numeroRegistrosModificados = update.executeUpdate();
            System.out.println("//aumentarPreguntaFormulada " + numeroRegistrosModificados + 
                    " registros modificados");
        } catch (SQLException ex) {
            System.out.println("ERROR aumentarPreguntaFormulada: " + ex.toString());
        }
    }
    //Aumenta el numero de veces que una pregunta es acertada
    private void aumentarPreguntaAcertada(int veces_acertada, int id, Connection conexion){
        try {
            String sql = "UPDATE preguntas SET veces_acertada = ? WHERE id = ?";
            PreparedStatement update = conexion.prepareStatement(sql);
            update.setInt(1, veces_acertada);
            update.setInt(2, id);
            int numeroRegistrosModificados = update.executeUpdate();
            System.out.println("//aumentarPreguntaAcertada " + numeroRegistrosModificados + 
                    " registros modificados");
        } catch (SQLException ex) {
            System.out.println("ERROR aumentarPreguntaAcertada: " + ex.toString());
        }
    }
    //Aumenta el numero de veces que se responde una respuesta
    public void aumentarRespuestaRespondida(int veces_respondida, int id, Connection conexion) {
        try {
            String sql = "UPDATE respuestas SET veces_respondida=? WHERE id=?";
            PreparedStatement update = conexion.prepareStatement(sql);
            update.setInt(1, veces_respondida + 1);
            update.setInt(2, id);
            int numeroRegistrosModificados = update.executeUpdate();
            System.out.println("//aumentarRespuestaRespondida " + numeroRegistrosModificados + 
                    " registros modificados");
        } catch (SQLException ex) {
            System.out.println("ERROR aumentarRespuestaRespondida: " + ex.toString());
        }
    }
}
