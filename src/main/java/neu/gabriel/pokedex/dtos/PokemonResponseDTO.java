package neu.gabriel.pokedex.dtos;

import java.util.Set;

public record PokemonResponseDTO(
        Long id,
        String name,
        Set<TypeResponseDTO> types,
        Float height,
        Float weight,
        String abilities
) {}