package es.hectorsanchez.hoja_5_ejercicio_1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author usuario
 */
public class Instituto implements Serializable{
    //Atributos-------------------------------------------------//
    private String nombre;
    private LocalDate fechaConstruccion;
    private String localidad;
    //Constructores--------------------------------------------//

    public Instituto(String nombre, LocalDate fechaConstruccion, String localidad)
    {
        this.nombre = nombre;
        this.fechaConstruccion = fechaConstruccion;
        this.localidad = localidad;
    }

    public Instituto()
    {
    }
    //Getter_&_Setter_&_toString-------------------------------------//

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public LocalDate getFechaConstruccion()
    {
        return fechaConstruccion;
    }

    public void setFechaConstruccion(LocalDate fechaConstruccion)
    {
        this.fechaConstruccion = fechaConstruccion;
    }

    public String getLocalidad()
    {
        return localidad;
    }

    public void setLocalidad(String localidad)
    {
        this.localidad = localidad;
    }

    @Override
    public String toString()
    {
        return "Instituto{" + "nombre=" + nombre + ", fechaConstruccion=" + fechaConstruccion + ", localidad=" + localidad + '}';
    }
    //Equals_&_HashCode----------------------------------------------------------//

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.fechaConstruccion);
        hash = 67 * hash + Objects.hashCode(this.localidad);
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
        final Instituto other = (Instituto) obj;
        if (!Objects.equals(this.nombre, other.nombre))
            return false;
        if (!Objects.equals(this.localidad, other.localidad))
            return false;
        if (!Objects.equals(this.fechaConstruccion, other.fechaConstruccion))
            return false;
        return true;
    }
    
}
