package neu.gabriel.pokedex.repositories;

import neu.gabriel.pokedex.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    @Query("SELECT DISTINCT p FROM Pokemon p JOIN p.types t WHERE t.id = :typeId")
    List<Pokemon> findByTypeId(@Param("typeId") Long typeId);

    @Query("SELECT DISTINCT p FROM Pokemon p JOIN p.types t WHERE t.name = :typeName")
    List<Pokemon> findByTypeName(@Param("typeName") String typeName);
}