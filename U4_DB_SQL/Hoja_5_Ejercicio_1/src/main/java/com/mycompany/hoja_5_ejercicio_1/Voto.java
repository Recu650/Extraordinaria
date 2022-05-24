package com.mycompany.hoja_5_ejercicio_1;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Voto {

    private Usuario usuario;
    private Date fecha;
    private Cancion cancion;

    public Voto() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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
