package es.hectorsanchez.Ejemplo_1;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="propietarios")
@NamedQuery(name="Propietario.findAll", query="SELECT p FROM Propietario p")
public class Propietario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DNI")
	private String dni;

	private int edad;

	private String nombre;

	//bi-directional many-to-one association to Coche
	@OneToMany(mappedBy="propietario")
	private List<Coche> coches;

	public Propietario() {
	}
	public String getDni() {
		return this.dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getEdad() {
		return this.edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Coche> getCoches() {
		return this.coches;
	}
	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}
	public Coche addCoche(Coche coche) {
		getCoches().add(coche);
		coche.setPropietario(this);

		return coche;
	}
	public Coche removeCoche(Coche coche) {
		getCoches().remove(coche);
		coche.setPropietario(null);

		return coche;
	}
}
