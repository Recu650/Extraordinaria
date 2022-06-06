package es.hectorsanchez.Ejemplo_1;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado extends Usuario{
	private static final long serialVersionUID = 1L;
	
	private float sueldo;

	public Empleado() {
		super();
	}

	public float getSueldo() {
		return sueldo;
	}
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
	
}
