//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.RegistroCuidadoRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class RegistroCuidadoService {
//
//    private final RegistroCuidadoRepository registroCuidadoRepository;
//
//    @Transactional
//    public RegistroCuidado save(RegistroCuidado registroCuidado) {
//        return registroCuidadoRepository.save(registroCuidado);
//    }
//
//    @Transactional
//    public void delete(String id) {
//        registroCuidadoRepository.deleteById(id);
//    }
//
//    public boolean existsById(String id) {
//        return registroCuidadoRepository.existsById(id);
//    }
//
//    public Optional<RegistroCuidado> findById(String id) {
//        return registroCuidadoRepository.findById(id);
//    }
//
//    public Page<RegistroCuidado> findAll(Pageable pageable) {
//        return registroCuidadoRepository.findAll(pageable);
//    }
//
//    public Page<RegistroCuidado> findByAnimalId(String animalId, Pageable pageable) {
//        return registroCuidadoRepository.findByAnimalId(animalId, pageable);
//    }
//
//    public Page<RegistroCuidado> findByAnimalIdAndCategoria(String animalId,
//                                                            CategoriaCuidadoEnum categoria,
//                                                            Pageable pageable) {
//        return registroCuidadoRepository.findByAnimalIdAndCategoria(animalId, categoria, pageable);
//    }
//
//    public Page<RegistroCuidado> buscarDiarioPorAnimal(String animalId, Pageable pageable) {
//        return registroCuidadoRepository.buscarDiarioPorAnimal(animalId, pageable);
//    }
//
//    public long countByAnimalIdAndCategoria(String animalId, CategoriaCuidadoEnum categoria) {
//        return registroCuidadoRepository.countByAnimalIdAndCategoria(animalId, categoria);
//    }
//}
//
//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.RegistroCuidadoRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.RegistroCuidadoResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class RegistroCuidadoService {
//
//    private final RegistroCuidadoRepository registroCuidadoRepository;
//    private final AnimalRepository animalRepository;
//
//    /**
//     * Converte a entidade RegistroCuidado para o DTO de resposta.
//     * Busca o nome do animal diretamente pelo UUID original.
//     */
//    public RegistroCuidadoResponse mapToResponse(RegistroCuidado entity) {
//        RegistroCuidadoResponse response = RegistroCuidadoResponse.toDto(entity);
//
//        // Busca o animal pelo ID correto (UUID) sem conversão para String
//        if (entity.getAnimalId() != null) {
//            animalRepository.findById(UUID.fromString(String.valueOf(entity.getAnimalId())))
//                    .ifPresent(animal -> response.setNomeAnimal(animal.getNome()));
//        }
//
//        return response;
//    }
//
//    @Transactional
//    public RegistroCuidado save(RegistroCuidado registroCuidado) {
//        return registroCuidadoRepository.save(registroCuidado);
//    }
//
//    @Transactional
//    public void delete(UUID id) {
//        registroCuidadoRepository.deleteById(id);
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<RegistroCuidado> findById(UUID id) {
//        return registroCuidadoRepository.findById(id);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<RegistroCuidado> findAll(Pageable pageable) {
//        return registroCuidadoRepository.findAll(pageable);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<RegistroCuidado> findByAnimalId(UUID animalId, Pageable pageable) {
//        return registroCuidadoRepository.findByAnimalId(animalId, pageable);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<RegistroCuidado> findByAnimalIdAndCategoria(UUID animalId,
//                                                            CategoriaCuidadoEnum categoria,
//                                                            Pageable pageable) {
//        return registroCuidadoRepository.findByAnimalIdAndCategoria(animalId, categoria, pageable);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<RegistroCuidado> buscarDiarioPorAnimal(UUID animalId, Pageable pageable) {
//        return registroCuidadoRepository.buscarDiarioPorAnimal(animalId, pageable);
//    }
//
//
//
//    public RegistroCuidadoResponse toResponse(RegistroCuidado entity) {
//        // 1. Converte a entidade básica para DTO usando o método que já existe no seu Response
//        RegistroCuidadoResponse response = RegistroCuidadoResponse.toDto(entity);
//
//        if (entity.getAnimalId() != null) {
//            animalRepository.findById(entity.getAnimalId())
//                    .ifPresent(animal -> response.setNomeAnimal(animal.getNome()));
//        }
//
//        return response;
//    }
//
//
//}

package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.RegistroCuidadoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import br.com.fiap.javaadv.blog.backend.resources.dtos.RegistroCuidadoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.RegistroCuidadoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistroCuidadoService {

    private final RegistroCuidadoRepository repository;
    private final AnimalRepository animalRepository;

    // --- MÉTODOS DE ESCRITA (CRUD) ---

    @Transactional
    public RegistroCuidado create(RegistroCuidadoRequest request) {
        var animal = animalRepository.findById(UUID.fromString(request.getIdAnimal()))
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        RegistroCuidado entity = request.toEntity(animal);
        return repository.save(entity);
    }

    @Transactional
    public RegistroCuidado update(UUID id, RegistroCuidadoRequest request) {
        return repository.findById(id).map(existente -> {
            var animal = animalRepository.findById(UUID.fromString(request.getIdAnimal()))
                    .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

            // Atualiza campos (ajuste conforme os atributos do seu RegistroCuidado)
            existente.setCategoria(request.getCategoria());
            existente.setDescricao(request.getDescricao());
            existente.setAnimal(animal);

            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Registro de cuidado não encontrado"));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) throw new RuntimeException("Registro não encontrado");
        repository.deleteById(id);
    }

    // --- MÉTODOS DE LEITURA ---

    @Transactional(readOnly = true)
    public Optional<RegistroCuidado> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<RegistroCuidado> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<RegistroCuidado> findByAnimalId(UUID animalId, Pageable pageable) {
        return repository.findByAnimalId(animalId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<RegistroCuidado> findByAnimalIdAndCategoria(UUID animalId, CategoriaCuidadoEnum cat, Pageable pageable) {
        return repository.findByAnimalIdAndCategoria(animalId, cat, pageable);
    }

    @Transactional(readOnly = true)
    public Page<RegistroCuidado> buscarDiarioPorAnimal(UUID animalId, Pageable pageable) {
        return repository.buscarDiarioPorAnimal(animalId, pageable);
    }

    // --- MÉTODOS DE CONVERSÃO ---

    public RegistroCuidadoResponse toResponse(RegistroCuidado entity) {
        RegistroCuidadoResponse response = RegistroCuidadoResponse.toDto(entity);
        // Opcional: Garante que o nome do animal esteja sempre atualizado no DTO
        if (entity.getAnimal() != null) {
            response.setNomeAnimal(entity.getAnimal().getNome());
        }
        return response;
    }
}