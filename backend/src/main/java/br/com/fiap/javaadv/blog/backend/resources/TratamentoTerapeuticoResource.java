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

        var response = service.create(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tratamento")
    @ApiStandardErrors
    public ResponseEntity<TratamentoTerapeuticoResponse> update(@PathVariable UUID id, @Valid @RequestBody TratamentoTerapeuticoRequest request) {

        var response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    @ApiStandardErrors
    public ResponseEntity<TratamentoTerapeuticoResponse> fetchById(@PathVariable UUID id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos")
    public ResponseEntity<Page<TratamentoTerapeuticoResponse>> findAll(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {

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