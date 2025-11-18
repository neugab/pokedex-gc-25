package neu.gabriel.pokedex.configs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import neu.gabriel.pokedex.entities.Type;
import neu.gabriel.pokedex.repositories.TypeRepository;

@RequiredArgsConstructor

public class TypeConfig {
    private final TypeRepository typeRepository;

    @PostConstruct
    public void init(){
        Type type= new Type();
        type.setId(null);
        type.setName(Type.TypeName.AGUA);
        typeRepository.save(type);

        Type type2= new Type();
        type.setId(null);
        type.setName(Type.TypeName.VENENOSO);
        typeRepository.save(type2);

        Type type3= new Type();
        type.setId(null);
        type.setName(Type.TypeName.FOGO);
        typeRepository.save(type3);

        Type type4= new Type();
        type.setId(null);
        type.setName(Type.TypeName.ELETRICO);
        typeRepository.save(type4);

        Type type5= new Type();
        type.setId(null);
        type.setName(Type.TypeName.DRAGAO);
        typeRepository.save(type5);

        Type type6= new Type();
        type.setId(null);
        type.setName(Type.TypeName.FANTASMA);
        typeRepository.save(type6);

        Type type7= new Type();
        type.setId(null);
        type.setName(Type.TypeName.NORMAL);
        typeRepository.save(type7);

        Type type8= new Type();
        type.setId(null);
        type.setName(Type.TypeName.GRAMA);
        typeRepository.save(type8);

        Type type9= new Type();
        type.setId(null);
        type.setName(Type.TypeName.PSIQUICO);
        typeRepository.save(type9);

        Type type10= new Type();
        type.setId(null);
        type.setName(Type.TypeName.LUTADOR);
        typeRepository.save(type10);

        Type type11= new Type();
        type.setId(null);
        type.setName(Type.TypeName.VOADOR);
        typeRepository.save(type11);

        Type type12= new Type();
        type.setId(null);
        type.setName(Type.TypeName.TERRESTRE);
        typeRepository.save(type12);

        Type type13= new Type();
        type.setId(null);
        type.setName(Type.TypeName.INSETO);
        typeRepository.save(type13);

        Type type14= new Type();
        type.setId(null);
        type.setName(Type.TypeName.PEDRA);
        typeRepository.save(type14);

        Type type15= new Type();
        type.setId(null);
        type.setName(Type.TypeName.GELO);
        typeRepository.save(type15);

        Type type16= new Type();
        type.setId(null);
        type.setName(Type.TypeName.FADA);
        typeRepository.save(type16);

    }
}