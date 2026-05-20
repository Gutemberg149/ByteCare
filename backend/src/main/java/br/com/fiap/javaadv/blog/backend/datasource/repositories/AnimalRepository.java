//package br.com.fiap.javaadv.blog.backend.datasource.repositories;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface AnimalRepository extends JpaRepository<Animal, String> {
//
//
//    List<Animal> findByAtivoTrue();
//
//    List<Animal> findByAtivoFalse();
//
//    Page<Animal> findByAtivoTrue(Pageable pageable);
//
//    Page<Animal> findByAtivoFalse(Pageable pageable);
//
//    // ========== BUSCAS POR TIPO ==========
//
//    List<Animal> findByTipo(TipoAnimalEnum tipo);
//
//    List<Animal> findByTipoAndAtivoTrue(TipoAnimalEnum tipo);
//
//    Page<Animal> findByTipo(TipoAnimalEnum tipo, Pageable pageable);
//
//    // ========== BUSCAS POR NOME ==========
//
//    Optional<Animal> findByNome(String nome);
//
//    List<Animal> findByNomeContainingIgnoreCase(String nome);
//
//    List<Animal> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
//
//    boolean existsByNomeIgnoreCase(String nome);
//
//    // ========== BUSCAS POR DATA ==========
//
////    List<Animal> findByDataNascimentoAfter(LocalDate data);
//
//    List<Animal> findByDataNascimentoBefore(LocalDate data);
//
//    List<Animal> findByDataNascimentoBetween(LocalDate inicio, LocalDate fim);
//
//    // ========== BUSCAS POR RAÇA ==========
//
//    List<Animal> findByRaca(String raca);
//
//    List<Animal> findByRacaIgnoreCase(String raca);
//
//    // ========== BUSCAS COMBINADAS ==========
//
//    List<Animal> findByTipoAndRaca(TipoAnimalEnum tipo, String raca);
//
//    List<Animal> findByTipoAndRacaAndAtivoTrue(TipoAnimalEnum tipo, String raca);
//
//    // ========== QUERIES PERSONALIZADAS COM JPQL ==========
//
//    @Query("SELECT a FROM Animal a WHERE a.tipo = :tipo AND a.ativo = true")
//    List<Animal> findAtivosByTipo(@Param("tipo") TipoAnimalEnum tipo);
//
//    @Query("SELECT a FROM Animal a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
//    List<Animal> buscarPorNomeLike(@Param("nome") String nome);
//
//    @Query("SELECT a FROM Animal a WHERE a.observacaoGeral IS NULL OR a.observacaoGeral = ''")
//    List<Animal> findWithoutObservacao();
//
//    @Query("SELECT a FROM Animal a WHERE a.observacaoGeral IS NOT NULL AND a.observacaoGeral != ''")
//    List<Animal> findWithObservacao();
//
//    // ========== QUERIES COM COUNT E ESTATÍSTICAS ==========
//
//    long countByAtivoTrue();
//
//    long countByAtivoFalse();
//
//    long countByTipo(TipoAnimalEnum tipo);
//
//    long countByTipoAndAtivoTrue(TipoAnimalEnum tipo);
//
//    long countByTipoAndRaca(TipoAnimalEnum tipo, String raca);
//
//    // ========== OPERAÇÕES DE ATUALIZAÇÃO EM MASSA ==========
//
//    @Modifying
//    @Transactional
//    @CacheEvict(value = "animais", allEntries = true) // Limpa toda a região de memória cacheada ao alterar dados em massa
//    @Query("UPDATE Animal a SET a.ativo = false WHERE a.tipo = :tipo")
//    int desativarPorTipo(@Param("tipo") TipoAnimalEnum tipo);
//
//    @Modifying
//    @Transactional
//    @CacheEvict(value = "animais", allEntries = true)
//    @Query("UPDATE Animal a SET a.ativo = true WHERE a.tipo = :tipo AND a.ativo = false")
//    int ativarPorTipo(@Param("tipo") TipoAnimalEnum tipo);
//
//    // ========== CONSULTAS COM PAGINAÇÃO E ORDENAÇÃO ==========
//
//    List<Animal> findAllByOrderByNomeAsc();
//
//    List<Animal> findByAtivoTrueOrderByNomeAsc();
//
//    List<Animal> findByTipoOrderByNomeAsc(TipoAnimalEnum tipo);
//
//    // ========== BUSCAS POR MÚLTIPLOS IDS ==========
//
//    List<Animal> findByIdIn(List<String> ids);
//
//    List<Animal> findByIdInAndAtivoTrue(List<String> ids);
//}

package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {

    // ========== BUSCAS POR TIPO ==========

    // Listagem direta por tipo (Solicitado)
    List<Animal> findByTipo(TipoAnimalEnum tipo);

    // CRÍTICO: Listagem por tipo ordenada para não vir bagunçada na tela/mobile
    List<Animal> findByTipoOrderByNomeAsc(TipoAnimalEnum tipo);


    // ========== BUSCAS POR NOME ==========

    // Busca exata por nome (Solicitado)
    Optional<Animal> findByNome(String nome);

    // Busca parcial por nome ignorando maiúsculas/minúsculas (Solicitado)
    List<Animal> findByNomeContainingIgnoreCase(String nome);

    // CRÍTICO: Evita cadastrar dois animais com o mesmo nome duplicado por erro de digitação
    boolean existsByNomeIgnoreCase(String nome);


    // ========== INFRAESTRUTURA ESSENCIAL ==========

    // CRÍTICO: Retornar todos os animais em ordem alfabética por padrão
    List<Animal> findAllByOrderByNomeAsc();
}