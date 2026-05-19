package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import br.com.fiap.javaadv.blog.backend.resources.dtos.RegistroCuidadoResponse;
import br.com.fiap.javaadv.blog.backend.services.RegistroCuidadoService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registros-cuidado")
@RequiredArgsConstructor
public class RegistroCuidadoResource {

    private final RegistroCuidadoService registroCuidadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<RegistroCuidadoResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                registroCuidadoService.findAll(pageable)
                        .stream()
                        .map(RegistroCuidadoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroCuidadoResponse> fetchById(@PathVariable String id) {
        return registroCuidadoService.findById(id)
                .map(RegistroCuidadoResponse::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<RegistroCuidadoResponse>> fetchByAnimal(@PathVariable String animalId,
                                                                           @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                registroCuidadoService.findByAnimalId(animalId, pageable)
                        .stream()
                        .map(RegistroCuidadoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/animal/{animalId}/categoria/{categoria}")
    public ResponseEntity<List<RegistroCuidadoResponse>> fetchByAnimalAndCategoria(
            @PathVariable String animalId,
            @PathVariable String categoria,
            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {

        CategoriaCuidadoEnum categoriaEnum;
        try {
            categoriaEnum = CategoriaCuidadoEnum.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                registroCuidadoService.findByAnimalIdAndCategoria(animalId, categoriaEnum, pageable)
                        .stream()
                        .map(RegistroCuidadoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/animal/{animalId}/diario")
    public ResponseEntity<List<RegistroCuidadoResponse>> fetchDiarioPorAnimal(
            @PathVariable String animalId,
            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                registroCuidadoService.buscarDiarioPorAnimal(animalId, pageable)
                        .stream()
                        .map(RegistroCuidadoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/animal/{animalId}/categoria/{categoria}/count")
    public ResponseEntity<Long> countByAnimalAndCategoria(
            @PathVariable String animalId,
            @PathVariable String categoria) {

        CategoriaCuidadoEnum categoriaEnum;
        try {
            categoriaEnum = CategoriaCuidadoEnum.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(registroCuidadoService.countByAnimalIdAndCategoria(animalId, categoriaEnum));
    }
}