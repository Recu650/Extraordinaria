package es.hectorsanchez.Ejemplo_1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "publicaciones")
public class Publicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Lob
	private String descripcion;
	private LocalDate fecha;
	private String titulo;
	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PublicacionTag> tags = new ArrayList();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<PublicacionTag> getTags() {
		return tags;
	}
	public void setTags(List<PublicacionTag> tags) {
		this.tags = tags;
	}

	public Tag addTag(Tag tag) {
		return addTag(tag, LocalDate.now());
	}

	public Tag addTag(Tag tag, LocalDate fechaCreacion) {
		PublicacionTag publicacionTag = new PublicacionTag(this, tag, fechaCreacion);
		this.tags.add(publicacionTag);
		tag.getPublicaciones().add(publicacionTag);

		return tag;
	}

	public Tag removeTag(Tag tag) {
		Iterator<PublicacionTag> it = tags.iterator();
		while (it.hasNext()) {
			PublicacionTag publicacionTag = it.next();

			if (publicacionTag.getPublicacion().equals(this) && publicacionTag.getTag().equals(tag)) {
				it.remove();
				publicacionTag.getTag().getPublicaciones().remove(publicacionTag);
				publicacionTag.setPublicacion(null);
				publicacionTag.setTag(null);
			}
		}
		return tag;
	}

	@Override
	public int hashCode() {
		return Objects.hash(titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publicacion other = (Publicacion) obj;
		return Objects.equals(titulo, other.titulo);
	}
	
}
