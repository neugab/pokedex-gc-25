package neu.gabriel.pokedex.services;

import lombok.RequiredArgsConstructor;
import neu.gabriel.pokedex.dtos.CreatePokemonDTO;
import neu.gabriel.pokedex.dtos.PokemonResponseDTO;
import neu.gabriel.pokedex.dtos.UpdatePokemonDTO;
import neu.gabriel.pokedex.entities.Pokemon;
import neu.gabriel.pokedex.entities.Type;
import neu.gabriel.pokedex.exceptions.PokemonNotFoundException;
import neu.gabriel.pokedex.exceptions.PokemonTypeException;
import neu.gabriel.pokedex.mappers.PokemonMapper;
import neu.gabriel.pokedex.repositories.PokemonRepository;
import neu.gabriel.pokedex.repositories.TypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    private final TypeRepository typeRepository;
    private final PokemonMapper pokemonMapper;

    @Transactional
    public PokemonResponseDTO createPokemon(CreatePokemonDTO pokemonDTO) {
        validateTypes(pokemonDTO.typeIds());

        List<Type> types = typeRepository.findAllById(pokemonDTO.typeIds());

        if (types.size() != pokemonDTO.typeIds().size()) {
            throw new PokemonTypeException("Um ou mais tipos informados não existem");
        }

        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDTO.name());
        pokemon.setHeight(pokemonDTO.height());
        pokemon.setWeight(pokemonDTO.weight());
        pokemon.setAbilities(pokemonDTO.abilities());
        pokemon.setTypes(new HashSet<>(types));

        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return pokemonMapper.toResponseDTO(savedPokemon);
    }

    @Transactional(readOnly = true)
    public List<PokemonResponseDTO> getAllPokemons() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons.stream()
                .map(pokemonMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public PokemonResponseDTO getPokemonById(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokémon com ID " + id + " não encontrado"));
        return pokemonMapper.toResponseDTO(pokemon);
    }

    @Transactional
    public PokemonResponseDTO updatePokemon(Long id, UpdatePokemonDTO updateDTO) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokémon com ID " + id + " não encontrado"));

        if (updateDTO.name() != null) {
            pokemon.setName(updateDTO.name());
        }

        if (updateDTO.typeIds() != null && !updateDTO.typeIds().isEmpty()) {
            validateTypes(updateDTO.typeIds());
            List<Type> types = typeRepository.findAllById(updateDTO.typeIds());

            if (types.size() != updateDTO.typeIds().size()) {
                throw new PokemonTypeException("Um ou mais tipos informados não existem");
            }

            pokemon.setTypes(new HashSet<>(types));
        }

        if (updateDTO.height() != null) {
            pokemon.setHeight(updateDTO.height());
        }

        if (updateDTO.weight() != null) {
            pokemon.setWeight(updateDTO.weight());
        }

        if (updateDTO.abilities() != null) {
            pokemon.setAbilities(updateDTO.abilities());
        }

        Pokemon updatedPokemon = pokemonRepository.save(pokemon);
        return pokemonMapper.toResponseDTO(updatedPokemon);
    }

    @Transactional
    public void deletePokemon(Long id) {
        if (!pokemonRepository.existsById(id)) {
            throw new PokemonNotFoundException("Pokémon com ID " + id + " não encontrado");
        }
        pokemonRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PokemonResponseDTO> getPokemonsByType(Long typeId) {
        if (!typeRepository.existsById(typeId)) {
            throw new PokemonTypeException("Tipo com ID " + typeId + " não encontrado");
        }

        List<Pokemon> pokemons = pokemonRepository.findByTypeId(typeId);
        return pokemons.stream()
                .map(pokemonMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PokemonResponseDTO> getPokemonsByTypeName(String typeName) {
        List<Pokemon> pokemons = pokemonRepository.findByTypeName(typeName.toUpperCase());
        return pokemons.stream()
                .map(pokemonMapper::toResponseDTO)
                .toList();
    }

    private void validateTypes(Set<Long> typeIds) {
        if (typeIds == null || typeIds.isEmpty()) {
            throw new PokemonTypeException("Pokémon deve ter pelo menos um tipo");
        }

        if (typeIds.size() > 2) {
            throw new PokemonTypeException("Pokémon não pode ter mais de 2 tipos");
        }
    }
}