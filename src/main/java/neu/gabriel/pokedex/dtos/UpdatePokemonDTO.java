package neu.gabriel.pokedex.dtos;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UpdatePokemonDTO(
        @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
        String name,

        @Size(min = 1, max = 2, message = "Pokémon deve ter entre 1 e 2 tipos")
        Set<Long> typeIds,

        @Positive(message = "Altura deve ser um valor positivo")
        Float height,

        @Positive(message = "Peso deve ser um valor positivo")
        Float weight,

        @Size(max = 500, message = "Habilidades não podem exceder 500 caracteres")
        String abilities
) {}