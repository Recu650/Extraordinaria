package es.hectorsanchez.Hoja_4_Ejercicio_1;

import java.time.LocalDate;
import java.util.ArrayList;
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
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "empleados_estudios",
				joinColumns = {@JoinColumn(name = "empleado_id")},
				inverseJoinColumns = {@JoinColumn(name = "estudio_id")})
	private List<Estudio> estudios = new ArrayList();
	
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
	public List<Estudio> getEstudios() {
		return estudios;
	}
	public void setEstudios(List<Estudio> estudios) {
		this.estudios = estudios;
	}
	
	public Estudio addEstudio(Estudio estudio) {
		this.getEstudios().add(estudio);
		estudio.getEmpleados().add(this);
		
		return estudio;
	}
	public Estudio removeEstudio(Estudio estudio) {
		this.getEstudios().remove(estudio);
		estudio.getEmpleados().remove(this);
		
		return estudio;
	}
	
}
