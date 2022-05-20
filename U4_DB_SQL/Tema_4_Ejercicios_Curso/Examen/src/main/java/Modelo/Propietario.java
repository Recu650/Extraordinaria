package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Propietario {
    private int id;
    private String nombre;
    private String pais;
    private List<Fabrica> Fabricas;

    public Propietario()
    {
        this.Fabricas = new ArrayList();
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

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public List<Fabrica> getFabricas()
    {
        return Fabricas;
    }

    public void setFabricas(List<Fabrica> Fabricas)
    {
        this.Fabricas = Fabricas;
    }
    
    public void addFabrica(Fabrica fabrica){
        this.Fabricas.add(fabrica);
    }
}
