package es.hectorsanchez.Ejemplo_1;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
	@Column
	private String calle;
	@Column
	private int numero;
	
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
