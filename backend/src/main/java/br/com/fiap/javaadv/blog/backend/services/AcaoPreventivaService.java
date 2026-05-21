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

        var animal = animalService.fetchById(request.getIdAnimal());

        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado com ID: " + request.getIdAnimal());
        }

        var acao = AcaoPreventivaRequest.toEntity(request, animal);
        return AcaoPreventivaResponse.toDto(repository.save(acao));
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public Optional<AcaoPreventivaResponse> update(UUID id, AcaoPreventivaRequest request) {
        return repository.findById(id).map(existente -> {

            var animal = animalService.fetchById(request.getIdAnimal());

            if (animal == null) {
                throw new IllegalArgumentException("Animal não encontrado com ID: " + request.getIdAnimal());
            }

            var atualizada = AcaoPreventivaRequest.toEntity(request, animal);
            atualizada.setId(id);
            atualizada.setDataHoraRegistro(existente.getDataHoraRegistro());

            return AcaoPreventivaResponse.toDto(repository.save(atualizada));
        });
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
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