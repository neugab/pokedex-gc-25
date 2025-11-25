package neu.gabriel.pokedex.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import neu.gabriel.pokedex.dtos.CreatePokemonDTO;
import neu.gabriel.pokedex.dtos.PokemonResponseDTO;
import neu.gabriel.pokedex.dtos.UpdatePokemonDTO;
import neu.gabriel.pokedex.exceptions.ErrorMessage;
import neu.gabriel.pokedex.services.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pokemons")
@Tag(name = "Pokémon", description = "Endpoints para gerenciamento de Pokémon")
public class PokemonController {

    private final PokemonService pokemonService;

    @PostMapping
    @Operation(summary = "Cadastrar novo Pokémon", description = "Cria um novo Pokémon no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pokémon criado com sucesso",
                    content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class))),
            @ApiResponse(responseCode = "422", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<PokemonResponseDTO> createPokemon(
            @Valid @RequestBody CreatePokemonDTO pokemonDTO) {
        PokemonResponseDTO response = pokemonService.createPokemon(pokemonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os Pokémon", description = "Retorna uma lista com todos os Pokémon cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de Pokémon retornada com sucesso")
    public ResponseEntity<List<PokemonResponseDTO>> getAllPokemons() {
        List<PokemonResponseDTO> pokemons = pokemonService.getAllPokemons();
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Pokémon por ID", description = "Retorna um Pokémon específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pokémon encontrado",
                    content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pokémon não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<PokemonResponseDTO> getPokemonById(
            @Parameter(description = "ID do Pokémon") @PathVariable Long id) {
        PokemonResponseDTO pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Pokémon", description = "Atualiza os dados de um Pokémon existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pokémon atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = PokemonResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pokémon não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<PokemonResponseDTO> updatePokemon(
            @Parameter(description = "ID do Pokémon") @PathVariable Long id,
            @Valid @RequestBody UpdatePokemonDTO updateDTO) {
        PokemonResponseDTO updatedPokemon = pokemonService.updatePokemon(id, updateDTO);
        return ResponseEntity.ok(updatedPokemon);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Pokémon", description = "Remove um Pokémon do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pokémon deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pokémon não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<Void> deletePokemon(
            @Parameter(description = "ID do Pokémon") @PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo/{typeId}")
    @Operation(summary = "Filtrar Pokémon por tipo", description = "Retorna todos os Pokémon de um tipo específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Pokémon retornada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Tipo não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<List<PokemonResponseDTO>> getPokemonsByType(
            @Parameter(description = "ID do tipo") @PathVariable Long typeId) {
        List<PokemonResponseDTO> pokemons = pokemonService.getPokemonsByType(typeId);
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/tipo/nome/{typeName}")
    @Operation(summary = "Filtrar Pokémon por nome do tipo",
            description = "Retorna todos os Pokémon de um tipo específico pelo nome (ex: FOGO, AGUA)")
    @ApiResponse(responseCode = "200", description = "Lista de Pokémon retornada com sucesso")
    public ResponseEntity<List<PokemonResponseDTO>> getPokemonsByTypeName(
            @Parameter(description = "Nome do tipo (AGUA, FOGO, GRAMA, etc.)")
            @PathVariable String typeName) {
        List<PokemonResponseDTO> pokemons = pokemonService.getPokemonsByTypeName(typeName);
        return ResponseEntity.ok(pokemons);
    }
}