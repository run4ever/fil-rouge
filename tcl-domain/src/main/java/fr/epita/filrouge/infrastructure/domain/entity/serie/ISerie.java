package fr.epita.filrouge.infrastructure.domain.entity.serie;

public interface ISerie {


    Long create(Serie serie);

    Serie findById(Long id);


}
