package br.com.fiap.javaadv.blog.backend.datasource.repositories;



import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {

    // ========== BUSCAS POR STATUS ==========

    /**
     * Busca todos os animais ativos
     */
    List<Animal> findByAtivoTrue();

    /**
     * Busca todos os animais inativos
     */
    List<Animal> findByAtivoFalse();

    /**
     * Busca animais ativos com paginação
     */
    Page<Animal> findByAtivoTrue(Pageable pageable);

    /**
     * Busca animais inativos com paginação
     */
    Page<Animal> findByAtivoFalse(Pageable pageable);

    // ========== BUSCAS POR TIPO ==========

    /**
     * Busca animais por tipo (CACHORRO ou GATO)
     */
    List<Animal> findByTipo(TipoAnimalEnum tipo);

    /**
     * Busca animais ativos por tipo
     */
    List<Animal> findByTipoAndAtivoTrue(TipoAnimalEnum tipo);

    /**
     * Busca animais por tipo com paginação
     */
    Page<Animal> findByTipo(TipoAnimalEnum tipo, Pageable pageable);

    // ========== BUSCAS POR NOME ==========

    /**
     * Busca animais por nome (exato)
     */
    Optional<Animal> findByNome(String nome);

    /**
     * Busca animais por nome (contém - case insensitive)
     */
    List<Animal> findByNomeContainingIgnoreCase(String nome);

    /**
     * Busca animais ativos por nome (contém)
     */
    List<Animal> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);

    /**
     * Verifica se já existe um animal com o mesmo nome (case insensitive)
     */
    boolean existsByNomeIgnoreCase(String nome);

    // ========== BUSCAS POR DATA ==========

    /**
     * Busca animais nascidos após uma data
     */
    List<Animal> findByDataNascimentoAfter(LocalDate data);

    /**
     * Busca animais nascidos antes de uma data
     */
    List<Animal> findByDataNascimentoBefore(LocalDate data);

    /**
     * Busca animais nascidos entre duas datas
     */
    List<Animal> findByDataNascimentoBetween(LocalDate inicio, LocalDate fim);

    // ========== BUSCAS POR RAÇA ==========

    /**
     * Busca animais por raça
     */
    List<Animal> findByRaca(String raca);

    /**
     * Busca animais por raça (case insensitive)
     */
    List<Animal> findByRacaIgnoreCase(String raca);

    // ========== BUSCAS COMBINADAS ==========

    /**
     * Busca animais por tipo e raça
     */
    List<Animal> findByTipoAndRaca(TipoAnimalEnum tipo, String raca);

    /**
     * Busca animais ativos por tipo e raça
     */
    List<Animal> findByTipoAndRacaAndAtivoTrue(TipoAnimalEnum tipo, String raca);

    // ========== QUERIES PERSONALIZADAS COM JPQL ==========

    /**
     * Busca animais ativos por tipo (usando JPQL)
     */
    @Query("SELECT a FROM Animal a WHERE a.tipo = :tipo AND a.ativo = true")
    List<Animal> findAtivosByTipo(@Param("tipo") TipoAnimalEnum tipo);

    /**
     * Busca animais por parte do nome (JPQL)
     */
    @Query("SELECT a FROM Animal a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Animal> buscarPorNomeLike(@Param("nome") String nome);

    /**
     * Busca animais que não têm observação geral
     */
    @Query("SELECT a FROM Animal a WHERE a.observacaoGeral IS NULL OR a.observacaoGeral = ''")
    List<Animal> findWithoutObservacao();

    /**
     * Busca animais que têm observação geral
     */
    @Query("SELECT a FROM Animal a WHERE a.observacaoGeral IS NOT NULL AND a.observacaoGeral != ''")
    List<Animal> findWithObservacao();

    // ========== QUERIES COM COUNT E ESTATÍSTICAS ==========

    /**
     * Conta quantos animais ativos existem
     */
    long countByAtivoTrue();

    /**
     * Conta quantos animais inativos existem
     */
    long countByAtivoFalse();

    /**
     * Conta animais por tipo
     */
    long countByTipo(TipoAnimalEnum tipo);

    /**
     * Conta animais ativos por tipo
     */
    long countByTipoAndAtivoTrue(TipoAnimalEnum tipo);

    /**
     * Conta animais por tipo e raça
     */
    long countByTipoAndRaca(TipoAnimalEnum tipo, String raca);

    // ========== OPERAÇÕES DE ATUALIZAÇÃO EM MASSA ==========

    /**
     * Desativa todos os animais de um determinado tipo
     */
    @Modifying
    @Transactional
    @Query("UPDATE Animal a SET a.ativo = false WHERE a.tipo = :tipo")
    int desativarPorTipo(@Param("tipo") TipoAnimalEnum tipo);

    /**
     * Ativa todos os animais de um determinado tipo
     */
    @Modifying
    @Transactional
    @Query("UPDATE Animal a SET a.ativo = true WHERE a.tipo = :tipo AND a.ativo = false")
    int ativarPorTipo(@Param("tipo") TipoAnimalEnum tipo);

    // ========== CONSULTAS COM PAGINAÇÃO E ORDENAÇÃO ==========

    /**
     * Busca animais ordenados por nome (crescente)
     */
    List<Animal> findAllByOrderByNomeAsc();

    /**
     * Busca animais ativos ordenados por nome
     */
    List<Animal> findByAtivoTrueOrderByNomeAsc();

    /**
     * Busca animais por tipo ordenados por nome
     */
    List<Animal> findByTipoOrderByNomeAsc(TipoAnimalEnum tipo);

    // ========== BUSCAS POR MÚLTIPLOS IDS ==========

    /**
     * Busca animais por uma lista de IDs
     */
    List<Animal> findByIdIn(List<String> ids);

    /**
     * Busca animais ativos por uma lista de IDs
     */
    List<Animal> findByIdInAndAtivoTrue(List<String> ids);
}