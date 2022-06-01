package es.hectorsanchez.Hoja_2;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
	private long id;
	private String nombre;
	private String localidad;
	private List<Empleado> empleados = new ArrayList();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	public Empleado addEmpleado(Empleado empleado) {
		this.getEmpleados().add(empleado);
		empleado.setDepartamento(this);
		
		return empleado;
	}
	public Empleado removeEmpleado(Empleado empleado) {
		this.getEmpleados().remove(empleado);
		empleado.setDepartamento(null);
		
		return empleado;
	}
	
}
