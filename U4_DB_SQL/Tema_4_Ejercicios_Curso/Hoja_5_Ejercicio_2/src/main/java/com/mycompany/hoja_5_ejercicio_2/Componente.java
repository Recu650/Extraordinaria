package com.mycompany.hoja_5_ejercicio_2;

import java.math.BigDecimal;

/**
 *
 * @author usuario
 */
public class Componente {
    private BigDecimal idcomp;
    private String nombre;
    private String apellido;
    private String alias;
    private String funcion;
    private Grupo grupo;

    public Componente()
    {
    }

    public BigDecimal getIdcomp()
    {
        return idcomp;
    }

    public void setIdcomp(BigDecimal idcomp)
    {
        this.idcomp = idcomp;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getFuncion()
    {
        return funcion;
    }

    public void setFuncion(String funcion)
    {
        this.funcion = funcion;
    }

    public Grupo getGrupo()
    {
        return grupo;
    }

    public void setGrupo(Grupo grupo)
    {
        this.grupo = grupo;
    }
    
    
}
