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
            // Atualiza os campos
            existente.setAtividade(request.getNomeAtividade());
            existente.setObservacaoAtividade(request.getObservacaoAtividade());
            existente.setDuracao(request.getDuracao());
            existente.setObservacao(request.getObservacao());
            existente.setCategoria(request.getCategoria());

            // Atualiza o relacionamento
            existente.setAnimal(animal);
            existente.setAnimalId(animal.getId());

            // Salva e retorna o DTO
            return AtividadeBemEstarResponse.toDto(repository.save(existente));
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