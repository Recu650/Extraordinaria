package com.mycompany.hoja_8_ejercicio_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Curso {
    private String id;
    private String nombre;
    private Profesor tutor;
    private List<Alumno> alumnos;

    public Curso() {
        this.alumnos = new ArrayList();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Profesor getTutor() {
        return tutor;
    }
    public void setTutor(Profesor tutor) {
        this.tutor = tutor;
    }
    public List<Alumno> getAlumnos() {
        return alumnos;
    }
    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    public void addAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    @Override
    public String toString() {
        
        return "Curso { " + id + "\tcurso: " + nombre + "\ttutor: " + tutor + " }";
    }
    
    
}
