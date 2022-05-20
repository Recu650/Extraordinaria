package com.mycompany.hoja_5_ejercicio_2;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Grupo
{
    private BigDecimal codgrupo;
    private String nombre;
    private String localidad;
    private String estilo;
    private boolean esgrupo;
    private int annograb;
    private Date fechaestreno;
    private String compania;
    private List<Cancion> canciones;
    private List<Componente> componentes;

    public Grupo()
    {
        canciones = new ArrayList();
        componentes = new ArrayList();
    }

    public void addCancion(Cancion cancion)
    {
        this.canciones.add(cancion);
    }

    public List<Cancion> getCanciones()
    {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones)
    {
        this.canciones = canciones;
    }
    
    public void addComponente(Componente componente){
        this.componentes.add(componente);
    }
    public List<Componente> getComponentes()
    {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes)
    {
        this.componentes = componentes;
    }
    
    public BigDecimal getCodgrupo()
    {
        return codgrupo;
    }

    public void setCodgrupo(BigDecimal codgrupo)
    {
        this.codgrupo = codgrupo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getLocalidad()
    {
        return localidad;
    }

    public void setLocalidad(String localidad)
    {
        this.localidad = localidad;
    }

    public String getEstilo()
    {
        return estilo;
    }

    public void setEstilo(String estilo)
    {
        this.estilo = estilo;
    }

    public boolean isEsgrupo()
    {
        return esgrupo;
    }

    public void setEsgrupo(boolean esgrupo)
    {
        this.esgrupo = esgrupo;
    }

    public int getAnnograb()
    {
        return annograb;
    }

    public void setAnnograb(int annograb)
    {
        this.annograb = annograb;
    }

    public Date getFechaestreno()
    {
        return fechaestreno;
    }

    public void setFechaestreno(Date fechaestreno)
    {
        this.fechaestreno = fechaestreno;
    }

    public String getCompania()
    {
        return compania;
    }

    public void setCompania(String compania)
    {
        this.compania = compania;
    }

}
