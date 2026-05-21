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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Transactional
    @CachePut(value = "animais", key = "#result.id")
    public Animal create(Animal animal) {
        if (animalRepository.existsByNomeIgnoreCase(animal.getNome())) {
            throw new IllegalArgumentException("Já existe um animal com o nome: " + animal.getNome());
        }
        return animalRepository.save(animal);
    }

    @Transactional
    @CachePut(value = "animais", key = "#id")
    public Animal update(UUID id, Animal novo) {

        System.out.println("DEBUG: Atualizando animal " + id + " com raca=" + novo.getRaca());

        return animalRepository.findById(id)
                .map(existente -> {

                    existente.setNome(novo.getNome());
                    existente.setTipo(novo.getTipo());
                    existente.setRaca(novo.getRaca());
                    existente.setDataNascimento(novo.getDataNascimento());
                    existente.setObservacaoGeral(novo.getObservacaoGeral());
                    existente.setAtivo(novo.isAtivo());

                    return animalRepository.save(existente);
                })
                .orElseThrow(() -> new RuntimeException("Animal não encontrado para o ID: " + id));
    }

    @Transactional
    @CacheEvict(value = "animais", key = "#id")
    public void delete(UUID id) {
        if (!animalRepository.existsById(id)) throw new RuntimeException("ID não encontrado");
        animalRepository.deleteById(id);
    }

    public boolean existsById(UUID id) { return animalRepository.existsById(id); }

    @Cacheable(value = "animais", key = "#id")
    public Animal fetchById(UUID id) {
        return animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
    }

    public List<Animal> findAll() { return animalRepository.findAllByOrderByNomeAsc(); }

    public List<Animal> findByTipo(TipoAnimalEnum tipo) { return animalRepository.findByTipoOrderByNomeAsc(tipo); }

    @Transactional
    @CachePut(value = "animais", key = "#id")
    public Animal setStatus(UUID id, boolean ativo) {
        Animal animal = fetchById(id);
        animal.setAtivo(ativo);
        return animalRepository.save(animal);
    }
}