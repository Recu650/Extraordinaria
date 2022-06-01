package com.mycompany.hoja_4_ejercicio_2;

import java.sql.Time;
import java.util.List;

public class Cancion {
    private int numcancion;
    private Time duracion;
    private String titulo;
    private int total_votos;
    private Grupo grupo;
    private List<Voto> votos;

    public Cancion() {
    }

    public int getNumcancion() {
        return numcancion;
    }
    public void setNumcancion(int numcancion) {
        this.numcancion = numcancion;
    }
    public Time getDuracion() {
        return duracion;
    }
    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getTotal_votos() {
        return total_votos;
    }
    public void setTotal_votos(int total_votos) {
        this.total_votos = total_votos;
    }
    public Grupo getGrupo() {
        return grupo;
    }
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    public List<Voto> getVotos() {
        return votos;
    }
    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }   
}
