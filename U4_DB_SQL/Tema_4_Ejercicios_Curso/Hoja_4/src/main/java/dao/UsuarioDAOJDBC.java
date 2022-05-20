package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import util.Conexion;

/**
 *
 * @author usuario
 */
public class UsuarioDAOJDBC implements UsuarioDAO
{

    private final Connection conexion;

    public UsuarioDAOJDBC()
    {
        this.conexion = Conexion.getInstance().getConnection();
    }

    @Override
    public boolean insertar(Usuario usuario)
    {
        boolean insertado = false;
        PreparedStatement consultaPreparada = null;
        String sql = "INSERT INTO usuarios (nombre, apellidos, \"user\", contraseña, fechanac)"
                + "VALUES(?,?,?,md5(?),?)";
        try 
        {
            consultaPreparada = conexion.prepareStatement(sql);
            
            consultaPreparada.setString(1, usuario.getNombre());
            consultaPreparada.setString(2, usuario.getApellidos());
            consultaPreparada.setString(3, usuario.getUsuario());
            consultaPreparada.setString(4, usuario.getContraseña());
            consultaPreparada.setObject(5, usuario.getFechaNacimiento());
            
            int n = consultaPreparada.executeUpdate();
            if(n == 1){
                System.out.println("Insertado Correctamente");
                insertado = true;
            }
        } catch (SQLException ex)
        {
            System.out.println("Error del PreparedStatement");
            System.out.println(ex.toString());
        }
        return insertado;
    }

    @Override
    public void actualizar(Usuario entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Usuario entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buscar(Usuario entity)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> getTodos()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> getUsuariosMayoresEdad()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
