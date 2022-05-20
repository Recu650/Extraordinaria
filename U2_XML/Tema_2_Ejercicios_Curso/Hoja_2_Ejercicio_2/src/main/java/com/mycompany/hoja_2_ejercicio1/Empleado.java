/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_2_ejercicio1;

/**
 *
 * @author usuario
 */
public class Empleado {
    private String nombre;
    private String puesto;
    private float salario;

    public Empleado() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", puesto=" + puesto + ", salario=" + salario + '}';
    }
    
    
    
}
