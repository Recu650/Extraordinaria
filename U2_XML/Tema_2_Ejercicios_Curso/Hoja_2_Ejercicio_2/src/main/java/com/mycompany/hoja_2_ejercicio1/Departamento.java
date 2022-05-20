/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_2_ejercicio1;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author usuario
 */
public class Departamento {
    private String nombre;
    private String telefono;
    private String tipo;
    private String codigo;
    private List<Empleado> empleados;

    public Departamento() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        String textoEmpleados = empleados.stream()
                .map(e -> e.toString())
                .collect(Collectors.joining("\n"));
        return "Departamento" + nombre + " (" + codigo +
                ") con telefono " + telefono +
                ", tipo " + tipo + "\n" + textoEmpleados;
    }
    
    public boolean insertarEmpleado(Empleado empleado){
        return this.empleados.add(empleado);
    }
}
