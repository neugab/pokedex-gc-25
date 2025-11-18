package neu.gabriel.pokedex.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokemon_id")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name= "tipo_pokemon",
            joinColumns = @JoinColumn(name= "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_id")
    )
    private Set<Type> type = new HashSet<>();
    private Float height;
    private Float weight;

    @Override
    public boolean equals(Object o){
        if(o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon= (Pokemon) o;
        return Objects.equals(id, pokemon.id);
    }

    @Override
    public int hashCode() {return Objects.hashCode(id);}

}
