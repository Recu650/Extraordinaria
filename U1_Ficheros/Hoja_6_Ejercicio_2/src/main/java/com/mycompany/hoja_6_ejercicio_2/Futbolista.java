package com.mycompany.hoja_6_ejercicio_2;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author usuario
 */
public class Futbolista
{
    private int idJugador;
    private String nombre;
    private String apellidos;
    private String alias;
    private Puesto puesto;
    private float altura;
    private LocalDate fechaNacimiento;
    private String codeEquipo;

    public Futbolista(int idJugador, String nombre, String apellidos, String alias, Puesto puesto, float altura, LocalDate fechaNacimiento, String codeEquipo)
    {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.alias = alias;
        this.puesto = puesto;
        this.altura = altura;
        this.fechaNacimiento = fechaNacimiento;
        this.codeEquipo = codeEquipo;
    }

    public Puesto getPuesto()
    {
        return puesto;
    }

    public void setPuesto(Puesto puesto)
    {
        this.puesto = puesto;
    }

    public int getIdJugador()
    {
        return idJugador;
    }

    public void setIdJugador(int idJugador)
    {
        this.idJugador = idJugador;
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

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public float getAltura()
    {
        return altura;
    }

    public void setAltura(float altura)
    {
        this.altura = altura;
    }

    public LocalDate getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCodeEquipo()
    {
        return codeEquipo;
    }

    public void setCodeEquipo(String codeEquipo)
    {
        this.codeEquipo = codeEquipo;
    }

    @Override
    public String toString()
    {
        return "Futbolista{" + "idJugador=" + idJugador + ", nombre=" + nombre + ", apellidos=" + apellidos + ", alias=" + alias + ", puesto=" + puesto + ", altura=" + altura + ", fechaNacimiento=" + fechaNacimiento + ", codeEquipo=" + codeEquipo + '}';
    }

    
    public String toCSV()
    {
        return idJugador + "," + nombre + "," + apellidos + "," + alias + "," + puesto + "," + altura + "," + fechaNacimiento + "," + codeEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idJugador;
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.apellidos);
        hash = 23 * hash + Objects.hashCode(this.alias);
        hash = 23 * hash + Objects.hashCode(this.puesto);
        hash = 23 * hash + Float.floatToIntBits(this.altura);
        hash = 23 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 23 * hash + Objects.hashCode(this.codeEquipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Futbolista other = (Futbolista) obj;
        if (this.idJugador != other.idJugador)
            return false;
        if (Float.floatToIntBits(this.altura) != Float.floatToIntBits(other.altura))
            return false;
        if (!Objects.equals(this.nombre, other.nombre))
            return false;
        if (!Objects.equals(this.apellidos, other.apellidos))
            return false;
        if (!Objects.equals(this.alias, other.alias))
            return false;
        if (!Objects.equals(this.codeEquipo, other.codeEquipo))
            return false;
        if (this.puesto != other.puesto)
            return false;
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento))
            return false;
        return true;
    }
    
    
}
