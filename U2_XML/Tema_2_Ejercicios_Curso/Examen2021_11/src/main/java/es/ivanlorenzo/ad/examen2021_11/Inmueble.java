package es.ivanlorenzo.ad.examen2021_11;

import java.util.Objects;

/**
 *
 * @author ivan
 */
public class Inmueble
{
    private String nombre;
    private double precio;
    private String descripcion;

    public Inmueble()
    {
    }

    public Inmueble(String nombre, double precio, String descripcion)
    {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public double getPrecio()
    {
        return precio;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
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
        final Inmueble other = (Inmueble) obj;
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio))
            return false;
        if (!Objects.equals(this.nombre, other.nombre))
            return false;
        if (!Objects.equals(this.descripcion, other.descripcion))
            return false;
        return true;
    }
    
    
    
    

    @Override
    public String toString()
    {
        return String.format("    | %-25s | %8.2f€ | \t%s", nombre, precio, descripcion);
    }
    
    
}
