package neu.gabriel.pokedex.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class PokemonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeName name;

    private Set<Pokemon> pokemons;

    enum TypeName{
        AGUA,
        VENENOSO,
        FOGO
    }
}
