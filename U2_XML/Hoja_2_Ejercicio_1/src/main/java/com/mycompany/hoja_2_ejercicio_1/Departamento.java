package com.mycompany.hoja_2_ejercicio_1;

/**
 *
 * @author usuario
 */
public class Departamento {
    private int numero;
    private String nombre;
    private String localidad;
    private int empleados;

    public Departamento() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public int getEmpleados() {
        return empleados;
    }

    public void setEmpleados(int empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Departamento{" + "numero=" + numero + ", nombre=" + nombre + ", localidad=" + localidad + ", empleados=" + empleados + '}';
    }
    
    
}
