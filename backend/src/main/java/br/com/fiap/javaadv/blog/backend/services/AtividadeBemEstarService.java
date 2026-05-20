//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class AtividadeBemEstarService {
//
//    private final AtividadeBemEstarRepository atividadeBemEstarRepository;
//
//    @Transactional
//    public AtividadeBemEstar create(AtividadeBemEstar atividadeBemEstar) {
//        return atividadeBemEstarRepository.save(atividadeBemEstar);
//    }
//
//    @Transactional
//    public Optional<AtividadeBemEstar> update(String id, AtividadeBemEstar atividadeAtualizada) {
//        return atividadeBemEstarRepository.findById(id)
//                .map(atividadeExistente -> {
//                    atividadeAtualizada.setDataHoraRegistro(atividadeExistente.getDataHoraRegistro());
//                    atividadeAtualizada.setId(id);
//                    return atividadeBemEstarRepository.save(atividadeAtualizada);
//                });
//    }
//    @Transactional
//    public void delete(String id) {
//        atividadeBemEstarRepository.deleteById(id);
//    }
//
//    public boolean existsById(String id) {
//        return atividadeBemEstarRepository.existsById(id);
//    }
//
//    public Optional<AtividadeBemEstar> findById(String id) {
//        return atividadeBemEstarRepository.findById(id);
//    }
//
//    public Page<AtividadeBemEstar> findAll(Pageable pageable) {
//        return atividadeBemEstarRepository.findAll(pageable);
//    }
//
//    public List<AtividadeBemEstar> findAll() {
//        return atividadeBemEstarRepository.findAll();
//    }
//
//    public Page<AtividadeBemEstar> findByAnimalId(String animalId, Pageable pageable) {
//        return atividadeBemEstarRepository.findByAnimalId(animalId, pageable);
//    }
//
//    public Page<AtividadeBemEstar> buscarPorAtividade(String animalId, String atividade, Pageable pageable) {
//        return atividadeBemEstarRepository.buscarPorAtividade(animalId, atividade, pageable);
//    }
//
//
//}

//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class AtividadeBemEstarService {
//
//    private final AtividadeBemEstarRepository atividadeBemEstarRepository;
//
//    @Transactional
//    public AtividadeBemEstar create(AtividadeBemEstar atividadeBemEstar) {
//        return atividadeBemEstarRepository.save(atividadeBemEstar);
//    }
//
//    @Transactional
//    public Optional<AtividadeBemEstar> update(UUID id, AtividadeBemEstar atividadeAtualizada) {
//        return atividadeBemEstarRepository.findById(id)
//                .map(atividadeExistente -> {
//                    atividadeAtualizada.setDataHoraRegistro(atividadeExistente.getDataHoraRegistro());
//                    atividadeAtualizada.setId(id);
//                    return atividadeBemEstarRepository.save(atividadeAtualizada);
//                });
//    }
//
//    @Transactional
//    public void delete(UUID id) {
//        atividadeBemEstarRepository.deleteById(id);
//    }
//
//    @Transactional(readOnly = true)
//    public boolean existsById(UUID id) {
//        return atividadeBemEstarRepository.existsById(id);
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<AtividadeBemEstar> findById(UUID id) {
//        return atividadeBemEstarRepository.findById(id);
//    }
//
//    @Transactional(readOnly = true)
//    public List<AtividadeBemEstar> findAll() {
//        return atividadeBemEstarRepository.findAll();
//    }
//
//    // CORRIGIDO: Voltamos para findByAnimalId que é o método real do seu Repository
//    @Transactional(readOnly = true)
//    public List<AtividadeBemEstar> findAllByAnimalId(UUID animalId) {
//        return atividadeBemEstarRepository.findByAnimalId(animalId);
//    }
//
//    @Transactional(readOnly = true)
//    public List<AtividadeBemEstar> buscarPorAtividade(UUID animalId, String atividade) {
//        return atividadeBemEstarRepository.buscarPorAtividade(animalId, atividade);
//    }
//}

package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtividadeBemEstarService {

    private final AtividadeBemEstarRepository repository;

    @Transactional
    public AtividadeBemEstar create(AtividadeBemEstar entidade) {
        entidade.setDataHoraRegistro(LocalDateTime.now());
        return repository.save(entidade);
    }

    @Transactional
    public Optional<AtividadeBemEstarResponse> update(UUID id, AtividadeBemEstarRequest request, Animal animal) {
        return repository.findById(id).map(existente -> {
            AtividadeBemEstar atualizada = AtividadeBemEstarRequest.toEntity(request, animal);
            atualizada.setId(id);
            atualizada.setDataHoraRegistro(existente.getDataHoraRegistro());
            return AtividadeBemEstarResponse.toDto(repository.save(atualizada));
        });
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Transactional(readOnly = true)
    public Optional<AtividadeBemEstarResponse> findById(UUID id) {
        return repository.findById(id).map(AtividadeBemEstarResponse::toDto);
    }

    @Transactional(readOnly = true)
    public List<AtividadeBemEstarResponse> findAll() {
        return repository.findAll().stream()
                .map(AtividadeBemEstarResponse::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AtividadeBemEstarResponse> findAllByAnimalId(UUID animalId) {
        return repository.findByAnimalId(animalId).stream()
                .map(AtividadeBemEstarResponse::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AtividadeBemEstarResponse> buscarPorAtividade(UUID animalId, String atividade) {
        return repository.buscarPorAtividade(animalId, atividade).stream()
                .map(AtividadeBemEstarResponse::toDto)
                .toList();
    }
}