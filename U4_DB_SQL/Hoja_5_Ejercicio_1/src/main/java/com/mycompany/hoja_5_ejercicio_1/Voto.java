package com.mycompany.hoja_5_ejercicio_1;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Voto {

    private String usuario;
    private Date fecha;
    private Cancion cancion;

    public Voto() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

}
