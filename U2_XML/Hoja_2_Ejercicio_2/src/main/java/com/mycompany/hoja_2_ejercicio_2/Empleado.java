package com.mycompany.hoja_2_ejercicio_2;

/**
 *
 * @author usuario
 */
public class Empleado {
    private String puesto;
    private String nombre;
    private int salario;

    public Empleado() {
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "puesto=" + puesto + ", nombre=" + nombre + ", salario=" + salario + '}';
    }
    
}
