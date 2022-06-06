package es.hectorsanchez.Hoja_4_Ejercicio_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "estudios")
public class Estudio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NaturalId
	@Column(nullable = false)
	private String nombre;
	
	@OneToMany(mappedBy = "estudio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmpleadoEstudio> empleados = new ArrayList();
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<EmpleadoEstudio> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<EmpleadoEstudio> empleados) {
		this.empleados = empleados;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudio other = (Estudio) obj;
		return Objects.equals(nombre, other.nombre);
	}
		
}
