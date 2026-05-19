package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.RegistroCuidadoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistroCuidadoService {

    private final RegistroCuidadoRepository registroCuidadoRepository;

    @Transactional
    public RegistroCuidado save(RegistroCuidado registroCuidado) {
        return registroCuidadoRepository.save(registroCuidado);
    }

    @Transactional
    public void delete(String id) {
        registroCuidadoRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return registroCuidadoRepository.existsById(id);
    }

    public Optional<RegistroCuidado> findById(String id) {
        return registroCuidadoRepository.findById(id);
    }

    public Page<RegistroCuidado> findAll(Pageable pageable) {
        return registroCuidadoRepository.findAll(pageable);
    }

    public Page<RegistroCuidado> findByAnimalId(String animalId, Pageable pageable) {
        return registroCuidadoRepository.findByAnimalId(animalId, pageable);
    }

    public Page<RegistroCuidado> findByAnimalIdAndCategoria(String animalId,
                                                            CategoriaCuidadoEnum categoria,
                                                            Pageable pageable) {
        return registroCuidadoRepository.findByAnimalIdAndCategoria(animalId, categoria, pageable);
    }

    public Page<RegistroCuidado> buscarDiarioPorAnimal(String animalId, Pageable pageable) {
        return registroCuidadoRepository.buscarDiarioPorAnimal(animalId, pageable);
    }

    public long countByAnimalIdAndCategoria(String animalId, CategoriaCuidadoEnum categoria) {
        return registroCuidadoRepository.countByAnimalIdAndCategoria(animalId, categoria);
    }
}