package org.serratec.serratecmusic.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "playlists")
public class Playlists {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Nome da playlist")
	@NotBlank(message = "O nome da playlist é obrigatório")
	@Size(min = 2, max = 100)
	@Column(nullable = false)
	private String nome;

	@Schema(description = "Descrição da playlist")
	@NotBlank(message = "A descrição da playlist é obrigatória")
	@Size(min = 2, max = 255)
	@Column(nullable = false)
	private String descricao;

	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "playlist_musica", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "musica_id"))
	private List<Musicas> musicas;

	public Playlists() {
		super();
	}

	public Playlists(Long id, String nome, String descricao, List<Musicas> musicas) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.musicas = musicas;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Musicas> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musicas> musicas) {
		this.musicas = musicas;
	}
}
