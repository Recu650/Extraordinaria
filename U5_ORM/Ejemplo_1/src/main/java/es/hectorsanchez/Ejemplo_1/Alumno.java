package es.hectorsanchez.Ejemplo_1;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alumnos") //Añade nombre a la tabla
public class Alumno {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Autoincrementable
	private int id;
	
	@Column(nullable = false, length = 20) //No nulo + max 20 caracteres
	private String nombre;
	private float nota;
	
	@Embedded
	private Direccion direccion;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "calle", column = @Column(name = "calle_facturacion")),
		@AttributeOverride(name = "numero", column = @Column(name = "numero_facturacion"))
	})
	private Direccion direccionFacturacion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public Direccion getDireccionFacturacion() {
		return direccionFacturacion;
	}
	public void setDireccionFacturacion(Direccion direccionFacturacion) {
		this.direccionFacturacion = direccionFacturacion;
	}
	
	
	
	
}
