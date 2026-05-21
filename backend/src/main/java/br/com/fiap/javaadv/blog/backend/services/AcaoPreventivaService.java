//////package br.com.fiap.javaadv.blog.backend.services;
//////
//////import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
//////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.data.domain.Page;
//////import org.springframework.data.domain.Pageable;
//////import org.springframework.stereotype.Service;
//////import org.springframework.transaction.annotation.Transactional;
//////
//////import java.util.List;
//////import java.util.Optional;
//////
//////@Service
//////@RequiredArgsConstructor
//////public class AcaoPreventivaService {
//////
//////    private final AcaoPreventivaRepository acaoPreventivaRepository;
//////
//////    @Transactional
//////    public AcaoPreventiva create(AcaoPreventiva acaoPreventiva) {
//////        return acaoPreventivaRepository.save(acaoPreventiva);
//////    }
//////
//////    @Transactional
//////    public Optional<AcaoPreventiva> update(String id, AcaoPreventiva acaoAtualizada) {
//////        return acaoPreventivaRepository.findById(id)
//////                .map(acaoExistente -> {
//////                    acaoAtualizada.setId(id);
//////                    return acaoPreventivaRepository.save(acaoAtualizada);
//////                });
//////    }
//////
//////    @Transactional
//////    public void delete(String id) {
//////        acaoPreventivaRepository.deleteById(id);
//////    }
//////
//////    public boolean existsById(String id) {
//////        return acaoPreventivaRepository.existsById(id);
//////    }
//////
//////    public Optional<AcaoPreventiva> findById(String id) {
//////        return acaoPreventivaRepository.findById(id);
//////    }
//////
//////    public Page<AcaoPreventiva> findAll(Pageable pageable) {
//////        return acaoPreventivaRepository.findAll(pageable);
//////    }
//////
//////    public List<AcaoPreventiva> findAll() {
//////        return acaoPreventivaRepository.findAll();
//////    }
//////
//////    public Page<AcaoPreventiva> findByAnimalId(String animalId, Pageable pageable) {
//////        return acaoPreventivaRepository.findByAnimalId(animalId, pageable);
//////    }
//////
//////    public List<AcaoPreventiva> findByAnimalId(String animalId) {
//////        return acaoPreventivaRepository.findByAnimalId(animalId);
//////    }
//////
//////    public Page<AcaoPreventiva> buscarPorNomeServico(String animalId, String nome, Pageable pageable) {
//////        return acaoPreventivaRepository.buscarPorNomeServico(animalId, nome, pageable);
//////    }
//////}
////package br.com.fiap.javaadv.blog.backend.services;
////
////import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
////import lombok.RequiredArgsConstructor;
////import org.springframework.cache.annotation.CacheEvict;
////import org.springframework.cache.annotation.Cacheable;
////import org.springframework.data.domain.Page;
////import org.springframework.data.domain.Pageable;
////import org.springframework.stereotype.Service;
////import org.springframework.transaction.annotation.Transactional;
////
////import java.util.List;
////import java.util.Optional;
////
////@Service
////@RequiredArgsConstructor
////public class AcaoPreventivaService {
////
////    private final AcaoPreventivaRepository acaoPreventivaRepository;
////
////    @Transactional
////    @CacheEvict(value = {"acoesPreventivas", "acoesPorAnimal"}, allEntries = true)
////    public AcaoPreventiva create(AcaoPreventiva acaoPreventiva) {
////        return acaoPreventivaRepository.save(acaoPreventiva);
////    }
////
////    @Transactional
////    @CacheEvict(value = {"acoesPreventivas", "acoesPorAnimal"}, allEntries = true)
////    public Optional<AcaoPreventiva> update(String id, AcaoPreventiva acaoAtualizada) {
////        return acaoPreventivaRepository.findById(id)
////                .map(acaoExistente -> {
////                    acaoAtualizada.setId(id);
////                    return acaoPreventivaRepository.save(acaoAtualizada);
////                });
////    }
////
////    @Transactional
////    @CacheEvict(value = {"acoesPreventivas", "acoesPorAnimal"}, allEntries = true)
////    public void delete(String id) {
////        acaoPreventivaRepository.deleteById(id);
////    }
////
////    public boolean existsById(String id) {
////        return acaoPreventivaRepository.existsById(id);
////    }
////
////    @Cacheable(value = "acoesPreventivas", key = "#id")
////    public Optional<AcaoPreventiva> findById(String id) {
////        return acaoPreventivaRepository.findById(id);
////    }
////
////    @Cacheable(value = "acoesPreventivas", key = "'all_' + #pageable.pageNumber")
////    public Page<AcaoPreventiva> findAll(Pageable pageable) {
////        return acaoPreventivaRepository.findAll(pageable);
////    }
////
////    @Cacheable(value = "acoesPorAnimal", key = "#animalId + '_' + #pageable.pageNumber")
////    public Page<AcaoPreventiva> findByAnimalId(String animalId, Pageable pageable) {
////        return acaoPreventivaRepository.findByAnimalId(animalId, pageable);
////    }
////
////    public List<AcaoPreventiva> findByAnimalId(String animalId) {
////        return acaoPreventivaRepository.findByAnimalId(animalId);
////    }
////
////    public Page<AcaoPreventiva> buscarPorNomeServico(String animalId, String nome, Pageable pageable) {
////        return acaoPreventivaRepository.buscarPorNomeServico(animalId, nome, pageable);
////    }
////}
//
////package br.com.fiap.javaadv.blog.backend.services;
////
////import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
////import lombok.RequiredArgsConstructor;
////import org.springframework.cache.annotation.CacheEvict;
////import org.springframework.cache.annotation.Cacheable;
////import org.springframework.stereotype.Service;
////import org.springframework.transaction.annotation.Transactional;
////
////import java.util.List;
////import java.util.Optional;
////import java.util.UUID;
////
////@Service
////@RequiredArgsConstructor
////public class AcaoPreventivaService {
////
////    private final AcaoPreventivaRepository acaoPreventivaRepository;
////
////    @Transactional
////    @CacheEvict(value = {"acoesPreventivas", "acoesPorAnimal"}, allEntries = true)
////    public AcaoPreventiva create(AcaoPreventiva acaoPreventiva) {
////        return acaoPreventivaRepository.save(acaoPreventiva);
////    }
////
////    @Transactional
////    @CacheEvict(value = {"acoesPreventivas", "acoesPorAnimal"}, allEntries = true)
////    public Optional<AcaoPreventiva> update(UUID id, AcaoPreventiva acaoAtualizada) {
////        // Limpeza: findById agora recebe o UUID diretamente, sem conversões de String
////        return acaoPreventivaRepository.findById(id)
////                .map(acaoExistente -> {
////                    acaoAtualizada.setId(id);
////                    return acaoPreventivaRepository.save(acaoAtualizada);
////                });
////    }
////
////    @Transactional
////    @CacheEvict(value = {"acoesPreventivas", "acoesPorAnimal"}, allEntries = true)
////    public void delete(UUID id) {
////        acaoPreventivaRepository.deleteById(id);
////    }
////
////    @Transactional(readOnly = true)
////    public boolean existsById(UUID id) {
////        return acaoPreventivaRepository.existsById(id);
////    }
////
////    @Transactional(readOnly = true)
////    @Cacheable(value = "acoesPreventivas", key = "#id")
////    public Optional<AcaoPreventiva> findById(UUID id) {
////        return acaoPreventivaRepository.findById(id);
////    }
////
////    @Transactional(readOnly = true)
////    @Cacheable(value = "acoesPreventivas", key = "'all'")
////    public List<AcaoPreventiva> findAll() {
////        // Refatorado: Retorna a lista completa para o app mobile, sem paginação
////        return acaoPreventivaRepository.findAll();
////    }
////
////    @Transactional(readOnly = true)
////    @Cacheable(value = "acoesPorAnimal", key = "#animalId + '_list'")
////    public List<AcaoPreventiva> findAllByAnimalId(UUID animalId) {
////        // Corrigido: Passando o UUID limpo e chamando o método correto do repositório
////        return acaoPreventivaRepository.findByAnimalId(animalId);
////    }
////
////    @Transactional(readOnly = true)
////    @Cacheable(value = "acoesPorAnimal", key = "#animalId + '_busca_' + #nome")
////    public List<AcaoPreventiva> buscarPorNomeServico(UUID animalId, String nome) {
////        // Corrigido: Passando os 2 argumentos esperados (UUID e String) e retornando List
////        return acaoPreventivaRepository.buscarPorNomeServico(animalId, nome);
////    }
////}
//
//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaRequest;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@CacheConfig(cacheNames = {"acoesPreventivas", "acoesPorAnimal"})
//public class AcaoPreventivaService {
//
//    private final AcaoPreventivaRepository repository;
//    private final AnimalService animalService;
//
//    @Transactional
//    @CacheEvict(allEntries = true)
//    public AcaoPreventivaResponse create(AcaoPreventivaRequest request) {
//        Animal animal = animalService.fetchById(request.getIdAnimal())
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
//
//        AcaoPreventiva acao = AcaoPreventivaRequest.toEntity(request, animal);
//        return AcaoPreventivaResponse.toDto(repository.save(acao));
//    }
//
//    @Transactional
//    @CacheEvict(allEntries = true)
//    public Optional<AcaoPreventivaResponse> update(UUID id, AcaoPreventivaRequest request) {
//        return repository.findById(id).map(existente -> {
//            Animal animal = animalService.fetchById(request.getIdAnimal())
//                    .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
//
//            AcaoPreventiva atualizada = AcaoPreventivaRequest.toEntity(request, animal);
//            atualizada.setId(id);
//            atualizada.setDataHoraRegistro(existente.getDataHoraRegistro());
//
//            return AcaoPreventivaResponse.toDto(repository.save(atualizada));
//        });
//    }
//
//    @Transactional
//    @CacheEvict(allEntries = true)
//    public void delete(UUID id) {
//        repository.deleteById(id);
//    }
//
//    @Transactional(readOnly = true)
//    public boolean existsById(UUID id) {
//        return repository.existsById(id);
//    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(key = "#id")
//    public Optional<AcaoPreventivaResponse> findById(UUID id) {
//        return repository.findById(id).map(AcaoPreventivaResponse::toDto);
//    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(key = "'all'")
//    public List<AcaoPreventivaResponse> findAll() {
//        return repository.findAll().stream()
//                .map(AcaoPreventivaResponse::toDto)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(key = "#animalId + '_list'")
//    public List<AcaoPreventivaResponse> findAllByAnimalId(UUID animalId) {
//        return repository.findByAnimalId(animalId).stream()
//                .map(AcaoPreventivaResponse::toDto)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(key = "#animalId + '_busca_' + #nome")
//    public List<AcaoPreventivaResponse> buscarPorNomeServico(UUID animalId, String nome) {
//        return repository.buscarPorNomeServico(animalId, nome).stream()
//                .map(AcaoPreventivaResponse::toDto)
//                .collect(Collectors.toList());
//    }
//}

package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"acoesPreventivas", "acoesPorAnimal"})
public class AcaoPreventivaService {

    private final AcaoPreventivaRepository repository;
    private final AnimalService animalService;

    @Transactional
    @CacheEvict(allEntries = true)
    public AcaoPreventivaResponse create(AcaoPreventivaRequest request) {
        // Agora recebe o Animal direto ou lança exceção (o Spring/Resource tratará)
        var animal = animalService.fetchById(request.getIdAnimal());
        var acao = AcaoPreventivaRequest.toEntity(request, animal);
        return AcaoPreventivaResponse.toDto(repository.save(acao));
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public Optional<AcaoPreventivaResponse> update(UUID id, AcaoPreventivaRequest request) {
        return repository.findById(id).map(existente -> {
            var animal = animalService.fetchById(request.getIdAnimal());
            var atualizada = AcaoPreventivaRequest.toEntity(request, animal);

            atualizada.setId(id);
            atualizada.setDataHoraRegistro(existente.getDataHoraRegistro());

            return AcaoPreventivaResponse.toDto(repository.save(atualizada));
        });
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id")
    public Optional<AcaoPreventivaResponse> findById(UUID id) {
        return repository.findById(id).map(AcaoPreventivaResponse::toDto);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "'all'")
    public List<AcaoPreventivaResponse> findAll() {
        return repository.findAll().stream()
                .map(AcaoPreventivaResponse::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#animalId + '_list'")
    public List<AcaoPreventivaResponse> findAllByAnimalId(UUID animalId) {
        return repository.findByAnimalId(animalId).stream()
                .map(AcaoPreventivaResponse::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#animalId + '_busca_' + #nome")
    public List<AcaoPreventivaResponse> buscarPorNomeServico(UUID animalId, String nome) {
        return repository.buscarPorNomeServico(animalId, nome).stream()
                .map(AcaoPreventivaResponse::toDto)
                .toList();
    }
}