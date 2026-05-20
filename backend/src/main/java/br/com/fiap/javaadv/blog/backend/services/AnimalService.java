//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
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
//public class AnimalService {
//
//    private final AnimalRepository animalRepository;
//
//    @Transactional
//    @CachePut(value = "animais", key = "#result.id")
//    public Animal create(Animal animal) {
//        return animalRepository.save(animal);
//    }
//
//    @Transactional
//    @CachePut(value = "animais", key = "#id")
//    public Optional<Animal> update(String id, Animal animalAtualizado) {
//        return animalRepository.findById(id)
//                .map(animalExistente -> {
//                    animalAtualizado.setId(id);
//                    return animalRepository.save(animalAtualizado);
//                });
//    }
//
//    @Transactional
//    @CacheEvict(value = "animais", key = "#id")
//    public void delete(String id) {
//        animalRepository.deleteById(id);
//    }
//
//    public boolean existsById(String id) {
//        return animalRepository.existsById(id);
//    }
//
//    @Cacheable(value = "animais", key = "#id")
//    public Optional<Animal> fetchById(String id) {
//        return animalRepository.findById(id);
//    }
//
//    public Page<Animal> fetchAll(Pageable pageable) {
//        return animalRepository.findAll(pageable);
//    }
//
//    public List<Animal> findAll() {
//        return animalRepository.findAll();
//    }
//
//    public List<Animal> findByAtivoTrue() {
//        return animalRepository.findByAtivoTrue();
//    }
//
//    public List<Animal> findByTipo(TipoAnimalEnum tipo) {
//        return animalRepository.findByTipo(tipo);
//    }
//
//    public List<Animal> findByTipo(String tipo) {
//        try {
//            TipoAnimalEnum tipoEnum = TipoAnimalEnum.valueOf(tipo.toUpperCase());
//            return animalRepository.findByTipo(tipoEnum);
//        } catch (IllegalArgumentException e) {
//            return List.of();
//        }
//    }
//
//    public List<Animal> findByNomeContaining(String nome) {
//        return animalRepository.findByNomeContainingIgnoreCase(nome);
//    }
//
//    @Transactional
//    @CachePut(value = "animais", key = "#id")
//    public Animal desativar(String id) {
//        Animal animal = animalRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
//        animal.setAtivo(false);
//        return animalRepository.save(animal);
//    }
//
//    @Transactional
//    @CachePut(value = "animais", key = "#id")
//    public Animal activar(String id) {
//        Animal animal = animalRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
//        animal.setAtivo(true);
//        return animalRepository.save(animal);
//    }
//
//    @Transactional
//    @CachePut(value = "animais", key = "#id")
//    public Animal ativar(String id) {
//        Animal animal = animalRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
//        animal.setAtivo(true);
//        return animalRepository.save(animal);
//    }
//}

//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class AnimalService {
//
//    private final AnimalRepository animalRepository;
//
//    @Transactional
//    @CachePut(value = "animais", key = "#result.id")
//    public Animal create(Animal animal) {
//        return animalRepository.save(animal);
//    }
//
//    @Transactional
//    @CachePut(value = "animais", key = "#id")
//    public Optional<Animal> update(UUID id, Animal animalAtualizado) {
//        return animalRepository.findById(String.valueOf(id))
//                .map(animalExistente -> {
//                    animalAtualizado.setId(id);
//                    return animalRepository.save(animalAtualizado);
//                });
//    }
//
//    @Transactional
//    @CacheEvict(value = "animais", key = "#id")
//    public void delete(UUID id) {
//        animalRepository.deleteById(String.valueOf(id));
//    }
//
//    public boolean existsById(UUID id) {
//        return animalRepository.existsById(String.valueOf(id));
//    }
//
//    @Cacheable(value = "animais", key = "#id")
//    public Optional<Animal> fetchById(UUID id) {
//        return animalRepository.findById(String.valueOf(id));
//    }
//
//    public Page<Animal> fetchAll(Pageable pageable) {
//        return animalRepository.findAll(pageable);
//    }
//
//    public List<Animal> findAll() {
//        return animalRepository.findAll();
//    }
//
//    public List<Animal> findByAtivoTrue() {
//        return animalRepository.findByAtivoTrue();
//    }
//
//    public List<Animal> findByTipo(TipoAnimalEnum tipo) {
//        return animalRepository.findByTipo(tipo);
//    }
//
//    public List<Animal> findByTipo(String tipo) {
//        try {
//            TipoAnimalEnum tipoEnum = TipoAnimalEnum.valueOf(tipo.toUpperCase());
//            return animalRepository.findByTipo(tipoEnum);
//        } catch (IllegalArgumentException e) {
//            return List.of();
//        }
//    }
//
//    public List<Animal> findByNomeContaining(String nome) {
//        return animalRepository.findByNomeContainingIgnoreCase(nome);
//    }
//
//    @Transactional
//    @CachePut(value = "animais", key = "#id")
//    public Animal desativar(UUID id) {
//        Animal animal = animalRepository.findById(String.valueOf(id))
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
//        animal.setAtivo(false);
//        return animalRepository.save(animal);
//    }
//
//    @Transactional
//    @CachePut(value = "animais", key = "#id")
//    public Animal activar(UUID id) {
//        Animal animal = animalRepository.findById(String.valueOf(id))
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
//        animal.setAtivo(true);
//        return animalRepository.save(animal);
//    }
//
//    @Transactional
//    @CachePut(value = "animais", key = "#id")
//    public Animal ativar(UUID id) {
//        Animal animal = animalRepository.findById(String.valueOf(id))
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
//        animal.setAtivo(true);
//        return animalRepository.save(animal);
//    }
//}

package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Transactional
    @CachePut(value = "animais", key = "#result.id")
    public Animal create(Animal animal) {
        // Validação preventiva usando o método enxuto do repositório
        if (animalRepository.existsByNomeIgnoreCase(animal.getNome())) {
            throw new IllegalArgumentException("Já existe um animal cadastrado com o nome: " + animal.getNome());
        }
        return animalRepository.save(animal);
    }

    @Transactional
    @CachePut(value = "animais", key = "#id")
    public Optional<Animal> update(UUID id, Animal animalAtualizado) {
        return animalRepository.findById(UUID.fromString(String.valueOf(id)))
                .map(animalExistente -> {
                    animalAtualizado.setId(id);
                    return animalRepository.save(animalAtualizado);
                });
    }

    @Transactional
    @CacheEvict(value = "animais", key = "#id")
    public void delete(UUID id) {
        animalRepository.deleteById(UUID.fromString(String.valueOf(id)));
    }

    public boolean existsById(UUID id) {
        return animalRepository.existsById(UUID.fromString(String.valueOf(id)));
    }

    @Cacheable(value = "animais", key = "#id")
    public Optional<Animal> fetchById(UUID id) {
        return animalRepository.findById(UUID.fromString(String.valueOf(id)));
    }

    // Retorna todos ordenados por padrão utilizando a estrutura importante mantida
    public List<Animal> findAll() {
        return animalRepository.findAllByOrderByNomeAsc();
    }

    // Busca por Enum diretamente (Solicitado)
    public List<Animal> findByTipo(TipoAnimalEnum tipo) {
        return animalRepository.findByTipoOrderByNomeAsc(tipo);
    }

    // Conversão de String para Enum segura (Solicitado via endpoint/front)
    public List<Animal> findByTipo(String tipo) {
        try {
            TipoAnimalEnum tipoEnum = TipoAnimalEnum.valueOf(tipo.toUpperCase());
            return animalRepository.findByTipoOrderByNomeAsc(tipoEnum);
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    // Busca parcial por nome (Solicitado)
    public List<Animal> findByNomeContaining(String nome) {
        return animalRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Busca exata por nome (Solicitado)
    public Optional<Animal> findByNome(String nome) {
        return animalRepository.findByNome(nome);
    }

    @Transactional
    @CachePut(value = "animais", key = "#id")
    public Animal desativar(UUID id) {
        Animal animal = animalRepository.findById(UUID.fromString(String.valueOf(id)))
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
        animal.setAtivo(false);
        return animalRepository.save(animal);
    }

    @Transactional
    @CachePut(value = "animais", key = "#id")
    public Animal ativar(UUID id) {
        Animal animal = animalRepository.findById(UUID.fromString(String.valueOf(id)))
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
        animal.setAtivo(true);
        return animalRepository.save(animal);
    }
}