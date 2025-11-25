package neu.gabriel.pokedex.mappers;

import neu.gabriel.pokedex.dtos.PokemonResponseDTO;
import neu.gabriel.pokedex.dtos.TypeResponseDTO;
import neu.gabriel.pokedex.entities.Pokemon;
import neu.gabriel.pokedex.entities.Type;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PokemonMapper {

    public PokemonResponseDTO toResponseDTO(Pokemon pokemon) {
        return new PokemonResponseDTO(
                pokemon.getId(),
                pokemon.getName(),
                toTypeResponseDTOs(pokemon.getTypes()),
                pokemon.getHeight(),
                pokemon.getWeight(),
                pokemon.getAbilities()
        );
    }

    public TypeResponseDTO toTypeResponseDTO(Type type) {
        return new TypeResponseDTO(
                type.getId(),
                type.getName().name()
        );
    }

    public Set<TypeResponseDTO> toTypeResponseDTOs(Set<Type> types) {
        return types.stream()
                .map(this::toTypeResponseDTO)
                .collect(Collectors.toSet());
    }
}