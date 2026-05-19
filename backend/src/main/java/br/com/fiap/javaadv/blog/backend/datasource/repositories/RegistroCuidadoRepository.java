package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroCuidadoRepository extends JpaRepository<RegistroCuidado, String> {

    Page<RegistroCuidado> findByAnimalId(String animalId, Pageable pageable);

    Page<RegistroCuidado> findByAnimalIdAndCategoria(String animalId,
                                                     CategoriaCuidadoEnum categoria,
                                                     Pageable pageable);

    @Query("SELECT r FROM RegistroCuidado r WHERE r.animal.id = :animalId ORDER BY r.dataHoraRegistro DESC")
    Page<RegistroCuidado> buscarDiarioPorAnimal(@Param("animalId") String animalId, Pageable pageable);

    long countByAnimalIdAndCategoria(String animalId, CategoriaCuidadoEnum categoria);
}
