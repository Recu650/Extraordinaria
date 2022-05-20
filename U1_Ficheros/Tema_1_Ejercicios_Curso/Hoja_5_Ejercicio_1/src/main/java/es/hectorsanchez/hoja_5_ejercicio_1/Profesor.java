package es.hectorsanchez.hoja_5_ejercicio_1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author usuario
 */
public class Profesor implements Serializable{
    //Atributos-------------------------------------------------//
    private int numRegistro;
    private String nombre;
    private LocalDate fechaIngreso;
    private Instituto insti;
    //Constructores---------------------------------------------//

    public Profesor()
    {
    }

    public Profesor(int numRegistro, String nombre, LocalDate fechaIngreso, Instituto insti)
    {
        this.numRegistro = numRegistro;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.insti = insti;
    }
    //Getter_&_Setter_&_ toString------------------------------------//
    public int getNumRegistro()
    {
        return numRegistro;
    }

    public void setNumRegistro(int numRegistro)
    {
        this.numRegistro = numRegistro;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public LocalDate getFechaIngreso()
    {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso)
    {
        this.fechaIngreso = fechaIngreso;
    }

    public Instituto getInsti()
    {
        return insti;
    }

    public void setInsti(Instituto insti)
    {
        this.insti = insti;
    }

    @Override
    public String toString()
    {
        return "Profesor{" + "numRegistro=" + numRegistro + ", nombre=" + nombre + ", fechaIngreso=" + fechaIngreso + ", insti=" + insti + '}';
    }

    
    //Equals_&_HashCode-----------------------------------------------------------//

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + this.numRegistro;
        hash = 83 * hash + Objects.hashCode(this.nombre);
        hash = 83 * hash + Objects.hashCode(this.fechaIngreso);
        hash = 83 * hash + Objects.hashCode(this.insti);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Profesor other = (Profesor) obj;
        if (this.numRegistro != other.numRegistro)
            return false;
        if (!Objects.equals(this.nombre, other.nombre))
            return false;
        if (!Objects.equals(this.fechaIngreso, other.fechaIngreso))
            return false;
        if (!Objects.equals(this.insti, other.insti))
            return false;
        return true;
    }
    
    
}
