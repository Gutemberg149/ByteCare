package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Transactional
    public Animal create(Animal animal) {
        return animalRepository.save(animal);
    }

    @Transactional
    public Optional<Animal> update(String id, Animal animalAtualizado) {
        return animalRepository.findById(id)
                .map(animalExistente -> {
                    animalAtualizado.setId(id);
                    return animalRepository.save(animalAtualizado);
                });
    }

    @Transactional
    public void delete(String id) {
        animalRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return animalRepository.existsById(id);
    }

    public Optional<Animal> fetchById(String id) {
        return animalRepository.findById(id);
    }

    public Page<Animal> fetchAll(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public List<Animal> findByAtivoTrue() {
        return animalRepository.findByAtivoTrue();
    }

    // CORRIGIDO: Agora recebe TipoAnimalEnum diretamente
    public List<Animal> findByTipo(TipoAnimalEnum tipo) {
        return animalRepository.findByTipo(tipo);
    }

    // Sobrecarga do método para receber String (se preferir)
    public List<Animal> findByTipo(String tipo) {
        try {
            TipoAnimalEnum tipoEnum = TipoAnimalEnum.valueOf(tipo.toUpperCase());
            return animalRepository.findByTipo(tipoEnum);
        } catch (IllegalArgumentException e) {
            return List.of(); // Retorna lista vazia se o tipo for inválido
        }
    }

    public List<Animal> findByNomeContaining(String nome) {
        return animalRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Animal desativar(String id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
        animal.setAtivo(false);
        return animalRepository.save(animal);
    }

    @Transactional
    public Animal ativar(String id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + id));
        animal.setAtivo(true);
        return animalRepository.save(animal);
    }
}