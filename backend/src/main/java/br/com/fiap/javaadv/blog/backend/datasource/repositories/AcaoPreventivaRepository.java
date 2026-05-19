//package br.com.fiap.javaadv.blog.backend.datasource.repositories;
//
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface AcaoPreventivaRepository extends JpaRepository<AcaoPreventiva, String> {
//
//    Page<AcaoPreventiva> findByAnimalId(String animalId, Pageable pageable);
//
//    @Query("SELECT a FROM AcaoPreventiva a WHERE a.animal.id = :animalId " +
//            "AND LOWER(a.nomeServico) LIKE LOWER(CONCAT('%', :nome, '%'))")
//    Page<AcaoPreventiva> buscarPorNomeServico(@Param("animalId") String animalId,
//                                              @Param("nome") String nome,
//                                              Pageable pageable);
//}

package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcaoPreventivaRepository extends JpaRepository<AcaoPreventiva, String> {

    Page<AcaoPreventiva> findByAnimalId(String animalId, Pageable pageable);

    List<AcaoPreventiva> findByAnimalId(String animalId);

    @Query("SELECT a FROM AcaoPreventiva a WHERE a.animal.id = :animalId " +
            "AND LOWER(a.nomeServico) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<AcaoPreventiva> buscarPorNomeServico(@Param("animalId") String animalId,
                                              @Param("nome") String nome,
                                              Pageable pageable);
}