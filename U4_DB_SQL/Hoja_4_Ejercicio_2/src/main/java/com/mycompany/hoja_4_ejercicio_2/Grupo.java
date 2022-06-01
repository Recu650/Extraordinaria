package com.mycompany.hoja_4_ejercicio_2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Grupo {
    private BigDecimal codgrupo;
    private String nombre;
    private String localidad;
    private String estilo;
    private boolean esgrupo;
    private int annograb;
    private Date fechaestreno;
    private String compania;
    private List<Cancion> canciones;
    
    public Grupo(){
        this.canciones = new ArrayList();
    }
    
    public void addCancion(Cancion cancion){
        this.canciones.add(cancion);
    }
    public List<Cancion> getCanciones() {
        return canciones;
    }
    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
    public BigDecimal getCodgrupo() {
        return codgrupo;
    }
    public void setCodgrupo(BigDecimal codgrupo) {
        this.codgrupo = codgrupo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getEstilo() {
        return estilo;
    }
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
    public boolean isEsgrupo() {
        return esgrupo;
    }
    public void setEsgrupo(boolean esgrupo) {
        this.esgrupo = esgrupo;
    }
    public int getAnnograb() {
        return annograb;
    }
    public void setAnnograb(int annograb) {
        this.annograb = annograb;
    }
    public Date getFechaestreno() {
        return fechaestreno;
    }
    public void setFechaestreno(Date fechaestreno) {
        this.fechaestreno = fechaestreno;
    }
    public String getCompania() {
        return compania;
    }
    public void setCompania(String compania) {
        this.compania = compania;
    }   
}
