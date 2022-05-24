package com.mycompany.hoja_5_ejercicio_1;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Usuario {
    private String usuario;
    private String password;
    private String nombre;
    private String apellidos;
    private Date fechanacimiento;
    private Voto voto;

    public Usuario()
    {
    }

    public String getUsuario()
    {
        return usuario;
    }
    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getNombre()
    {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String getApellidos()
    {
        return apellidos;
    }
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }
    public Date getFechanacimiento()
    {
        return fechanacimiento;
    }
    public void setFechanacimiento(Date fechanacimiento)
    {
        this.fechanacimiento = fechanacimiento;
    }   
    public Voto getVoto()
    {
        return voto;
    }
    public void setVoto(Voto voto)
    {
        this.voto = voto;
    }
    
}
