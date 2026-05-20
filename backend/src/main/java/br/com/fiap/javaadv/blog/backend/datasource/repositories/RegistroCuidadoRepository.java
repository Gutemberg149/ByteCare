//package br.com.fiap.javaadv.blog.backend.datasource.repositories;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface RegistroCuidadoRepository extends JpaRepository<RegistroCuidado, String> {
//
//    Page<RegistroCuidado> findByAnimalId(String animalId, Pageable pageable);
//
//    Page<RegistroCuidado> findByAnimalIdAndCategoria(String animalId,
//                                                     CategoriaCuidadoEnum categoria,
//                                                     Pageable pageable);
//
//    @Query("SELECT r FROM RegistroCuidado r WHERE r.animal.id = :animalId ORDER BY r.dataHoraRegistro DESC")
//    Page<RegistroCuidado> buscarDiarioPorAnimal(@Param("animalId") String animalId, Pageable pageable);
//
//    long countByAnimalIdAndCategoria(String animalId, CategoriaCuidadoEnum categoria);
//}
package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RegistroCuidadoRepository extends JpaRepository<RegistroCuidado, UUID> {

    // CORRIGIDO: String -> UUID
    Page<RegistroCuidado> findByAnimalId(UUID animalId, Pageable pageable);

    // CORRIGIDO: String -> UUID
    Page<RegistroCuidado> findByAnimalIdAndCategoria(UUID animalId,
                                                     CategoriaCuidadoEnum categoria,
                                                     Pageable pageable);

    // CORRIGIDO: Tipo alterado para UUID e removido o ORDER BY por um campo inexistente (dataHoraRegistro)
    @Query("SELECT r FROM RegistroCuidado r WHERE r.animalId = :animalId")
    Page<RegistroCuidado> buscarDiarioPorAnimal(@Param("animalId") UUID animalId, Pageable pageable);

    // CORRIGIDO: String -> UUID
    long countByAnimalIdAndCategoria(UUID animalId, CategoriaCuidadoEnum categoria);
}