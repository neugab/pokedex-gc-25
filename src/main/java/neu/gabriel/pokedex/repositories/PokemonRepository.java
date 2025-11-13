package neu.gabriel.pokedex.repositories;

import neu.gabriel.pokedex.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Type, Long> {
}