package com.mycompany.hoja_8_ejercicio_1;

import java.sql.Connection;
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

    private boolean existeCurso(String curso) {
        boolean existe = false;
        int contador = 0;
        try {
            String sql = "SELECT * FROM cursos WHERE id = '" + curso + "';";
            Statement st = this.conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                contador++;
            }
            if (contador == 1)
                existe = true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return existe;
    }

    public String tutorCurso(String curso) {
        String tutor = "";
        if (existeCurso(curso)) {
            try {
                String sql = "SELECT * FROM profesores "
                        + "INNER JOIN cursos ON cursos.tutor_id = profesores.id "
                        + "WHERE cursos.id = '" + curso + "';";
                Statement st = this.conexion.createStatement();
                ResultSet result = st.executeQuery(sql);
                result.next();

                tutor = result.getString("nombre");

            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        } else
            tutor = "El curso que buscas no existe";
        return tutor;
    }

    public List<Curso> cursosSinTutor() {
        List<Curso> cursos = new ArrayList();
        try {
            String sql = "SELECT * FROM cursos WHERE tutor_id IS NULL;";
            Statement st = this.conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                Curso curso = new Curso();
                curso.setId(result.getString("id"));
                curso.setNombre(result.getString("nombre"));
                cursos.add(curso);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return cursos;
    }

    public List<Curso> cursosSinAlumnos() {
        List<Curso> cursos = new ArrayList();
        try {
            String sql = "SELECT * FROM cursos WHERE id NOT IN (SELECT curso FROM alumnos);";
            Statement st = this.conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                Curso curso = new Curso();
                curso.setId(result.getString("id"));
                curso.setNombre(result.getString("nombre"));
                cursos.add(curso);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return cursos;
    }

    public List<Profesor> profeNoTutor() {
        List<Profesor> profesores = new ArrayList();
        try {
            String sql = "SELECT * FROM profesores WHERE id NOT IN (SELECT tutor_id FROM cursos);";
            Statement st = this.conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                Profesor profesor = new Profesor();
                profesor.setId(result.getInt("id"));
                profesor.setNombre(result.getString("nombre"));
                profesor.setFecha_nacimiento(result.getDate("fecha_nacimiento"));
                profesores.add(profesor);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return profesores;
    }

    public String alumnosEnCadaCurso() { //INVENTADISIMA
        String resultado = "";
        try {
            String sql = "SELECT count(alumnos.id), cursos.nombre "
                    + "FROM alumnos INNER JOIN cursos ON alumnos.curso = curso.id "
                    + "GROUP BY alumnos.curso;";
            Statement st = this.conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()){
                resultado = resultado + result.getString("cursos.combre") + "\t" 
                        + result.getInt("count(alumno.id)") + "\n";
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return resultado;
    }
    
    public List<Alumno> alumnosDelCursoDe(String idAlumno){
        List<Alumno> alumnos = new ArrayList();
        try{
            String sql = "SELECT * FROM alumnos WHERE curso = "
                    + "(SELECT curso FROM alumnos WHERE id = " + idAlumno + ")";
            Statement st = this.conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()){
                Alumno alumno = new Alumno();
                alumno.setId(result.getInt("id"));
                alumno.setNombre(result.getString("nombre"));
                alumno.setNota_media(result.getDouble("nota_media"));
                alumnos.add(alumno);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return alumnos;
    }
    
    public List<Alumno> alumnosConMasNota(int nAlumnos){
        List<Alumno> alumnos = new ArrayList();
        try{
            String sql = "SELECT * FROM alumnos ORDER BY nota_media DESC LIMIT " + nAlumnos + ";";
            Statement st = this.conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()){
                Alumno alumno = new Alumno();
                alumno.setId(result.getInt("id"));
                alumno.setNombre(result.getString("nombre"));
                alumno.setNota_media(result.getDouble("nota_media"));
                alumnos.add(alumno);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return alumnos;
    }
    
}
