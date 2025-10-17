package org.serratec.serratecmusic.entity.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecmusic.domain.Usuarios;
import org.serratec.serratecmusic.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Operation(summary = "Lista todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados no sistema.")
	@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	@GetMapping
	public ResponseEntity<List<Usuarios>> listar() {
		return ResponseEntity.ok(usuariosRepository.findAll());
	}

	@Operation(summary = "Busca usuário por ID", description = "Retorna os detalhes de um usuário específico a partir do seu identificador único.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado") })
	@GetMapping("/{id}")
	public ResponseEntity<Usuarios> buscarPorId(@PathVariable Long id) {
		Optional<Usuarios> usuarios = usuariosRepository.findById(id);
		return usuarios.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Cria um novo usuário", description = "Adiciona um novo usuário ao banco de dados.")
	@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuarios criarUsuario(@Valid @RequestBody Usuarios usuarios) {
		return usuariosRepository.save(usuarios);
	}

	@Operation(summary = "Atualiza um usuario pelo ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Usuario informado não foi encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@PutMapping("/{id}")
	public ResponseEntity<Usuarios> atualizar(@Valid @RequestBody Usuarios usuarios, @PathVariable Long id) {
		if (usuariosRepository.existsById(id)) {
			usuarios.setId(id);
			return ResponseEntity.ok(usuariosRepository.save(usuarios));
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Deletar um usuario pelo ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Usuario informado não foi encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (usuariosRepository.existsById(id)) {
			usuariosRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
