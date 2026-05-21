//package br.com.fiap.javaadv.blog.backend.resources;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.RegistroCuidadoResponse;
//import br.com.fiap.javaadv.blog.backend.services.RegistroCuidadoService;
//import lombok.RequiredArgsConstructor;
//import org.springdoc.core.annotations.ParameterObject;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/registros-cuidado")
//@RequiredArgsConstructor
//public class RegistroCuidadoResource {
//
//    private final RegistroCuidadoService registroCuidadoService;
//
//    @GetMapping("/listar")
//    public ResponseEntity<List<RegistroCuidadoResponse>> fetchAll(
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                registroCuidadoService.findAll(pageable)
//                        .stream()
//                        .map(registroCuidadoService::mapToResponse) // <--- Refatorado
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<RegistroCuidadoResponse> fetchById(@PathVariable UUID id) {
//        return registroCuidadoService.findById(id)
//                .map(registroCuidadoService::mapToResponse) // <--- Refatorado
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/animal/{animalId}")
//    public ResponseEntity<List<RegistroCuidadoResponse>> fetchByAnimal(
//            @PathVariable UUID animalId,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                registroCuidadoService.findByAnimalId(animalId, pageable)
//                        .stream()
//                        .map(registroCuidadoService::mapToResponse) // <--- Refatorado
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/animal/{animalId}/categoria/{categoria}")
//    public ResponseEntity<List<RegistroCuidadoResponse>> fetchByAnimalAndCategoria(
//            @PathVariable UUID animalId,
//            @PathVariable String categoria,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//
//        return parseCategoria(categoria)
//                .map(categoriaEnum -> ResponseEntity.ok(
//                        registroCuidadoService.findByAnimalIdAndCategoria(animalId, categoriaEnum, pageable)
//                                .stream()
//                                .map(registroCuidadoService::mapToResponse) // <--- Refatorado
//                                .collect(Collectors.toList())
//                ))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }
//
//    @GetMapping("/animal/{animalId}/diario")
//    public ResponseEntity<List<RegistroCuidadoResponse>> fetchDiarioPorAnimal(
//            @PathVariable UUID animalId,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                registroCuidadoService.buscarDiarioPorAnimal(animalId, pageable)
//                        .stream()
//                        .map(registroCuidadoService::mapToResponse) // <--- Refatorado
//                        .collect(Collectors.toList())
//        );
//    }
//
//    private java.util.Optional<CategoriaCuidadoEnum> parseCategoria(String categoria) {
//        try {
//            return java.util.Optional.of(CategoriaCuidadoEnum.valueOf(categoria.toUpperCase()));
//        } catch (IllegalArgumentException e) {
//            return java.util.Optional.empty();
//        }
//    }
//}

//package br.com.fiap.javaadv.blog.backend.resources;
//
//import br.com.fiap.javaadv.blog.backend.config.docs.ApiStandardErrors;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.RegistroCuidadoResponse;
//import br.com.fiap.javaadv.blog.backend.services.RegistroCuidadoService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springdoc.core.annotations.ParameterObject;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/registros-cuidado")
//@RequiredArgsConstructor
//@Tag(name = "Registros de Cuidado", description = "Endpoints para gerenciamento de registros de cuidado dos animais")
//public class RegistroCuidadoResource {
//
//    private final RegistroCuidadoService registroCuidadoService;
//
//    @GetMapping("/listar")
//    @Operation(summary = "Listar todos os registros de cuidado")
//    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<List<RegistroCuidadoResponse>> fetchAll(
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                registroCuidadoService.findAll(pageable)
//                        .stream()
//                        .map(registroCuidadoService::toResponse)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Buscar registro por ID")
//    @ApiResponse(responseCode = "200", description = "Registro encontrado")
//    @ApiStandardErrors
//    public ResponseEntity<RegistroCuidadoResponse> fetchById(@PathVariable UUID id) {
//        return registroCuidadoService.findById(id)
//                .map(registroCuidadoService::toResponse)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/animal/{animalId}")
//    @Operation(summary = "Listar registros por animal")
//    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<List<RegistroCuidadoResponse>> fetchByAnimal(
//            @PathVariable UUID animalId,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                registroCuidadoService.findByAnimalId(animalId, pageable)
//                        .stream()
//                        .map(registroCuidadoService::toResponse)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/animal/{animalId}/categoria/{categoria}")
//    @Operation(summary = "Listar registros por animal e categoria")
//    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<List<RegistroCuidadoResponse>> fetchByAnimalAndCategoria(
//            @PathVariable UUID animalId,
//            @PathVariable String categoria,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//
//        return parseCategoria(categoria)
//                .map(categoriaEnum -> ResponseEntity.ok(
//                        registroCuidadoService.findByAnimalIdAndCategoria(animalId, categoriaEnum, pageable)
//                                .stream()
//                                .map(registroCuidadoService::toResponse)
//                                .collect(Collectors.toList())
//                ))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }
//
//    @GetMapping("/animal/{animalId}/diario")
//    @Operation(summary = "Listar diário de cuidados por animal")
//    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<List<RegistroCuidadoResponse>> fetchDiarioPorAnimal(
//            @PathVariable UUID animalId,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                registroCuidadoService.buscarDiarioPorAnimal(animalId, pageable)
//                        .stream()
//                        .map(registroCuidadoService::toResponse)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    private java.util.Optional<CategoriaCuidadoEnum> parseCategoria(String categoria) {
//        try {
//            return java.util.Optional.of(CategoriaCuidadoEnum.valueOf(categoria.toUpperCase()));
//        } catch (IllegalArgumentException e) {
//            return java.util.Optional.empty();
//        }
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.config.docs.ApiStandardErrors;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import br.com.fiap.javaadv.blog.backend.resources.dtos.RegistroCuidadoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.RegistroCuidadoResponse;
import br.com.fiap.javaadv.blog.backend.services.RegistroCuidadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registros-cuidado")
@RequiredArgsConstructor
@Tag(name = "Registros de Cuidado", description = "Endpoints para gerenciamento de registros de cuidado dos animais")
public class RegistroCuidadoResource {

    private final RegistroCuidadoService service;

    // --- C (Create) ---
    @PostMapping
    @Operation(summary = "Criar novo registro de cuidado")
    @ApiResponse(responseCode = "201", description = "Registro criado com sucesso")
    @ApiStandardErrors
    public ResponseEntity<RegistroCuidadoResponse> create(@Valid @RequestBody RegistroCuidadoRequest request) {
        var saved = service.create(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    // --- U (Update) ---
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar registro de cuidado existente")
    @ApiStandardErrors
    public ResponseEntity<RegistroCuidadoResponse> update(@PathVariable UUID id,
                                                          @Valid @RequestBody RegistroCuidadoRequest request) {
        var updated = service.update(id, request);
        return ResponseEntity.ok(service.toResponse(updated));
    }

    // --- D (Delete) ---
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um registro de cuidado")
    @ApiResponse(responseCode = "204", description = "Excluído com sucesso")
    @ApiStandardErrors
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- R (Read) ---
    @GetMapping("/listar")
    public ResponseEntity<List<RegistroCuidadoResponse>> fetchAll(
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable).stream()
                .map(service::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroCuidadoResponse> fetchById(@PathVariable UUID id) {
        return service.findById(id).map(service::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<RegistroCuidadoResponse>> fetchByAnimal(
            @PathVariable UUID animalId, @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.findByAnimalId(animalId, pageable).stream()
                .map(service::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/animal/{animalId}/categoria/{categoria}")
    public ResponseEntity<List<RegistroCuidadoResponse>> fetchByAnimalAndCategoria(
            @PathVariable UUID animalId, @PathVariable String categoria,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return parseCategoria(categoria)
                .map(c -> ResponseEntity.ok(service.findByAnimalIdAndCategoria(animalId, c, pageable).stream()
                        .map(service::toResponse).collect(Collectors.toList())))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/animal/{animalId}/diario")
    public ResponseEntity<List<RegistroCuidadoResponse>> fetchDiarioPorAnimal(
            @PathVariable UUID animalId, @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.buscarDiarioPorAnimal(animalId, pageable).stream()
                .map(service::toResponse).collect(Collectors.toList()));
    }

    private Optional<CategoriaCuidadoEnum> parseCategoria(String categoria) {
        try {
            return Optional.of(CategoriaCuidadoEnum.valueOf(categoria.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}