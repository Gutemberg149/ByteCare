//package br.com.fiap.javaadv.blog.backend.resources;
//
//import br.com.fiap.javaadv.blog.backend.config.docs.ApiStandardErrors;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AnimalRequest;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AnimalResponse;
//import br.com.fiap.javaadv.blog.backend.services.AnimalService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/animais")
//@RequiredArgsConstructor
//@Tag(name = "Animais", description = "Endpoints para gerenciamento de animais")
//public class AnimalResource {
//
//    private final AnimalService animalService;
//
//    @PostMapping
//    @Operation(summary = "Cadastrar um novo animal")
//    @ApiResponse(responseCode = "201", description = "Animal criado com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<AnimalResponse> create(@Valid @RequestBody AnimalRequest request) {
//        var savedAnimal = animalService.create(request.toEntity());
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(savedAnimal.getId()).toUri();
//
//        return ResponseEntity.created(location).body(AnimalResponse.toDto(savedAnimal));
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Atualizar dados de um animal")
//    @ApiStandardErrors
//    public ResponseEntity<AnimalResponse> update(@PathVariable UUID id, @Valid @RequestBody AnimalRequest request) {
//        return animalService.update(id, request.toEntity())
//                .map(animal -> ResponseEntity.ok(AnimalResponse.toDto(animal)))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    @Operation(summary = "Listar todos os animais")
//    public ResponseEntity<List<AnimalResponse>> findAll() {
//        return ResponseEntity.ok(animalService.findAll().stream().map(AnimalResponse::toDto).toList());
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Buscar animal por ID")
//    public ResponseEntity<AnimalResponse> fetchById(@PathVariable UUID id) {
//        return animalService.fetchById(id)
//                .map(a -> ResponseEntity.ok(AnimalResponse.toDto(a)))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/tipo/{tipo}")
//    @Operation(summary = "Buscar animais por tipo")
//    public ResponseEntity<List<AnimalResponse>> fetchByTipo(@PathVariable String tipo) {
//        try {
//            var tipoEnum = TipoAnimalEnum.valueOf(tipo.toUpperCase());
//            return ResponseEntity.ok(animalService.findByTipo(tipoEnum).stream().map(AnimalResponse::toDto).toList());
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Remover um animal")
//    @ApiResponse(responseCode = "204")
//    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
//        if (!animalService.existsById(id)) return ResponseEntity.notFound().build();
//        animalService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PatchMapping("/{id}/{status}")
//    @Operation(summary = "Ativar ou desativar animal")
//    public ResponseEntity<Void> toggleStatus(@PathVariable UUID id, @PathVariable String status) {
//        try {
//            if ("ativar".equalsIgnoreCase(status)) animalService.ativar(id);
//            else if ("desativar".equalsIgnoreCase(status)) animalService.desativar(id);
//            else return ResponseEntity.badRequest().build();
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.config.docs.ApiStandardErrors;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AnimalRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AnimalResponse;
import br.com.fiap.javaadv.blog.backend.services.AnimalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animais")
@RequiredArgsConstructor
@Tag(name = "Animais")
public class AnimalResource {

    private final AnimalService service;

    @PostMapping
    public ResponseEntity<AnimalResponse> create(@Valid @RequestBody AnimalRequest req) {
        var saved = service.create(req.toEntity());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(AnimalResponse.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> update(@PathVariable UUID id, @Valid @RequestBody AnimalRequest req) {
        return ResponseEntity.ok(AnimalResponse.toDto(service.update(id, req.toEntity())));
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(AnimalResponse::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(AnimalResponse.toDto(service.fetchById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<Void> patch(@PathVariable UUID id, @PathVariable String status) {
        service.setStatus(id, "ativar".equalsIgnoreCase(status));
        return ResponseEntity.noContent().build();
    }
}