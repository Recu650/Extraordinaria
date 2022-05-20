package Modelo;

/**
 *
 * @author usuario
 */

public class Categoria {
    //ATRIBUTOS-------------------------------------------------------------------------------------
    private int codigo;
    private String nombre;
    //CONSTRUCTOR-----------------------------------------------------------------------------------
    public Categoria()
    {
    }
    //GETTER_&_SETTER-------------------------------------------------------------------------------
    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }    
    
}
