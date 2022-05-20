package es.ivanlorenzo.ad.examen2021_11;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author ivan
 */
public class Inmobiliaria
{
    private int identificador;
    private String nombre;
    private int numeroEmpleados;
    private Set<Inmueble> inmuebles;

    public Inmobiliaria()
    {
        this.inmuebles = new LinkedHashSet();
    }

    public Inmobiliaria(int identificador, String nombre, int numeroEmpleados)
    {
        this.identificador = identificador;
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleados;
        this.inmuebles = new LinkedHashSet();
    }

    public int getIdentificador()
    {
        return identificador;
    }

    public int getNumeroEmpleados()
    {
        return numeroEmpleados;
    }

    public void setIdentificador(int identificador)
    {
        this.identificador = identificador;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setNumeroEmpleados(int numeroEmpleados)
    {
        this.numeroEmpleados = numeroEmpleados;
    }

    public boolean añadirInmueble(Inmueble inmueble)
    {
        return this.inmuebles.add(inmueble);
    }

    public double getMediaPrecios()
    {
        double suma = 0;
        suma = inmuebles.stream()
                .map(inmueble -> inmueble.getPrecio())
                .reduce(suma, (acumulador, precio) -> acumulador + precio);

//        for(Inmueble  inmueble: this.inmuebles){
//            suma += inmueble.getPrecio();
//        }

        return (suma == 0) ? 0 : suma / inmuebles.size();

//        if (suma == 0)
//        {
//            return 0;
//        } else
//        {
//            return suma / this.inmuebles.size();
//        }
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 67 * hash + this.identificador;
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
        final Inmobiliaria other = (Inmobiliaria) obj;
        if (this.identificador != other.identificador)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        String textoInmuebles = this.inmuebles.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining("\n"));

        return String.format("%3d | %-25s | %3d empleados \n%s\n",
                identificador, nombre, numeroEmpleados, textoInmuebles);
    }

}
