package org.serratec.serratecmusic.entity.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecmusic.domain.Artistas;
import org.serratec.serratecmusic.repository.ArtistasRepository;
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
@RequestMapping("/artistas")
public class ArtistasController {

	@Autowired
	private ArtistasRepository artistasRepository;

	@Operation(summary = "Lista todos os artistas", description = "Retorna uma lista com todos os artistas cadastrados.")
	@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	@ApiResponse(responseCode = "400", description = "Erro ao lista artistas")
	@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	@GetMapping
	public ResponseEntity<List<Artistas>> listar() {
		return ResponseEntity.ok(artistasRepository.findAll());
	}

	@Operation(summary = "Busca artista por ID", description = "Retorna os dados de um artista específico com base no seu ID.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Artista encontrado"),
			@ApiResponse(responseCode = "404", description = "Artista não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })

	@GetMapping("/{id}")
	public ResponseEntity<Artistas> buscarPorId(@PathVariable Long id) {
		Optional<Artistas> artistas = artistasRepository.findById(id);
		return artistas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Cria um novo artista", description = "Adiciona um novo artista ao banco de dados.")
	@ApiResponse(responseCode = "201", description = "Artista criado com sucesso")
	@ApiResponse(responseCode = "400", description = "Erro ao cadastrar Artista")
	@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Artistas criarArtista(@Valid @RequestBody Artistas artistas) {
		return artistasRepository.save(artistas);
	}

	@Operation(summary = "Atualiza um Artista pelo ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Artista atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Artista informado não foi encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@PutMapping("/{id}")
	public ResponseEntity<Artistas> atualizar(@Valid @RequestBody Artistas artistas, Long id) {
		if (artistasRepository.existsById(id)) {
			artistas.setId(id);
			artistasRepository.save(artistas);
			return ResponseEntity.ok(artistas);
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Deleta um Artista pelo ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Artista deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Artista informado não foi encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Artistas> deletar(@Valid @RequestBody Artistas artistas, Long id) {
		if (artistasRepository.existsById(id)) {
			artistas.setId(id);
			artistasRepository.save(artistas);
			return ResponseEntity.ok(artistas);
		}
		return ResponseEntity.notFound().build();
	}
}
