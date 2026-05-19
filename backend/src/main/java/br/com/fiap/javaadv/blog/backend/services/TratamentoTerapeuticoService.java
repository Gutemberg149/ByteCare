package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.TratamentoTerapeuticoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TratamentoTerapeuticoService {

    private final TratamentoTerapeuticoRepository tratamentoTerapeuticoRepository;

    @Transactional
    public TratamentoTerapeutico create(TratamentoTerapeutico tratamentoTerapeutico) {
        return tratamentoTerapeuticoRepository.save(tratamentoTerapeutico);
    }

    @Transactional
    public Optional<TratamentoTerapeutico> update(String id, TratamentoTerapeutico tratamentoAtualizado) {
        return tratamentoTerapeuticoRepository.findById(id)
                .map(tratamentoExistente -> {
                    tratamentoAtualizado.setId(id);
                    return tratamentoTerapeuticoRepository.save(tratamentoAtualizado);
                });
    }

    @Transactional
    public void delete(String id) {
        tratamentoTerapeuticoRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return tratamentoTerapeuticoRepository.existsById(id);
    }

    public Optional<TratamentoTerapeutico> findById(String id) {
        return tratamentoTerapeuticoRepository.findById(id);
    }

    public Page<TratamentoTerapeutico> findAll(Pageable pageable) {
        return tratamentoTerapeuticoRepository.findAll(pageable);
    }

    public List<TratamentoTerapeutico> findAll() {
        return tratamentoTerapeuticoRepository.findAll();
    }

    public Page<TratamentoTerapeutico> findByAnimalId(String animalId, Pageable pageable) {
        return tratamentoTerapeuticoRepository.findByAnimalId(animalId, pageable);
    }

    public Page<TratamentoTerapeutico> buscarPorMedicamento(String animalId, String medicamento, Pageable pageable) {
        return tratamentoTerapeuticoRepository.buscarPorMedicamento(animalId, medicamento, pageable);
    }
}