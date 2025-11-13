package neu.gabriel.pokedex.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeName name;

    @ManyToMany
    private Set<Pokemon> pokemons;

    enum TypeName{
        AGUA,
        VENENOSO,
        FOGO,
        ELETRICO,
        DRAGAO,
        FANTASMA,
        NORMAL,
        GRAMA,
        PSIQUICO,
        LUTADOR,
        VOADOR,
        TERRESTRE,
        INSETO,
        PEDRA,
        GELO,
        FADA
    }

    public boolean equals(Object o){
        if(o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(id, type.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}