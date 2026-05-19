package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeBemEstarRepository extends JpaRepository<AtividadeBemEstar, String> {

    Page<AtividadeBemEstar> findByAnimalId(String animalId, Pageable pageable);

    @Query("SELECT a FROM AtividadeBemEstar a WHERE a.animal.id = :animalId " +
            "AND LOWER(a.nomeAtividade) LIKE LOWER(CONCAT('%', :atividade, '%'))")
    Page<AtividadeBemEstar> buscarPorAtividade(@Param("animalId") String animalId,
                                               @Param("atividade") String atividade,
                                               Pageable pageable);
}
