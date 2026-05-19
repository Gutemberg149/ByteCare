//package br.com.fiap.javaadv.blog.backend.services;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
//import lombok.RequiredArgsConstructor;
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
//public class AcaoPreventivaService {
//
//    private final AcaoPreventivaRepository acaoPreventivaRepository;
//
//    @Transactional
//    public AcaoPreventiva create(AcaoPreventiva acaoPreventiva) {
//        return acaoPreventivaRepository.save(acaoPreventiva);
//    }
//
//    @Transactional
//    public Optional<AcaoPreventiva> update(String id, AcaoPreventiva acaoAtualizada) {
//        return acaoPreventivaRepository.findById(id)
//                .map(acaoExistente -> {
//                    acaoAtualizada.setId(id);
//                    return acaoPreventivaRepository.save(acaoAtualizada);
//                });
//    }
//
//    @Transactional
//    public void delete(String id) {
//        acaoPreventivaRepository.deleteById(id);
//    }
//
//    public boolean existsById(String id) {
//        return acaoPreventivaRepository.existsById(id);
//    }
//
//    public Optional<AcaoPreventiva> findById(String id) {
//        return acaoPreventivaRepository.findById(id);
//    }
//
//    public Page<AcaoPreventiva> findAll(Pageable pageable) {
//        return acaoPreventivaRepository.findAll(pageable);
//    }
//
//    public List<AcaoPreventiva> findAll() {
//        return acaoPreventivaRepository.findAll();
//    }
//
//    public Page<AcaoPreventiva> findByAnimalId(String animalId, Pageable pageable) {
//        return acaoPreventivaRepository.findByAnimalId(animalId, pageable);
//    }
//
//    public List<AcaoPreventiva> findByAnimalId(String animalId) {
//        return acaoPreventivaRepository.findByAnimalId(animalId);
//    }
//
//    public Page<AcaoPreventiva> buscarPorNomeServico(String animalId, String nome, Pageable pageable) {
//        return acaoPreventivaRepository.buscarPorNomeServico(animalId, nome, pageable);
//    }
//}
package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcaoPreventivaService {

    private final AcaoPreventivaRepository acaoPreventivaRepository;

    @Transactional
    @CacheEvict(value = {"acoesPreventivas", "acoesPorAnimal"}, allEntries = true)
    public AcaoPreventiva create(AcaoPreventiva acaoPreventiva) {
        return acaoPreventivaRepository.save(acaoPreventiva);
    }

    @Transactional
    @CacheEvict(value = {"acoesPreventivas", "acoesPorAnimal"}, allEntries = true)
    public Optional<AcaoPreventiva> update(String id, AcaoPreventiva acaoAtualizada) {
        return acaoPreventivaRepository.findById(id)
                .map(acaoExistente -> {
                    acaoAtualizada.setId(id);
                    return acaoPreventivaRepository.save(acaoAtualizada);
                });
    }

    @Transactional
    @CacheEvict(value = {"acoesPreventivas", "acoesPorAnimal"}, allEntries = true)
    public void delete(String id) {
        acaoPreventivaRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return acaoPreventivaRepository.existsById(id);
    }

    @Cacheable(value = "acoesPreventivas", key = "#id")
    public Optional<AcaoPreventiva> findById(String id) {
        return acaoPreventivaRepository.findById(id);
    }

    @Cacheable(value = "acoesPreventivas", key = "'all_' + #pageable.pageNumber")
    public Page<AcaoPreventiva> findAll(Pageable pageable) {
        return acaoPreventivaRepository.findAll(pageable);
    }

    @Cacheable(value = "acoesPorAnimal", key = "#animalId + '_' + #pageable.pageNumber")
    public Page<AcaoPreventiva> findByAnimalId(String animalId, Pageable pageable) {
        return acaoPreventivaRepository.findByAnimalId(animalId, pageable);
    }

    public List<AcaoPreventiva> findByAnimalId(String animalId) {
        return acaoPreventivaRepository.findByAnimalId(animalId);
    }

    public Page<AcaoPreventiva> buscarPorNomeServico(String animalId, String nome, Pageable pageable) {
        return acaoPreventivaRepository.buscarPorNomeServico(animalId, nome, pageable);
    }
}