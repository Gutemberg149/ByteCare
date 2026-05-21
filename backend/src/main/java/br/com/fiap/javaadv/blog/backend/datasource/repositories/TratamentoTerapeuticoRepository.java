package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface TratamentoTerapeuticoRepository extends JpaRepository<TratamentoTerapeutico, UUID> {

    // 1. Uso de JOIN FETCH para evitar LazyInitializationException e N+1
    @Query(value = "SELECT t FROM TratamentoTerapeutico t JOIN FETCH t.animal",
            countQuery = "SELECT count(t) FROM TratamentoTerapeutico t")
    Page<TratamentoTerapeutico> findAll(Pageable pageable);

    // 2. Refatorado com JOIN FETCH para carregar o Animal associado
    @Query("SELECT t FROM TratamentoTerapeutico t JOIN FETCH t.animal WHERE t.animal.id = :animalId")
    Page<TratamentoTerapeutico> findByAnimalId(@Param("animalId") UUID animalId, Pageable pageable);

    // 3. Refatorado: 't.animal.id' garante a navegabilidade correta
    @Query("SELECT t FROM TratamentoTerapeutico t JOIN FETCH t.animal WHERE t.animal.id = :animalId " +
            "AND LOWER(t.medicamento) LIKE LOWER(CONCAT('%', :medicamento, '%'))")
    Page<TratamentoTerapeutico> buscarPorMedicamento(@Param("animalId") UUID animalId,
                                                     @Param("medicamento") String medicamento,
                                                     Pageable pageable);
}