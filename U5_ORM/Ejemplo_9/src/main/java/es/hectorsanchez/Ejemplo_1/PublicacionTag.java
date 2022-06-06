package es.hectorsanchez.Ejemplo_1;

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
@IdClass(PublicacionTagId.class)
@Table(name = "publicaciones_tags")
public class PublicacionTag {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publicacion_id", insertable = false, updatable = false)
	private Publicacion publicacion;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id", insertable = false, updatable = false)
	private Tag tag;
	
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;

	public PublicacionTag() {
		
	}
	public PublicacionTag(Publicacion publicacion, Tag tag) {
		this.publicacion = publicacion;
		this.tag = tag;
	}
	public PublicacionTag(Publicacion publicacion, Tag tag, LocalDate fechaCreacion) {
		super();
		this.publicacion = publicacion;
		this.tag = tag;
		this.fechaCreacion = fechaCreacion;
	}
	public Publicacion getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
		PublicacionTag other = (PublicacionTag) obj;
		return Objects.equals(publicacion, other.publicacion) && Objects.equals(tag, other.tag);
	}
	
}
