package com.mycompany.hoja_5_ejercicio_2;

import java.sql.Time;
import java.util.List;

/**
 *
 * @author usuario
 */

public class Cancion {
    private int numcancion;
    private int grupo; 
    //Desde el principio con objeto grupo
    private Time duracion;
    private String titulo;
    private int total_votos;
    private Grupo grupoObjeto;
    private List<Voto> votos;

    public Cancion()
    {
    }

    public List<Voto> getVotos()
    {
        return votos;
    }

    public void setVotos(List<Voto> votos)
    {
        this.votos = votos;
    }

    public Grupo getGrupoObjeto()
    {
        return grupoObjeto;
    }

    public void setGrupoObjeto(Grupo grupoObjeto)
    {
        this.grupoObjeto = grupoObjeto;
    }

    public int getNumcancion()
    {
        return numcancion;
    }

    public void setNumcancion(int numcancion)
    {
        this.numcancion = numcancion;
    }

    public int getGrupo()
    {
        return grupo;
    }

    public void setGrupo(int grupo)
    {
        this.grupo = grupo;
    }

    public Time getDuracion()
    {
        return duracion;
    }

    public void setDuracion(Time duracion)
    {
        this.duracion = duracion;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public int getTotal_votos()
    {
        return total_votos;
    }

    public void setTotal_votos(int total_votos)
    {
        this.total_votos = total_votos;
    }   
    
}
