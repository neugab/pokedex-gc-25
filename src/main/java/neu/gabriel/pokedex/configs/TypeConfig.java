package neu.gabriel.pokedex.configs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import neu.gabriel.pokedex.entities.Type;
import neu.gabriel.pokedex.repositories.TypeRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TypeConfig {
    private final TypeRepository typeRepository;

    @PostConstruct
    public void init() {
        // Verifica se já existem tipos cadastrados para não duplicar
        if (typeRepository.count() > 0) {
            return;
        }

        // Cria todos os tipos
        createType(Type.TypeName.AGUA);
        createType(Type.TypeName.VENENOSO);
        createType(Type.TypeName.FOGO);
        createType(Type.TypeName.ELETRICO);
        createType(Type.TypeName.DRAGAO);
        createType(Type.TypeName.FANTASMA);
        createType(Type.TypeName.NORMAL);
        createType(Type.TypeName.GRAMA);
        createType(Type.TypeName.PSIQUICO);
        createType(Type.TypeName.LUTADOR);
        createType(Type.TypeName.VOADOR);
        createType(Type.TypeName.TERRESTRE);
        createType(Type.TypeName.INSETO);
        createType(Type.TypeName.PEDRA);
        createType(Type.TypeName.GELO);
        createType(Type.TypeName.FADA);
    }

    private void createType(Type.TypeName typeName) {
        Type type = new Type();
        type.setName(typeName);
        typeRepository.save(type);
    }
}