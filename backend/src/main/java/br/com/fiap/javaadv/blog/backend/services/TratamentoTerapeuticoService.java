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
        Animal animal = animalRepository.findById(UUID.fromString(request.getIdAnimal()))
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        // A entidade agora gerencia o relacionamento automaticamente
        TratamentoTerapeutico entity = TratamentoTerapeuticoRequest.toEntity(request, animal);
        return TratamentoTerapeuticoResponse.toDto(repository.save(entity));
    }

    @Transactional
    public TratamentoTerapeuticoResponse update(UUID id, TratamentoTerapeuticoRequest request) {
        TratamentoTerapeutico existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));

        Animal animal = animalRepository.findById(UUID.fromString(request.getIdAnimal()))
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        existing.setMedicamento(request.getMedicamento());
        existing.setDosagem(request.getDosagem());
        existing.setFrequencia(request.getFrequencia());
        existing.setDuracaoTratamento(request.getDuracaoTratamento());
        existing.setObservacao(request.getObservacao());
        existing.setCategoria(request.getCategoria());

        // Apenas setamos o objeto animal. O Hibernate cuida da coluna ANIMAL_ID.
        existing.setAnimal(animal);

        return TratamentoTerapeuticoResponse.toDto(repository.save(existing));
    }

    @Transactional
    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Transactional(readOnly = true)
    public TratamentoTerapeuticoResponse findById(UUID id) {
        return repository.findById(id)
                .map(TratamentoTerapeuticoResponse::toDto)
                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));
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

    @Transactional(readOnly = true)
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}