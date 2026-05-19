package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtividadeBemEstarService {

    private final AtividadeBemEstarRepository atividadeBemEstarRepository;

    @Transactional
    public AtividadeBemEstar create(AtividadeBemEstar atividadeBemEstar) {
        return atividadeBemEstarRepository.save(atividadeBemEstar);
    }

    @Transactional
    public Optional<AtividadeBemEstar> update(String id, AtividadeBemEstar atividadeAtualizada) {
        return atividadeBemEstarRepository.findById(id)
                .map(atividadeExistente -> {
                    atividadeAtualizada.setDataHoraRegistro(atividadeExistente.getDataHoraRegistro());
                    atividadeAtualizada.setId(id);
                    return atividadeBemEstarRepository.save(atividadeAtualizada);
                });
    }
    @Transactional
    public void delete(String id) {
        atividadeBemEstarRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return atividadeBemEstarRepository.existsById(id);
    }

    public Optional<AtividadeBemEstar> findById(String id) {
        return atividadeBemEstarRepository.findById(id);
    }

    public Page<AtividadeBemEstar> findAll(Pageable pageable) {
        return atividadeBemEstarRepository.findAll(pageable);
    }

    public List<AtividadeBemEstar> findAll() {
        return atividadeBemEstarRepository.findAll();
    }

    public Page<AtividadeBemEstar> findByAnimalId(String animalId, Pageable pageable) {
        return atividadeBemEstarRepository.findByAnimalId(animalId, pageable);
    }

    public Page<AtividadeBemEstar> buscarPorAtividade(String animalId, String atividade, Pageable pageable) {
        return atividadeBemEstarRepository.buscarPorAtividade(animalId, atividade, pageable);
    }


}