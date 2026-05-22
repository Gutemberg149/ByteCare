package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtividadeBemEstarService {

    private final AtividadeBemEstarRepository repository;

    @Transactional
    public AtividadeBemEstar create(AtividadeBemEstar entidade) {
        // Garantir integridade mínima
        if (entidade.getAnimal() == null) {
            throw new IllegalArgumentException("A atividade deve estar vinculada a um animal.");
        }

        entidade.setDataHoraRegistro(LocalDateTime.now());
        entidade.setAnimalId(entidade.getAnimal().getId());

        return repository.save(entidade);
    }

    @Transactional
    public AtividadeBemEstarResponse update(UUID id, AtividadeBemEstarRequest request, Animal animal) {
        AtividadeBemEstar existente = findEntityById(id);

        existente.setAtividade(request.getNomeAtividade());
        existente.setObservacaoAtividade(request.getObservacaoAtividade());
        existente.setDuracao(request.getDuracao());
        existente.setObservacao(request.getObservacao());
        existente.setCategoria(request.getCategoria());
        existente.setAnimal(animal);
        existente.setAnimalId(animal.getId());

        return AtividadeBemEstarResponse.toDto(repository.save(existente));
    }

    @Transactional(readOnly = true)
    public AtividadeBemEstarResponse findById(UUID id) {
        return AtividadeBemEstarResponse.toDto(findEntityById(id));
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

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Atividade não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }

    // Método auxiliar para centralizar a busca e evitar código duplicado
    private AtividadeBemEstar findEntityById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada com ID: " + id));
    }
}