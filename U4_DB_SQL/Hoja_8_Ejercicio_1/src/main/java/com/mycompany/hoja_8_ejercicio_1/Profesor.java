package com.mycompany.hoja_8_ejercicio_1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Profesor {
    private int id;
    private String nombre;
    private Date fecha_nacimiento;
    private List<Curso> cursos;

    public Profesor() {
        this.cursos = new ArrayList();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public List<Curso> getCursos() {
        return cursos;
    }
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    public void addCurso (Curso curso){
        this.cursos.add(curso);
    } 

    @Override
    public String toString() {
        return "Profesor { " + id + "\tnombre: " + nombre + "\tfecha de nacimiento: " + fecha_nacimiento + " }";
    }   
    
}
