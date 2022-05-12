package com.mycompany.hoja_5_ejercicio_1;

import java.util.List;

/**
 *
 * @author usuario
 */

public class ComunidadAutonoma {
    private String codigo;
    private String nombre;
    private String abreviatura;
    private String nombreCapi;
    private double habitantes;
    private double habitantesCapi;
    private double extension;
    private List<String> provincias;

    public ComunidadAutonoma() {
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getAbreviatura() {
        return abreviatura;
    }
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    public String getNombreCapi() {
        return nombreCapi;
    }
    public void setNombreCapi(String nombreCapi) {
        this.nombreCapi = nombreCapi;
    }
    public double getHabitantes() {
        return habitantes;
    }
    public void setHabitantes(double habitantes) {
        this.habitantes = habitantes;
    }
    public double getHabitantesCapi() {
        return habitantesCapi;
    }
    public void setHabitantesCapi(double habitantesCapi) {
        this.habitantesCapi = habitantesCapi;
    }
    public double getExtension() {
        return extension;
    }
    public void setExtension(double extension) {
        this.extension = extension;
    }    
    public List<String> getProvincias() {
        return provincias;
    }
    public void setProvincias(List<String> provincias) {
        this.provincias = provincias;
    }
    
    public void addProvincia(String provincia){
        this.provincias.add(provincia);
    }
}
