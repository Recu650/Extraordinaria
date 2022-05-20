package com.mycompany.hoja_8;

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

    public Curso()
    {
    }
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public void addAlumno(Alumno alumno){
        this.alumnos.add(alumno);
    }
    
    public List<Alumno> getAlumnos()
    {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos)
    {
        this.alumnos = alumnos;
    }
    
}
