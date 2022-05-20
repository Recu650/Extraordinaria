package Modelo;

/**
 *
 * @author usuario
 */
public class Fabrica {
    private int id;
    private String nombre;
    private Propietario propietario;

    public Fabrica()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public Propietario getPropietario()
    {
        return propietario;
    }

    public void setPropietario(Propietario propietario)
    {
        this.propietario = propietario;
    }
    
    
}
