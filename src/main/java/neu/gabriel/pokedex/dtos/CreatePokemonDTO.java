package neu.gabriel.pokedex.dtos;

import java.util.Set;

public record CreatePokemonDTO(
        String name,
        Set<Long> ids,
        Float height,
        Float weight
){}
