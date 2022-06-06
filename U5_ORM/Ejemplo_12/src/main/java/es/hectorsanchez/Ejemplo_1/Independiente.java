package es.hectorsanchez.Ejemplo_1;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("IND")
@Table(name = "independientes")
public class Independiente extends Usuario{
	private static final long serialVersionUID = 1L;
	
	private int numeroHoras;
	private float precioHora;
	
	public Independiente()
	{
		super();
	}

	public int getNumeroHoras()
	{
		return numeroHoras;
	}
	public void setNumeroHoras(int numeroHoras)
	{
		this.numeroHoras = numeroHoras;
	}
	public float getPrecioHora()
	{
		return precioHora;
	}
	public void setPrecioHora(float precioHora)
	{
		this.precioHora = precioHora;
	}
}
