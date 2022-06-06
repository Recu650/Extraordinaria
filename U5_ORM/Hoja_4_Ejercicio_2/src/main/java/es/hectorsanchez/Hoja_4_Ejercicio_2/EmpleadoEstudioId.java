package es.hectorsanchez.Hoja_4_Ejercicio_2;

import java.io.Serializable;
import java.util.Objects;

public class EmpleadoEstudioId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long empleado;
	private Long estudio;
	
	public Long getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Long empleado) {
		this.empleado = empleado;
	}
	public Long getEstudio() {
		return estudio;
	}
	public void setEstudio(Long estudio) {
		this.estudio = estudio;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(empleado, estudio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoEstudioId other = (EmpleadoEstudioId) obj;
		return Objects.equals(empleado, other.empleado) && Objects.equals(estudio, other.estudio);
	}
	
	
}
