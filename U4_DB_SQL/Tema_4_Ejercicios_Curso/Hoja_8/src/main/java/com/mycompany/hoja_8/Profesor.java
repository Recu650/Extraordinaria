package com.mycompany.hoja_8;

import java.sql.Date;
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

    public Profesor()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public Date getFecha_nacimiento()
    {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento)
    {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public List<Curso> getCursos()
    {
        return cursos;
    }

    public void setCursos(List<Curso> cursos)
    {
        this.cursos = cursos;
    }
    
    
}
