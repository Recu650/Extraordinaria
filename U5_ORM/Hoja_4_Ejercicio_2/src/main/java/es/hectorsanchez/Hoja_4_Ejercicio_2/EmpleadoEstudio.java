package es.hectorsanchez.Hoja_4_Ejercicio_2;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(EmpleadoEstudioId.class)
@Table(name = "empleados_estudios")
public class EmpleadoEstudio {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empleado_id", insertable = false, updatable = false)
	private Empleado empleado;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estudio_id", insertable = false, updatable = false)
	private Estudio estudio;
	
	@Column(name = "fecha_finalizacion")
	private LocalDate fechaFin;

	public EmpleadoEstudio() {
		
	}
	public EmpleadoEstudio(Empleado empleado, Estudio estudio) {
		this.empleado = empleado;
		this.estudio = estudio;
	}
	public EmpleadoEstudio(Empleado empleado, Estudio estudio, LocalDate fechaFin) {
		this.empleado = empleado;
		this.estudio = estudio;
		this.fechaFin = fechaFin;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Estudio getEstudio() {
		return estudio;
	}
	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
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
		EmpleadoEstudio other = (EmpleadoEstudio) obj;
		return Objects.equals(empleado, other.empleado) && Objects.equals(estudio, other.estudio);
	}
	
}
