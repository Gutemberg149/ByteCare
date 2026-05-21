package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.TratamentoTerapeuticoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TratamentoTerapeuticoService {

    private final TratamentoTerapeuticoRepository repository;
    private final AnimalRepository animalRepository;

    @Transactional
    public TratamentoTerapeuticoResponse create(TratamentoTerapeuticoRequest request) {
        Animal animal = findAnimalById(request.getIdAnimal());
        TratamentoTerapeutico entity = request.toEntity(animal);
        return TratamentoTerapeuticoResponse.toDto(repository.save(entity));
    }
    @Transactional
    public TratamentoTerapeuticoResponse update(UUID id, TratamentoTerapeuticoRequest request) {
        TratamentoTerapeutico existing = findTratamentoById(id);


        Animal animal = findAnimalById(request.getIdAnimal());

        if (request.getNomeAnimal() != null) {
            animal.setNome(request.getNomeAnimal());
            animalRepository.save(animal);
        }

        updateEntityFromRequest(existing, request, animal);
        return TratamentoTerapeuticoResponse.toDto(repository.save(existing));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Tratamento não encontrado para exclusão");
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public TratamentoTerapeuticoResponse findById(UUID id) {
        return TratamentoTerapeuticoResponse.toDto(findTratamentoById(id));
    }

    @Transactional(readOnly = true)
    public Page<TratamentoTerapeuticoResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(TratamentoTerapeuticoResponse::toDto);
    }

    @Transactional(readOnly = true)
    public Page<TratamentoTerapeuticoResponse> findByAnimalId(UUID animalId, Pageable pageable) {
        return repository.findByAnimalId(animalId, pageable).map(TratamentoTerapeuticoResponse::toDto);
    }

    @Transactional(readOnly = true)
    public Page<TratamentoTerapeuticoResponse> buscarPorMedicamento(UUID animalId, String med, Pageable pageable) {
        return repository.buscarPorMedicamento(animalId, med, pageable).map(TratamentoTerapeuticoResponse::toDto);
    }


    private TratamentoTerapeutico findTratamentoById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));
    }

    private Animal findAnimalById(String id) {
        return animalRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    private void updateEntityFromRequest(TratamentoTerapeutico entity, TratamentoTerapeuticoRequest request, Animal animal) {
        entity.setMedicamento(request.getMedicamento());
        entity.setDosagem(request.getDosagem());
        entity.setFrequencia(request.getFrequencia());
        entity.setDuracaoTratamento(request.getDuracaoTratamento());
        entity.setObservacao(request.getObservacao());
        entity.setCategoria(request.getCategoria());
        entity.setAnimal(animal);
    }
    @Transactional(readOnly = true)
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}