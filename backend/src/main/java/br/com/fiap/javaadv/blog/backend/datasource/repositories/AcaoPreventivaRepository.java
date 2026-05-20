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

//package br.com.fiap.javaadv.blog.backend.datasource.repositories;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.UUID;
//
//@Repository
//public interface AcaoPreventivaRepository extends JpaRepository<AcaoPreventiva, UUID> {
//
//    // Correção do Erro: Tipo alterado de String para UUID para casar com a Entidade
//    List<AcaoPreventiva> findByAnimalId(UUID animalId);
//
//    // Refatorado: Retorna uma List direta (sem paginação) e com busca textual segura
//    @Query("SELECT a FROM AcaoPreventiva a WHERE a.animal.id = :animalId " +
//            "AND LOWER(a.nomeServico) LIKE LOWER(CONCAT('%', :nome, '%'))")
//    List<AcaoPreventiva> buscarPorNomeServico(@Param("animalId") UUID animalId,
//                                              @Param("nome") String nome);
//}

package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AcaoPreventivaRepository extends JpaRepository<AcaoPreventiva, UUID> {

    /**
     * Busca todas as ações preventivas carregando os dados do animal
     * para evitar LazyInitializationException.
     */
    @Override
    @EntityGraph(attributePaths = {"animal"})
    List<AcaoPreventiva> findAll();

    /**
     * Busca uma ação específica pelo ID carregando os dados do animal.
     */
    @Override
    @EntityGraph(attributePaths = {"animal"})
    Optional<AcaoPreventiva> findById(UUID id);

    /**
     * Busca todas as ações de um determinado animal com JOIN automático.
     */
    @EntityGraph(attributePaths = {"animal"})
    List<AcaoPreventiva> findByAnimalId(UUID animalId);

    /**
     * Busca por nome de serviço e animal usando JOIN FETCH manual
     * para otimizar a query com cláusulas WHERE.
     */
    @Query("SELECT a FROM AcaoPreventiva a JOIN FETCH a.animal WHERE a.animal.id = :animalId " +
            "AND LOWER(a.nomeServico) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<AcaoPreventiva> buscarPorNomeServico(@Param("animalId") UUID animalId,
                                              @Param("nome") String nome);
}