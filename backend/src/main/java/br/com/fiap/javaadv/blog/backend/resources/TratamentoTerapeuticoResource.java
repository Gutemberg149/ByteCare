//package br.com.fiap.javaadv.blog.backend.resources;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoRequest;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoResponse;
//import br.com.fiap.javaadv.blog.backend.services.AnimalService;
//import br.com.fiap.javaadv.blog.backend.services.TratamentoTerapeuticoService;
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
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/tratamentos-terapeuticos")
//@RequiredArgsConstructor
//public class TratamentoTerapeuticoResource {
//
//    private final TratamentoTerapeuticoService tratamentoTerapeuticoService;
//    private final AnimalService animalService;
//
//    @PostMapping
//    public ResponseEntity<TratamentoTerapeuticoResponse> create(@Valid @RequestBody TratamentoTerapeuticoRequest request) {
//        Animal animal = animalService.fetchById(request.getIdAnimal())
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
//        TratamentoTerapeutico tratamento = TratamentoTerapeuticoRequest.toEntity(request, animal);
//        TratamentoTerapeutico saved = tratamentoTerapeuticoService.create(tratamento);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saved.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).body(TratamentoTerapeuticoResponse.toDto(saved));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TratamentoTerapeuticoResponse> update(@PathVariable String id,
//                                                                @Valid @RequestBody TratamentoTerapeuticoRequest request) {
//        Animal animal = animalService.fetchById(request.getIdAnimal())
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
//
//        TratamentoTerapeutico tratamentoExistente = tratamentoTerapeuticoService.findById(id)
//                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado com ID: " + id));
//
//        TratamentoTerapeutico tratamentoAtualizado = TratamentoTerapeuticoRequest.toEntity(request, animal);
//        tratamentoAtualizado.setId(id);
//
//        TratamentoTerapeutico saved = tratamentoTerapeuticoService.update(id, tratamentoAtualizado)
//                .orElseThrow(() -> new RuntimeException("Erro ao atualizar tratamento"));
//
//        return ResponseEntity.ok(TratamentoTerapeuticoResponse.toDto(saved));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable String id) {
//        if (tratamentoTerapeuticoService.existsById(id)) {
//            tratamentoTerapeuticoService.delete(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @GetMapping("/listar")
//    public ResponseEntity<List<TratamentoTerapeuticoResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                tratamentoTerapeuticoService.findAll(pageable)
//                        .stream()
//                        .map(TratamentoTerapeuticoResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TratamentoTerapeuticoResponse> fetchById(@PathVariable String id) {
//        return tratamentoTerapeuticoService.findById(id)
//                .map(TratamentoTerapeuticoResponse::toDto)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/animal/{animalId}")
//    public ResponseEntity<List<TratamentoTerapeuticoResponse>> fetchByAnimal(@PathVariable String animalId,
//                                                                             @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                tratamentoTerapeuticoService.findByAnimalId(animalId, pageable)
//                        .stream()
//                        .map(TratamentoTerapeuticoResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/buscar")
//    public ResponseEntity<List<TratamentoTerapeuticoResponse>> buscarPorMedicamento(
//            @RequestParam String animalId,
//            @RequestParam String medicamento,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                tratamentoTerapeuticoService.buscarPorMedicamento(animalId, medicamento, pageable)
//                        .stream()
//                        .map(TratamentoTerapeuticoResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//    @GetMapping
//    public ResponseEntity<List<TratamentoTerapeuticoResponse>> findAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                tratamentoTerapeuticoService.findAll(pageable)
//                        .stream()
//                        .map(TratamentoTerapeuticoResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TratamentoTerapeuticoResponse;
import br.com.fiap.javaadv.blog.backend.services.AnimalService;
import br.com.fiap.javaadv.blog.backend.services.TratamentoTerapeuticoService;
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
import java.util.UUID; // Adicionado o import do UUID
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tratamentos-terapeuticos")
@RequiredArgsConstructor
public class TratamentoTerapeuticoResource {

    private final TratamentoTerapeuticoService tratamentoTerapeuticoService;
    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<TratamentoTerapeuticoResponse> create(@Valid @RequestBody TratamentoTerapeuticoRequest request) {
        // request.getIdAnimal() já retorna UUID
        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
        TratamentoTerapeutico tratamento = TratamentoTerapeuticoRequest.toEntity(request, animal);
        TratamentoTerapeutico saved = tratamentoTerapeuticoService.create(tratamento);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(TratamentoTerapeuticoResponse.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TratamentoTerapeuticoResponse> update(@PathVariable UUID id, // Alterado para UUID
                                                                @Valid @RequestBody TratamentoTerapeuticoRequest request) {
        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));

        TratamentoTerapeutico tratamentoExistente = tratamentoTerapeuticoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado com ID: " + id));

        TratamentoTerapeutico tratamentoAtualizado = TratamentoTerapeuticoRequest.toEntity(request, animal);
        tratamentoAtualizado.setId(id);

        TratamentoTerapeutico saved = tratamentoTerapeuticoService.update(id, tratamentoAtualizado)
                .orElseThrow(() -> new RuntimeException("Erro ao atualizar tratamento"));

        return ResponseEntity.ok(TratamentoTerapeuticoResponse.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) { // Alterado para UUID
        if (tratamentoTerapeuticoService.existsById(id)) {
            tratamentoTerapeuticoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TratamentoTerapeuticoResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                tratamentoTerapeuticoService.findAll(pageable)
                        .stream()
                        .map(TratamentoTerapeuticoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamentoTerapeuticoResponse> fetchById(@PathVariable UUID id) { // Alterado para UUID
        return tratamentoTerapeuticoService.findById(id)
                .map(TratamentoTerapeuticoResponse::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<TratamentoTerapeuticoResponse>> fetchByAnimal(@PathVariable UUID animalId, // Alterado para UUID
                                                                             @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                tratamentoTerapeuticoService.findByAnimalId(animalId, pageable)
                        .stream()
                        .map(TratamentoTerapeuticoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TratamentoTerapeuticoResponse>> buscarPorMedicamento(
            @RequestParam UUID animalId, // Alterado para UUID
            @RequestParam String medicamento,
            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                tratamentoTerapeuticoService.buscarPorMedicamento(animalId, medicamento, pageable)
                        .stream()
                        .map(TratamentoTerapeuticoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping
    public ResponseEntity<List<TratamentoTerapeuticoResponse>> findAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                tratamentoTerapeuticoService.findAll(pageable)
                        .stream()
                        .map(TratamentoTerapeuticoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }
}