package es.hectorsanchez.Ejemplo_1;

import java.io.Serializable;
import java.util.Objects;

public class PublicacionTagId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long publicacion;
	private Long tag;
	
	public Long getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(Long publicacion) {
		this.publicacion = publicacion;
	}
	public Long getTag() {
		return tag;
	}
	public void setTag(Long tag) {
		this.tag = tag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(publicacion, tag);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublicacionTagId other = (PublicacionTagId) obj;
		return Objects.equals(publicacion, other.publicacion) && Objects.equals(tag, other.tag);
	}
	
}
