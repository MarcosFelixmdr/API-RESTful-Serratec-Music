package org.serratec.serratecmusic.entity.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecmusic.domain.Playlists;
import org.serratec.serratecmusic.repository.PlaylistsRepository;
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
@RequestMapping("/playlists")
public class PlaylistsController {

	@Autowired
	private PlaylistsRepository playlistsRepository;

	@Operation(summary = "Lista todas as playlists", description = "Retorna uma lista com todas as playlists cadastradas no banco de dados.")
	@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	@GetMapping
	public ResponseEntity<List<Playlists>> listar() {
		return ResponseEntity.ok(playlistsRepository.findAll());
	}

	@Operation(summary = "Busca playlist por ID", description = "Retorna os detalhes de uma playlist específica a partir do seu identificador único.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Playlist encontrada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Playlist não encontrada") })
	@GetMapping("/{id}")
	public ResponseEntity<Playlists> buscarPorId(@PathVariable Long id) {
		Optional<Playlists> playlists = playlistsRepository.findById(id);
		return playlists.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Cria uma nova playlist", description = "Adiciona uma nova playlist ao banco de dados.")
	@ApiResponse(responseCode = "201", description = "Playlist criada com sucesso")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Playlists criarPlaylist(@Valid @RequestBody Playlists playlists) {
		return playlistsRepository.save(playlists);
	}

	@Operation(summary = "Atualiza uma playList pelo ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "playList atualizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Playlist informado não foi encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@PutMapping("/{id}")
	public ResponseEntity<Playlists> atualizar(@Valid @RequestBody Playlists playLists, @PathVariable Long id) {
		if (playlistsRepository.existsById(id)) {
			playLists.setId(id);
			return ResponseEntity.ok(playlistsRepository.save(playLists));
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Deletar uma playList pelo ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "playList deletada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Playlist informada não foi encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (playlistsRepository.existsById(id)) {
			playlistsRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
