package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Articulo {
    private String codigo;
    private String nombre;
    private double precio;
    private List<Pedido> pedidos;
    public Articulo()
    {
        this.pedidos = new ArrayList();
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
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

    public double getPrecio()
    {
        return precio;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    public List<Pedido> getPedidos()
    {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos)
    {
        this.pedidos = pedidos;
    }
    
    public void addPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }
}
