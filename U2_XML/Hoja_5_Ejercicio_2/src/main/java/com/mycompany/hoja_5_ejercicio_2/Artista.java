package com.mycompany.hoja_5_ejercicio_2;

/**
 *
 * @author usuario
 */
public class Artista {
    private String nombre;
    private String foto;

    public Artista()
    {
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    @Override
    public String toString()
    {
        return "nombre=" + nombre + ", foto=" + foto;
    }
    
    
}
