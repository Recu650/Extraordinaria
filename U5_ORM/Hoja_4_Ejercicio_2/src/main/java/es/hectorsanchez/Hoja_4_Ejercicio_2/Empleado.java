package es.hectorsanchez.Hoja_4_Ejercicio_2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "empleados")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(length = 50)
	private String oficio;
	
	@Column(name = "fecha_alta")
	private LocalDate fechaAlta;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "salario", column = @Column(name = "salario", nullable = false)),
		@AttributeOverride(name = "comision", column = @Column(name = "comision"))
	})
	private Sueldo sueldo;
	
	@ManyToOne
	private Departamento departamento;
	
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmpleadoEstudio> estudios = new ArrayList();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOficio() {
		return oficio;
	}
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Sueldo getSueldo() {
		return sueldo;
	}
	public void setSueldo(Sueldo sueldo) {
		this.sueldo = sueldo;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public List<EmpleadoEstudio> getEstudios() {
		return estudios;
	}
	public void setEstudios(List<EmpleadoEstudio> estudios) {
		this.estudios = estudios;
	}
	
	public Estudio addEstudio(Estudio estudio) {
		return addEstudio(estudio, LocalDate.now());
	}
	
	public Estudio addEstudio(Estudio estudio, LocalDate fechaFin) {
		EmpleadoEstudio empleadoEstudio = new EmpleadoEstudio(this, estudio, fechaFin);
		this.estudios.add(empleadoEstudio);
		estudio.getEmpleados().add(empleadoEstudio);
		
		return estudio;
	}
	
	public Estudio removeEstudio(Estudio estudio) {
		Iterator<EmpleadoEstudio> it = this.estudios.iterator();
		while(it.hasNext()) {
			EmpleadoEstudio empleadoEstudio = it.next();
			 if(empleadoEstudio.getEmpleado().equals(this) && empleadoEstudio.getEstudio().equals(estudio)) {
				 it.remove();
				 empleadoEstudio.getEstudio().getEmpleados().remove(empleadoEstudio);
				 empleadoEstudio.setEmpleado(null);
				 empleadoEstudio.setEstudio(null);
			 }
		}
		return estudio;
	}
	
}
