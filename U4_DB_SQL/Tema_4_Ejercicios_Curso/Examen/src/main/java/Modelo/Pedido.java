package Modelo;
import java.sql.Date;

/**
 *
 * @author usuario
 */
public class Pedido {
    private Fabrica fabrica;
    private Articulo articulo;
    private Date fecha;
    private int unidades;
    private boolean urgente;
    private boolean servido;

    public Pedido()
    {
    }

    public Fabrica getFabrica()
    {
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica)
    {
        this.fabrica = fabrica;
    }

    public Articulo getArticulo()
    {
        return articulo;
    }

    public void setArticulo(Articulo articulo)
    {
        this.articulo = articulo;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public int getUnidades()
    {
        return unidades;
    }

    public void setUnidades(int unidades)
    {
        this.unidades = unidades;
    }

    public boolean isUrgente()
    {
        return urgente;
    }

    public void setUrgente(boolean urgente)
    {
        this.urgente = urgente;
    }

    public boolean isServido()
    {
        return servido;
    }

    public void setServido(boolean servido)
    {
        this.servido = servido;
    }
    
    
}
