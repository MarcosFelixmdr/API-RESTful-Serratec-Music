package org.serratec.serratecmusic.entity.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecmusic.domain.Musicas;
import org.serratec.serratecmusic.repository.MusicasRepository;
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
@RequestMapping("/musicas")
public class MusicasController {

	@Autowired
	private MusicasRepository musicasRepository;

	@Operation(summary = "Lista todas as músicas", description = "Retorna uma lista com todas as músicas cadastradas no banco de dados.")
	@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	@GetMapping
	public ResponseEntity<List<Musicas>> listar() {
		return ResponseEntity.ok(musicasRepository.findAll());
	}

	@Operation(summary = "Busca música por ID", description = "Retorna os detalhes de uma música específica a partir do seu identificador único.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Música encontrada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Música não encontrada") })
	@GetMapping("/{id}")
	public ResponseEntity<Musicas> buscarPorId(@PathVariable Long id) {
		Optional<Musicas> musicas = musicasRepository.findById(id);
		return musicas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Cria uma nova música", description = "Adiciona uma nova música ao banco de dados.")
	@ApiResponse(responseCode = "201", description = "Música criada com sucesso")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Musicas criarMusica(@Valid @RequestBody Musicas musicas) {
		return musicasRepository.save(musicas);
	}
	
	
	@Operation(summary = "Atualiza uma musica pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Musica atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Musica informado não foi encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Musicas> atualizar(@Valid @RequestBody Musicas musicas, @PathVariable Long id){
        Optional<Musicas> optionalMusica = musicasRepository.findById(id);

        if(optionalMusica.isPresent()) {
            musicas.setId(id);
            musicasRepository.save(musicas);
            return ResponseEntity.ok(optionalMusica.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deleta uma musica pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Musica deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Musica informado não foi encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (musicasRepository.existsById(id)) {
			musicasRepository.deleteById(id);
			return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
