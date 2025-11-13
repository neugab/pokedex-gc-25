package neu.gabriel.pokedex.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import neu.gabriel.pokedex.dtos.CreatePokemonDTO;
import neu.gabriel.pokedex.services.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    @PostMapping
    public ResponseEntity<Void> addPokemon(@Valid @RequestBody CreatePokemonDTO pokemonDTO) {
        pokemonService.createPokemon(pokemonDTO);
        return ResponseEntity.noContent().build();
    }
}
