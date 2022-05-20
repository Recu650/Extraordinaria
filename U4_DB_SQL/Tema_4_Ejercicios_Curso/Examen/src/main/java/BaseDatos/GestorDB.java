package BaseDatos;

import Modelo.Articulo;
import Modelo.Fabrica;
import Modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class GestorDB
{
    public int modificarUrgenciaArticulo(String nombreArticulo)
    {
        Connection conexion = Conexion.getInstance().getConnection();
        int nPedidosModificados = 0;
        Articulo a = new Articulo();
        try
        {
            String sqlConsultaArticulo = "SELECT * FROM articulos WHERE nombre = ?";
            PreparedStatement consultaArticulo = conexion.prepareStatement(sqlConsultaArticulo);
            consultaArticulo.setString(1, nombreArticulo);
            ResultSet resultArticulo = consultaArticulo.executeQuery();

            if (resultArticulo.next())
            {
                a.setCodigo(resultArticulo.getString("codigo"));
                a.setNombre(resultArticulo.getString("nombre"));
                a.setPrecio(resultArticulo.getDouble("precio"));

                String sqlUpdate = "UPDATE pedidos SET urgente = 0 "
                        + "WHERE codigo_articulo = ? AND servido = 1 AND urgente = 1";
                PreparedStatement update = conexion.prepareStatement(sqlUpdate);
                update.setString(1, a.getCodigo());
                nPedidosModificados = update.executeUpdate();
            } else
                System.out.println("Articulo no encontrado");

        } catch (SQLException ex)
        {
            System.out.println("ERROR modificarUrgenciaArticulo: " + ex.toString());
        }
        return nPedidosModificados;
    }

    public boolean eliminarPedidoAntiguo()
    {
        Connection conexion = Conexion.getInstance().getConnection();
        boolean correcto = true;
        int nModificaciones;

        try
        {
            //Saco el mas viejo no enviado
            String sql1 = "SELECT * FROM pedidos WHERE servido = 0 ORDER BY fecha LIMIT 1";
            PreparedStatement consulta = conexion.prepareStatement(sql1);
            Pedido p = new Pedido();
            ResultSet resultConsulta = consulta.executeQuery();
            if (resultConsulta.next())
            {
                Articulo a = new Articulo();
                a.setCodigo(resultConsulta.getString("codigo_articulo"));
                p.setArticulo(a);
                Fabrica f = new Fabrica();
                f.setId(resultConsulta.getInt("id_fabrica"));
                p.setFabrica(f);
                p.setFecha(resultConsulta.getDate("fecha"));

                //Modifico -10% el precio del articulo
                String sql2 = "SELECT * FROM articulos WHERE codigo = ?";
                PreparedStatement consulta2 = conexion.prepareStatement(sql2);
                consulta2.setString(1, p.getArticulo().getCodigo());
                ResultSet resultConsulta2 = consulta2.executeQuery();
                a.setNombre(resultConsulta2.getString("nombre"));
                a.setPrecio(resultConsulta2.getDouble("precio"));

                String sql3 = "UPDATE articulos SET precio = ? WHERE codigo = ?";
                PreparedStatement update = conexion.prepareStatement(sql3);
                update.setDouble(1, (a.getPrecio() * 0.9));
                update.setString(2, a.getCodigo());
                nModificaciones = update.executeUpdate();
                if (nModificaciones > 0)
                {
                    //Elimino el pedido
                    String sql4 = "DELETE FROM pedidos "
                            + "WHERE id_fabrica = ? AND codigo_articulo = ? AND fecha = ?";
                    PreparedStatement delete = conexion.prepareStatement(sql4);
                    delete.setInt(1, p.getFabrica().getId());
                    delete.setString(2, p.getArticulo().getCodigo());
                    delete.setDate(3, p.getFecha());
                    nModificaciones = delete.executeUpdate();
                    if(nModificaciones <= 0) correcto = false;
                } else correcto = false;

            }
        } catch (SQLException ex) 
        {
            System.out.println("ERROR eliminarPedidoAntiguo " + ex.toString());
            correcto = false;
        }
        return correcto;
    }
    
//    public Propietario menosUnidades(){
//        Propietario p = new Propietario();
//        
//        return p;
//    }
    
//    public List<Articulo> articulosSinPedidos() {
//        List<Articulo> articulos = new ArrayList();
//        
//        return articulos;
//    }
}
