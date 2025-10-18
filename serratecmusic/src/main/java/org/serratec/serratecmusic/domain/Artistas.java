package org.serratec.serratecmusic.domain;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "artistas")
public class Artistas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Nome do artista")
	@NotBlank(message = "O nome do artista é obrigatório")
	@Size(min = 2, max = 100)
	@Column(nullable = false)
	private String nome;

	@Schema(description = "Nacionalidade do artista")
	@NotBlank(message = "A nacionalidade do artista é obrigatória")
	@Size(min = 2, max = 100)
	@Column(nullable = false)
	private String nacionalidade;

	@ManyToMany
	@JoinTable(name = "artista_musica", joinColumns = @JoinColumn(name = "artista_id"), inverseJoinColumns = @JoinColumn(name = "musica_id"))
	private List<Musicas> musicas;

	public Artistas() {
	}

	public Artistas(Long id, String nome, String nacionalidade, List<Musicas> musicas) {
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.musicas = musicas;
	}

	// Getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<Musicas> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musicas> musicas) {
		this.musicas = musicas;
	}
}
