package org.serratec.serratecmusic.domain;

import java.util.List;

import org.serratec.serratecmusic.enums.GeneroMusical;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "musicas")
public class Musicas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Título da música")
	@NotBlank(message = "O título da música é obrigatório")
	@Size(min = 1, max = 100)
	@Column(nullable = false)
	private String titulo;

	@Schema(description = "Duração da música em minutos")
	@NotNull(message = "A duração da música é obrigatória")
	@Column(nullable = false)
	private Integer minutos;

	@Schema(description = "Gênero Musical", example = "ROCK, POP, SAMBA, FUNK, SERTANEJO")
	@NotNull(message = "O gênero da música é obrigatório")
	@Enumerated(EnumType.STRING)
	private GeneroMusical generoMusical;

	@JsonBackReference
	@ManyToMany(mappedBy = "musicas")
	private List<Artistas> artistas;

	public Musicas() {
		super();
	}

	public Musicas(Long id, String titulo, Integer minutos, GeneroMusical generoMusical, List<Artistas> artistas) {
		this.id = id;
		this.titulo = titulo;
		this.minutos = minutos;
		this.generoMusical = generoMusical;
		this.artistas = artistas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}

	public GeneroMusical getGeneroMusical() {
		return generoMusical;
	}

	public void setGeneroMusical(GeneroMusical generoMusical) {
		this.generoMusical = generoMusical;
	}

	public List<Artistas> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artistas> artistas) {
		this.artistas = artistas;
	}
}
