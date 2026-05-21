////package br.com.fiap.javaadv.blog.backend.resources;
////
////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
////import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoRequest;
////import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoResponse;
////import br.com.fiap.javaadv.blog.backend.services.AnimalService;
////import br.com.fiap.javaadv.blog.backend.services.TratamentoTerapeuticoService;
////import jakarta.validation.Valid;
////import lombok.RequiredArgsConstructor;
////import org.springdoc.core.annotations.ParameterObject;
////import org.springframework.data.domain.Pageable;
////import org.springframework.data.web.PageableDefault;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
////
////import java.net.URI;
////import java.util.List;
////import java.util.UUID;
////import java.util.stream.Collectors;
////
////@RestController
////@RequestMapping("/api/tratamentos-terapeuticos")
////@RequiredArgsConstructor
////public class TratamentoTerapeuticoResource {
////
////    private final TratamentoTerapeuticoService tratamentoTerapeuticoService;
////    private final AnimalService animalService;
////
////    @PostMapping
////    public ResponseEntity<TratamentoTerapeuticoResponse> create(@Valid @RequestBody TratamentoTerapeuticoRequest request) {
////        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
////                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
////
////        TratamentoTerapeutico tratamento = TratamentoTerapeuticoRequest.toEntity(request, animal);
////        TratamentoTerapeutico saved = tratamentoTerapeuticoService.create(tratamento);
////
////        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
////                .path("/{id}")
////                .buildAndExpand(saved.getId())
////                .toUri();
////
////        // Alterado para usar o serviço que popula o nome
////        return ResponseEntity.created(location).body(tratamentoTerapeuticoService.toResponse(saved));
////    }
////
////    @PutMapping("/{id}")
////    public ResponseEntity<TratamentoTerapeuticoResponse> update(@PathVariable UUID id, @Valid @RequestBody TratamentoTerapeuticoRequest request) {
////        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
////                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
////
////        TratamentoTerapeutico saved = tratamentoTerapeuticoService.update(id, TratamentoTerapeuticoRequest.toEntity(request, animal))
////                .orElseThrow(() -> new RuntimeException("Erro ao atualizar"));
////
////        return ResponseEntity.ok(tratamentoTerapeuticoService.toResponse(saved));
////    }
////
////    @GetMapping("/{id}")
////    public ResponseEntity<TratamentoTerapeuticoResponse> fetchById(@PathVariable UUID id) {
////        return tratamentoTerapeuticoService.findById(id)
////                .map(tratamentoTerapeuticoService::toResponse) // Usa o serviço
////                .map(ResponseEntity::ok)
////                .orElseGet(() -> ResponseEntity.notFound().build());
////    }
////
////    @GetMapping
////    public ResponseEntity<List<TratamentoTerapeuticoResponse>> findAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
////        return ResponseEntity.ok(
////                tratamentoTerapeuticoService.findAll(pageable)
////                        .stream()
////                        .map(tratamentoTerapeuticoService::toResponse) // Usa o serviço para mapear cada um
////                        .collect(Collectors.toList())
////        );
////    }
////
////    @GetMapping("/animal/{animalId}")
////    public ResponseEntity<List<TratamentoTerapeuticoResponse>> fetchByAnimal(@PathVariable UUID animalId, @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
////        return ResponseEntity.ok(
////                tratamentoTerapeuticoService.findByAnimalId(animalId, pageable)
////                        .stream()
////                        .map(tratamentoTerapeuticoService::toResponse) // Usa o serviço
////                        .collect(Collectors.toList())
////        );
////    }
////
////    @GetMapping("/buscar")
////    public ResponseEntity<List<TratamentoTerapeuticoResponse>> buscarPorMedicamento(
////            @RequestParam UUID animalId,
////            @RequestParam String medicamento,
////            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
////        return ResponseEntity.ok(
////                tratamentoTerapeuticoService.buscarPorMedicamento(animalId, medicamento, pageable)
////                        .stream()
////                        .map(tratamentoTerapeuticoService::toResponse) // Usa o serviço
////                        .collect(Collectors.toList())
////        );
////    }
////
////    @DeleteMapping("/{id}")
////    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
////        if (tratamentoTerapeuticoService.existsById(id)) {
////            tratamentoTerapeuticoService.delete(id);
////            return ResponseEntity.noContent().build();
////        }
////        return ResponseEntity.notFound().build();
////    }
////}
//
//package br.com.fiap.javaadv.blog.backend.resources;
//
//import br.com.fiap.javaadv.blog.backend.config.docs.ApiStandardErrors;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoRequest;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoResponse;
//import br.com.fiap.javaadv.blog.backend.services.AnimalService;
//import br.com.fiap.javaadv.blog.backend.services.TratamentoTerapeuticoService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springdoc.core.annotations.ParameterObject;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/tratamentos-terapeuticos")
//@RequiredArgsConstructor
//@Tag(name = "Tratamentos Terapêuticos", description = "Endpoints para gerenciamento de tratamentos terapêuticos")
//public class TratamentoTerapeuticoResource {
//
//    private final TratamentoTerapeuticoService tratamentoTerapeuticoService;
//    private final AnimalService animalService;
//
//    @PostMapping
//    @Operation(summary = "Criar novo tratamento terapêutico")
//    @ApiResponse(responseCode = "201", description = "Tratamento criado com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<TratamentoTerapeuticoResponse> create(@Valid @RequestBody TratamentoTerapeuticoRequest request) {
//        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
//
//        TratamentoTerapeutico tratamento = TratamentoTerapeuticoRequest.toEntity(request, animal);
//        TratamentoTerapeutico saved = tratamentoTerapeuticoService.create(tratamento);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saved.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).body(tratamentoTerapeuticoService.toResponse(saved));
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Atualizar tratamento terapêutico")
//    @ApiResponse(responseCode = "200", description = "Tratamento atualizado com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<TratamentoTerapeuticoResponse> update(@PathVariable UUID id, @Valid @RequestBody TratamentoTerapeuticoRequest request) {
//        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
//
//        TratamentoTerapeutico saved = tratamentoTerapeuticoService.update(id, TratamentoTerapeuticoRequest.toEntity(request, animal))
//                .orElseThrow(() -> new RuntimeException("Erro ao atualizar"));
//
//        return ResponseEntity.ok(tratamentoTerapeuticoService.toResponse(saved));
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Buscar tratamento por ID")
//    @ApiResponse(responseCode = "200", description = "Tratamento encontrado")
//    @ApiStandardErrors
//    public ResponseEntity<TratamentoTerapeuticoResponse> fetchById(@PathVariable UUID id) {
//        return tratamentoTerapeuticoService.findById(id)
//                .map(tratamentoTerapeuticoService::toResponse)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    @Operation(summary = "Listar todos os tratamentos")
//    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<List<TratamentoTerapeuticoResponse>> findAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                tratamentoTerapeuticoService.findAll(pageable)
//                        .stream()
//                        .map(tratamentoTerapeuticoService::toResponse)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/animal/{animalId}")
//    @Operation(summary = "Listar tratamentos por animal")
//    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<List<TratamentoTerapeuticoResponse>> fetchByAnimal(@PathVariable UUID animalId, @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                tratamentoTerapeuticoService.findByAnimalId(animalId, pageable)
//                        .stream()
//                        .map(tratamentoTerapeuticoService::toResponse)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/buscar")
//    @Operation(summary = "Buscar tratamentos por animal e medicamento")
//    @ApiResponse(responseCode = "200", description = "Resultado da busca")
//    @ApiStandardErrors
//    public ResponseEntity<List<TratamentoTerapeuticoResponse>> buscarPorMedicamento(
//            @RequestParam UUID animalId,
//            @RequestParam String medicamento,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                tratamentoTerapeuticoService.buscarPorMedicamento(animalId, medicamento, pageable)
//                        .stream()
//                        .map(tratamentoTerapeuticoService::toResponse)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Excluir tratamento terapêutico")
//    @ApiResponse(responseCode = "204", description = "Tratamento excluído com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
//        if (tratamentoTerapeuticoService.existsById(id)) {
//            tratamentoTerapeuticoService.delete(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.config.docs.ApiStandardErrors;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoResponse;
import br.com.fiap.javaadv.blog.backend.services.AnimalService;
import br.com.fiap.javaadv.blog.backend.services.TratamentoTerapeuticoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tratamentos-terapeuticos")
@RequiredArgsConstructor
@Tag(name = "Tratamentos Terapêuticos", description = "Endpoints para gerenciamento de tratamentos")
public class TratamentoTerapeuticoResource {

    private final TratamentoTerapeuticoService service;
    private final AnimalService animalService;

    @PostMapping
    @Operation(summary = "Criar novo tratamento")
    @ApiStandardErrors
    public ResponseEntity<TratamentoTerapeuticoResponse> create(@Valid @RequestBody TratamentoTerapeuticoRequest request) {
        // O service já faz a criação e retorna o DTO pronto
        var response = service.create(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tratamento")
    @ApiStandardErrors
    public ResponseEntity<TratamentoTerapeuticoResponse> update(@PathVariable UUID id, @Valid @RequestBody TratamentoTerapeuticoRequest request) {
        // O service já recebe o Request e faz a atualização retornando o DTO
        var response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    @ApiStandardErrors
    public ResponseEntity<TratamentoTerapeuticoResponse> fetchById(@PathVariable UUID id) {
        // O service.findById já retorna o DTO
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos")
    public ResponseEntity<Page<TratamentoTerapeuticoResponse>> findAll(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        // O service já retorna Page<DTO>, não precisa de stream
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/animal/{animalId}")
    @Operation(summary = "Listar por animal")
    public ResponseEntity<Page<TratamentoTerapeuticoResponse>> fetchByAnimal(
            @PathVariable UUID animalId, @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.findByAnimalId(animalId, pageable));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar por animal e medicamento")
    public ResponseEntity<Page<TratamentoTerapeuticoResponse>> buscar(
            @RequestParam UUID animalId, @RequestParam String medicamento, @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.buscarPorMedicamento(animalId, medicamento, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tratamento")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}