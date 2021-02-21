package fr.epita.filrouge.domain.entity.serie;

public interface ISerie {


    Serie findById(String id);


    Serie create(Serie mapDtoToDomain);
}
