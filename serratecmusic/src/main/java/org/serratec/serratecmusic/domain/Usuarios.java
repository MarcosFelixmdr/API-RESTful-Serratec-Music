package org.serratec.serratecmusic.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Nome do usuário")
	@NotBlank(message = "O nome do usuário é obrigatório")
	@Size(min = 2, max = 100)
	@Column(nullable = false)
	private String nome;

	@Schema(description = "E-mail do usuário")
	@Email(message = "O e-mail informado é inválido")
	@Size(min = 2, max = 100)
	@NotBlank(message = "O e-mail é obrigatório")
	private String email;

	public Usuarios() {
		super();
	}

	public Usuarios(Long id,
			@NotBlank(message = "O nome do usuário é obrigatório") @Size(min = 2, max = 100) String nome,
			@Email(message = "O e-mail informado é inválido") @Size(min = 2, max = 100) @NotBlank(message = "O e-mail é obrigatório") String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
