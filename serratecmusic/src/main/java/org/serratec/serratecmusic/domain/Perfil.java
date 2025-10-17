package org.serratec.serratecmusic.domain;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "perfil")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Nome do perfil")
	@NotBlank(message = "O nome do perfil é obrigatório")
	@Size(min = 2, max = 100)
	@Column(nullable = false)
	private String nome;

	@Schema(description = "Telefone")
	@NotBlank(message = "Telefone é obrigatório")
	@Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
	@Column(nullable = false)
	private String telefone;

	@Schema(description = "Data de nascimento")
	@NotBlank(message = "A data de nascimento é obrigatória")
	@PastOrPresent(message = "A data de nascimento não pode ser no futuro")
	@Size(max = 8, message = "A data de nascimento informada deve ser no formato: DD/MM/AAAA")
	@Column(nullable = false)
	private LocalDate dataNascimento;

	public Perfil() {
		super();
	}

	public Perfil(Long id, @NotBlank(message = "O nome do perfil é obrigatório") @Size(min = 2, max = 100) String nome,
			@NotBlank(message = "Telefone é obrigatório") @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres") String telefone,
			@NotBlank(message = "A data de nascimento é obrigatória") @PastOrPresent(message = "A data de nascimento não pode ser no futuro") @Size(max = 8, message = "A data de nascimento informada deve ser no formato: DD/MM/AAAA") LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
