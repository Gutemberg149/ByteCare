//////package br.com.fiap.javaadv.blog.backend.services;
//////
//////import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
//////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//////import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
//////import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
//////import jakarta.persistence.EntityNotFoundException;
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.stereotype.Service;
//////import org.springframework.transaction.annotation.Transactional;
//////
//////import java.time.LocalDateTime;
//////import java.util.List;
//////import java.util.UUID;
//////
//////@Service
//////@RequiredArgsConstructor
//////public class AtividadeBemEstarService {
//////
//////    private final AtividadeBemEstarRepository repository;
//////
//////    @Transactional
//////    public AtividadeBemEstar create(AtividadeBemEstar entidade) {
//////        // Garantir integridade mínima
//////        if (entidade.getAnimal() == null) {
//////            throw new IllegalArgumentException("A atividade deve estar vinculada a um animal.");
//////        }
//////
//////        entidade.setDataHoraRegistro(LocalDateTime.now());
//////        entidade.setAnimalId(entidade.getAnimal().getId());
//////
//////        return repository.save(entidade);
//////    }
//////
//////    @Transactional
//////    public AtividadeBemEstarResponse update(UUID id, AtividadeBemEstarRequest request, Animal animal) {
//////        AtividadeBemEstar existente = findEntityById(id);
//////
//////        existente.setAtividade(request.getNomeAtividade());
//////        existente.setObservacaoAtividade(request.getObservacaoAtividade());
//////        existente.setDuracao(request.getDuracao());
//////        existente.setObservacao(request.getObservacao());
//////        existente.setCategoria(request.getCategoria());
//////        existente.setAnimal(animal);
//////        existente.setAnimalId(animal.getId());
//////
//////        return AtividadeBemEstarResponse.toDto(repository.save(existente));
//////    }
//////
//////    @Transactional(readOnly = true)
//////    public AtividadeBemEstarResponse findById(UUID id) {
//////        return AtividadeBemEstarResponse.toDto(findEntityById(id));
//////    }
//////
//////    @Transactional(readOnly = true)
//////    public List<AtividadeBemEstarResponse> findAll() {
//////        return repository.findAll().stream()
//////                .map(AtividadeBemEstarResponse::toDto)
//////                .toList();
//////    }
//////
//////    @Transactional(readOnly = true)
//////    public List<AtividadeBemEstarResponse> findAllByAnimalId(UUID animalId) {
//////        return repository.findByAnimalId(animalId).stream()
//////                .map(AtividadeBemEstarResponse::toDto)
//////                .toList();
//////    }
//////
//////    @Transactional(readOnly = true)
//////    public List<AtividadeBemEstarResponse> buscarPorAtividade(UUID animalId, String atividade) {
//////        return repository.buscarPorAtividade(animalId, atividade).stream()
//////                .map(AtividadeBemEstarResponse::toDto)
//////                .toList();
//////    }
//////
//////    @Transactional
//////    public void delete(UUID id) {
//////        if (!repository.existsById(id)) {
//////            throw new EntityNotFoundException("Atividade não encontrada com ID: " + id);
//////        }
//////        repository.deleteById(id);
//////    }
//////
//////    // Método auxiliar para centralizar a busca e evitar código duplicado
//////    private AtividadeBemEstar findEntityById(UUID id) {
//////        return repository.findById(id)
//////                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada com ID: " + id));
//////    }
//////}
////
////package br.com.fiap.javaadv.blog.backend.services;
////
////import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
////import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
////import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
////import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
////import lombok.RequiredArgsConstructor;
////import org.springframework.stereotype.Service;
////import org.springframework.transaction.annotation.Transactional;
////
////import java.time.LocalDateTime;
////import java.util.List;
////import java.util.UUID;
////import java.util.stream.Collectors;
////
////@Service
////@RequiredArgsConstructor
////public class AtividadeBemEstarService {
////
////    private final AtividadeBemEstarRepository repository;
////    private final AnimalRepository animalRepository;
////
////    @Transactional
////    public AtividadeBemEstarResponse create(AtividadeBemEstarRequest request) {
////        Animal animal = animalRepository.findById(request.getAnimalId())
////                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
////
////        AtividadeBemEstar entity = AtividadeBemEstar.builder()
////                .atividade(request.getAtividade())
////                .observacao(request.getObservacao())
////                .categoria(request.getCategoria())
////                .dataHoraRegistro(LocalDateTime.now())
////                .animal(animal)
////                .build();
////
////        return AtividadeBemEstarResponse.fromEntity(repository.save(entity));
////    }
////
////    public List<AtividadeBemEstarResponse> findByAnimal(UUID animalId) {
////        return repository.findByAnimalId(animalId)
////                .stream()
////                .map(AtividadeBemEstarResponse::fromEntity)
////                .collect(Collectors.toList());
////    }
////
////    @Transactional
////    public AtividadeBemEstarResponse update(UUID id, AtividadeBemEstarRequest request, Animal animal) {
////        AtividadeBemEstar entity = repository.findById(id)
////                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));
////
////        entity.setAtividade(request.getAtividade());
////        entity.setObservacao(request.getObservacao());
////        entity.setCategoria(request.getCategoria());
////
////        return AtividadeBemEstarResponse.fromEntity(repository.save(entity));
////    }
////
////    @Transactional
////    public void delete(UUID id) {
////        repository.deleteById(id);
////    }
////}
//
//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@CacheConfig(cacheNames = {"atividadesBemEstar", "atividadesPorAnimal"})
//public class AtividadeBemEstarService {
//
//    private final AtividadeBemEstarRepository repository;
//
//    @Transactional
//    @CacheEvict(allEntries = true)
//    public AtividadeBemEstar create(AtividadeBemEstarRequest request, Animal animal) {
//        AtividadeBemEstar entity = AtividadeBemEstar.builder()
//                .atividade(request.getAtividade())
//                .observacao(request.getObservacao())
//                .categoria(request.getCategoria())
//                .dataHoraRegistro(LocalDateTime.now())
//                .animal(animal)
//                .build();
//        return repository.save(entity);
//    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(key = "'all'")
//    public List<AtividadeBemEstarResponse> findAll() {
//        return repository.findAll().stream()
//                .map(AtividadeBemEstarResponse::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(key = "#id")
//    public AtividadeBemEstarResponse findById(UUID id) {
//        return repository.findById(id)
//                .map(AtividadeBemEstarResponse::fromEntity)
//                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + id));
//    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(key = "#animalId + '_list'")
//    public List<AtividadeBemEstarResponse> findAllByAnimalId(UUID animalId) {
//        return repository.findByAnimalId(animalId).stream()
//                .map(AtividadeBemEstarResponse::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    @Cacheable(key = "#animalId + '_busca_' + #atividade")
//    public List<AtividadeBemEstarResponse> buscarPorAtividade(UUID animalId, String atividade) {
//        return repository.buscarPorAtividade(animalId, atividade).stream()
//                .map(AtividadeBemEstarResponse::fromEntity)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    @CacheEvict(allEntries = true)
//    public AtividadeBemEstarResponse update(UUID id, AtividadeBemEstarRequest request, Animal animal) {
//        AtividadeBemEstar entity = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + id));
//
//        entity.setAtividade(request.getAtividade());
//        entity.setObservacao(request.getObservacao());
//        entity.setCategoria(request.getCategoria());
//        entity.setAnimal(animal);
//
//        return AtividadeBemEstarResponse.fromEntity(repository.save(entity));
//    }
//
//    @Transactional
//    @CacheEvict(allEntries = true)
//    public void delete(UUID id) {
//        if (repository.existsById(id)) {
//            repository.deleteById(id);
//        }
//    }
//}

package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"atividadesBemEstar", "atividadesPorAnimal"})
public class AtividadeBemEstarService {

    private final AtividadeBemEstarRepository repository;

    @Transactional
    @CacheEvict(allEntries = true)
    public AtividadeBemEstar create(AtividadeBemEstarRequest request, Animal animal) {
        AtividadeBemEstar entity = AtividadeBemEstar.builder()
                .atividade(request.getAtividade())
                .observacao(request.getObservacao())
                .categoria(request.getCategoria())
                .dataHoraRegistro(LocalDateTime.now())
                .animal(animal)
                .build();
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "'all'")
    public List<AtividadeBemEstarResponse> findAll() {
        return repository.findAll().stream()
                .map(AtividadeBemEstarResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id")
    public AtividadeBemEstarResponse findById(UUID id) {
        return repository.findById(id)
                .map(AtividadeBemEstarResponse::fromEntity)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#animalId + '_list'")
    public List<AtividadeBemEstarResponse> findAllByAnimalId(UUID animalId) {
        return repository.findByAnimalId(animalId).stream()
                .map(AtividadeBemEstarResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#animalId + '_busca_' + #atividade")
    public List<AtividadeBemEstarResponse> buscarPorAtividade(UUID animalId, String atividade) {
        return repository.buscarPorAtividade(animalId, atividade).stream()
                .map(AtividadeBemEstarResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public AtividadeBemEstarResponse update(UUID id, AtividadeBemEstarRequest request, Animal animal) {
        AtividadeBemEstar entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + id));

        entity.setAtividade(request.getAtividade());
        entity.setObservacao(request.getObservacao());
        entity.setCategoria(request.getCategoria());
        entity.setAnimal(animal);

        return AtividadeBemEstarResponse.fromEntity(repository.save(entity));
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}