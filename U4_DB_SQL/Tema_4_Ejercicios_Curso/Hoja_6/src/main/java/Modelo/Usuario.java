package Modelo;

import java.sql.Date;



/**
 *
 * @author usuario
 */

public class Usuario {
    //ATRIBUTOS-----------------------------------------------------------------------------------
    private int id;
    private String usuario;
    private String password;
    private String nombre;
    private String apellidos;
    private Date fecha_nacimiento;
    private int num_accesos;
    private int test_realizados;
    private float puntuacion_media;
    //CONSTRUCTOR----------------------------------------------------------------------------------
    public Usuario()
    {
    }
    //GETTER_&_SETTER-------------------------------------------------------------------------------
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public Date getFecha_nacimiento()
    {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento)
    {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getNum_accesos()
    {
        return num_accesos;
    }

    public void setNum_accesos(int num_accesos)
    {
        this.num_accesos = num_accesos;
    }

    public int getTest_realizados()
    {
        return test_realizados;
    }

    public void setTest_realizados(int test_realizados)
    {
        this.test_realizados = test_realizados;
    }

    public float getPuntuacion_media()
    {
        return puntuacion_media;
    }

    public void setPuntuacion_media(float puntuacion_media)
    {
        this.puntuacion_media = puntuacion_media;
    }
    
}
