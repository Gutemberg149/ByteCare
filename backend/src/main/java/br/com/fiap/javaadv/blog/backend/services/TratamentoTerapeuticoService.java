//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.TratamentoTerapeuticoRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
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
//public class TratamentoTerapeuticoService {
//
//    private final TratamentoTerapeuticoRepository tratamentoTerapeuticoRepository;
//
//    @Transactional
//    public TratamentoTerapeutico create(TratamentoTerapeutico tratamentoTerapeutico) {
//        return tratamentoTerapeuticoRepository.save(tratamentoTerapeutico);
//    }
//
//    @Transactional
//    public Optional<TratamentoTerapeutico> update(String id, TratamentoTerapeutico tratamentoAtualizado) {
//        return tratamentoTerapeuticoRepository.findById(id)
//                .map(tratamentoExistente -> {
//                    tratamentoAtualizado.setId(id);
//                    return tratamentoTerapeuticoRepository.save(tratamentoAtualizado);
//                });
//    }
//
//    @Transactional
//    public void delete(String id) {
//        tratamentoTerapeuticoRepository.deleteById(id);
//    }
//
//    public boolean existsById(String id) {
//        return tratamentoTerapeuticoRepository.existsById(id);
//    }
//
//    public Optional<TratamentoTerapeutico> findById(String id) {
//        return tratamentoTerapeuticoRepository.findById(id);
//    }
//
//    public Page<TratamentoTerapeutico> findAll(Pageable pageable) {
//        return tratamentoTerapeuticoRepository.findAll(pageable);
//    }
//
//    public List<TratamentoTerapeutico> findAll() {
//        return tratamentoTerapeuticoRepository.findAll();
//    }
//
//    public Page<TratamentoTerapeutico> findByAnimalId(String animalId, Pageable pageable) {
//        return tratamentoTerapeuticoRepository.findByAnimalId(animalId, pageable);
//    }
//
//    public Page<TratamentoTerapeutico> buscarPorMedicamento(String animalId, String medicamento, Pageable pageable) {
//        return tratamentoTerapeuticoRepository.buscarPorMedicamento(animalId, medicamento, pageable);
//    }
//}

package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.TratamentoTerapeuticoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TratamentoTerapeuticoService {

    private final TratamentoTerapeuticoRepository repository;
    private final AnimalRepository animalRepository;

    @Transactional
    public TratamentoTerapeutico create(TratamentoTerapeutico entity) {
        return repository.save(entity);
    }

    @Transactional
    public Optional<TratamentoTerapeutico> update(UUID id, TratamentoTerapeutico updatedEntity) {
        return repository.findById(id).map(existing -> {
            updatedEntity.setId(id);
            return repository.save(updatedEntity);
        });
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    public Optional<TratamentoTerapeutico> findById(UUID id) {
        return repository.findById(id);
    }

    public Page<TratamentoTerapeutico> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<TratamentoTerapeutico> findByAnimalId(UUID animalId, Pageable pageable) {
        return repository.findByAnimalId(animalId, pageable);
    }

    public Page<TratamentoTerapeutico> buscarPorMedicamento(UUID animalId, String medicamento, Pageable pageable) {
        return repository.buscarPorMedicamento(animalId, medicamento, pageable);
    }

    /**
     * Auxiliar para buscar o nome do animal e evitar retornos null no DTO
     */
    public String findNomeAnimalById(UUID animalId) {
        return animalRepository.findById(animalId)
                .map(animal -> animal.getNome())
                .orElse("Animal não encontrado");
    }
}