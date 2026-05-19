package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamentoTerapeuticoRepository extends JpaRepository<TratamentoTerapeutico, String> {

    Page<TratamentoTerapeutico> findByAnimalId(String animalId, Pageable pageable);

    @Query("SELECT t FROM TratamentoTerapeutico t WHERE t.animal.id = :animalId " +
            "AND LOWER(t.nomeMedicamento) LIKE LOWER(CONCAT('%', :medicamento, '%'))")
    Page<TratamentoTerapeutico> buscarPorMedicamento(@Param("animalId") String animalId,
                                                     @Param("medicamento") String medicamento,
                                                     Pageable pageable);
}
