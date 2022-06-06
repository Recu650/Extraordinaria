package es.hectorsanchez.Ejemplo_1;

import java.time.LocalDate;
import java.util.LinkedHashSet;
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
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "publicaciones_tags", 
				joinColumns = {@JoinColumn(name = "publicacion_id")},
				inverseJoinColumns = {@JoinColumn(name = "tag_id")})
	private Set<Tag> tags = new LinkedHashSet();

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
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public Tag addTag(Tag tag) {
		this.getTags().add(tag);
		tag.getPublicaciones().add(this);
		
		return tag;
	}
	public Tag removeTag(Tag tag) {
		this.getTags().remove(tag);
		tag.getPublicaciones().remove(this);
		
		return tag;
	}
}
