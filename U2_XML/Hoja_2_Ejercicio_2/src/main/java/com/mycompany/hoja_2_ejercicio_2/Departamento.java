package com.mycompany.hoja_2_ejercicio_2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Departamento {
    private String codigo;
    private String telefono;
    private String tipo;
    private String nombre;
    private List<Empleado> empleados;

    public Departamento() {
        this.empleados = new ArrayList();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    
    public boolean insertarEmpleado(Empleado empleado){
        return this.empleados.add(empleado);
    }

    @Override
    public String toString() {
        String txtEmpleados = "";
        for(Empleado emp: this.empleados){
            txtEmpleados = txtEmpleados + "\tEmpleado: " + emp.getNombre() + "\n\t\tPuesto: " + emp.getPuesto()
                           + "\n\t\tSalario: " + emp.getSalario() + "\n";
        }
        return "Departamento: " + this.getNombre() + " (" + this.getCodigo() + ") \n"
                + "Telefono: " + this.getTelefono() + "\n"
                + "Tipo: " + this.getTipo() + "\n"
                + "Empleados: \n" + txtEmpleados;
    }

    

}
