package es.hectorsanchez.Ejemplo_1;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "coches")
@NamedQuery(name = "Coche.findAll", query = "SELECT c FROM Coche c")
public class Coche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String matricula;

	private String marca;

	private float precio;

	// bi-directional many-to-one association to Propietario
	@ManyToOne
	@JoinColumn(name = "DNI")
	private Propietario propietario;

	public Coche() {
	}
	public String getMatricula() {
		return this.matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return this.marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public float getPrecio() {
		return this.precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Propietario getPropietario() {
		return this.propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
}
