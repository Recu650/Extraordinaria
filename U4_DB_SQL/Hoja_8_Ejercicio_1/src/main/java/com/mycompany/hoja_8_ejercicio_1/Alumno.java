package com.mycompany.hoja_8_ejercicio_1;

/**
 *
 * @author usuario
 */
public class Alumno {
    private int id;
    private String nombre;
    private double nota_media;
    private Curso curso;

    public Alumno() {
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
    public double getNota_media() {
        return nota_media;
    }
    public void setNota_media(double nota_media) {
        this.nota_media = nota_media;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno { " + id + "\tnombre: " + nombre + "\tmedia: " + nota_media + "\tcurso: " + curso + " }";
    }
    
}
