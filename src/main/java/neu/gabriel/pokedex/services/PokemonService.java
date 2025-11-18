package neu.gabriel.pokedex.services;

import lombok.RequiredArgsConstructor;
import neu.gabriel.pokedex.dtos.CreatePokemonDTO;
import neu.gabriel.pokedex.entities.Pokemon;
import neu.gabriel.pokedex.entities.Type;
import neu.gabriel.pokedex.repositories.PokemonRepository;
import neu.gabriel.pokedex.repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    private final TypeRepository typeRepository;

    public void createPokemon(CreatePokemonDTO pokemonDTO){
        List<Type> types = typeRepository.findAllById(pokemonDTO.ids());
        var pokemon = new Pokemon();
        pokemon.setId(null);
        pokemon.setName(pokemonDTO.name());
        pokemon.setHeight(pokemonDTO.height());
        pokemon.setWeight(pokemonDTO.weight());
        pokemon.setType(new HashSet<>(types));
    }
}

