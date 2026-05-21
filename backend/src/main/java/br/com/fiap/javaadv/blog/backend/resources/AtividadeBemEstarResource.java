////package br.com.fiap.javaadv.blog.backend.resources;
////
////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
////import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
////import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
////import br.com.fiap.javaadv.blog.backend.services.AnimalService;
////import br.com.fiap.javaadv.blog.backend.services.AtividadeBemEstarService;
////import jakarta.validation.Valid;
////import lombok.RequiredArgsConstructor;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
////
////import java.net.URI;
////import java.util.List;
////import java.util.UUID;
////
////@RestController
////@RequestMapping("/api/atividades-bem-estar")
////@RequiredArgsConstructor
////public class AtividadeBemEstarResource {
////
////    private final AtividadeBemEstarService service;
////    private final AnimalService animalService;
////
////    @PostMapping
////    public ResponseEntity<AtividadeBemEstarResponse> create(@Valid @RequestBody AtividadeBemEstarRequest request) {
////        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
////                .orElseThrow(() -> new RuntimeException("Animal não encontrado: " + request.getIdAnimal()));
////
////        AtividadeBemEstar saved = service.create(AtividadeBemEstarRequest.toEntity(request, animal));
////
////        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
////                .path("/{id}")
////                .buildAndExpand(saved.getId())
////                .toUri();
////
////        return ResponseEntity.created(location).body(AtividadeBemEstarResponse.toDto(saved));
////    }
////
////    @GetMapping
////    public ResponseEntity<List<AtividadeBemEstarResponse>> findAll() {
////        return ResponseEntity.ok(service.findAll());
////    }
////
////    @GetMapping("/{id}")
////    public ResponseEntity<AtividadeBemEstarResponse> findById(@PathVariable UUID id) {
////        return service.findById(id)
////                .map(ResponseEntity::ok)
////                .orElse(ResponseEntity.notFound().build());
////    }
////
////    @GetMapping("/animal/{animalId}")
////    public ResponseEntity<List<AtividadeBemEstarResponse>> findByAnimal(@PathVariable UUID animalId) {
////        return ResponseEntity.ok(service.findAllByAnimalId(animalId));
////    }
////
////    @GetMapping("/buscar")
////    public ResponseEntity<List<AtividadeBemEstarResponse>> buscarPorAtividade(
////            @RequestParam UUID animalId, @RequestParam String atividade) {
////        return ResponseEntity.ok(service.buscarPorAtividade(animalId, atividade));
////    }
////
////    @PutMapping("/{id}")
////    public ResponseEntity<AtividadeBemEstarResponse> update(@PathVariable UUID id,
////                                                            @Valid @RequestBody AtividadeBemEstarRequest request) {
////        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
////                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
////
////        return service.update(id, request, animal)
////                .map(ResponseEntity::ok)
////                .orElse(ResponseEntity.notFound().build());
////    }
////
////    @DeleteMapping("/{id}")
////    public ResponseEntity<Void> delete(@PathVariable UUID id) {
////        if (!service.existsById(id)) return ResponseEntity.notFound().build();
////        service.delete(id);
////        return ResponseEntity.noContent().build();
////    }
////}
//
//package br.com.fiap.javaadv.blog.backend.resources;
//
//import br.com.fiap.javaadv.blog.backend.config.docs.ApiStandardErrors;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
//import br.com.fiap.javaadv.blog.backend.services.AnimalService;
//import br.com.fiap.javaadv.blog.backend.services.AtividadeBemEstarService;
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
//@RequestMapping("/api/atividades-bem-estar")
//@RequiredArgsConstructor
//@Tag(name = "Atividades de Bem-Estar", description = "Endpoints para gerenciamento de atividades de bem-estar dos animais")
//public class AtividadeBemEstarResource {
//
//    private final AtividadeBemEstarService service;
//    private final AnimalService animalService;
//
//    @PostMapping
//    @Operation(summary = "Criar nova atividade de bem-estar")
//    @ApiResponse(responseCode = "201", description = "Atividade criada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<AtividadeBemEstarResponse> create(@Valid @RequestBody AtividadeBemEstarRequest request) {
//        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado: " + request.getIdAnimal()));
//
//        AtividadeBemEstar saved = service.create(AtividadeBemEstarRequest.toEntity(request, animal));
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saved.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).body(AtividadeBemEstarResponse.toDto(saved));
//    }
//
//    @GetMapping
//    @Operation(summary = "Listar todas as atividades de bem-estar")
//    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<List<AtividadeBemEstarResponse>> findAll() {
//        return ResponseEntity.ok(service.findAll());
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Buscar atividade por ID")
//    @ApiResponse(responseCode = "200", description = "Atividade encontrada")
//    @ApiStandardErrors
//    public ResponseEntity<AtividadeBemEstarResponse> findById(@PathVariable UUID id) {
//        return service.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/animal/{animalId}")
//    @Operation(summary = "Listar atividades por ID do animal")
//    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<List<AtividadeBemEstarResponse>> findByAnimal(@PathVariable UUID animalId) {
//        return ResponseEntity.ok(service.findAllByAnimalId(animalId));
//    }
//
//    @GetMapping("/buscar")
//    @Operation(summary = "Buscar atividade por nome e animal")
//    @ApiResponse(responseCode = "200", description = "Resultado da busca")
//    @ApiStandardErrors
//    public ResponseEntity<List<AtividadeBemEstarResponse>> buscarPorAtividade(
//            @RequestParam UUID animalId, @RequestParam String atividade) {
//        return ResponseEntity.ok(service.buscarPorAtividade(animalId, atividade));
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Atualizar atividade de bem-estar")
//    @ApiResponse(responseCode = "200", description = "Atividade atualizada com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<AtividadeBemEstarResponse> update(@PathVariable UUID id,
//                                                            @Valid @RequestBody AtividadeBemEstarRequest request) {
//        Animal animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()))
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
//
//        return service.update(id, request, animal)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Excluir atividade de bem-estar")
//    @ApiResponse(responseCode = "204", description = "Atividade excluída com sucesso")
//    @ApiStandardErrors
//    public ResponseEntity<Void> delete(@PathVariable UUID id) {
//        if (!service.existsById(id)) return ResponseEntity.notFound().build();
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.config.docs.ApiStandardErrors;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
import br.com.fiap.javaadv.blog.backend.services.AnimalService;
import br.com.fiap.javaadv.blog.backend.services.AtividadeBemEstarService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/atividades-bem-estar")
@RequiredArgsConstructor
@Tag(name = "Atividades de Bem-Estar")
public class AtividadeBemEstarResource {

    private final AtividadeBemEstarService service;
    private final AnimalService animalService;

    @PostMapping
    @Operation(summary = "Criar nova atividade de bem-estar")
    @ApiStandardErrors
    public ResponseEntity<AtividadeBemEstarResponse> create(@Valid @RequestBody AtividadeBemEstarRequest request) {
        // O Service agora retorna Animal direto; se não achar, lança exceção automaticamente
        var animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()));
        var saved = service.create(AtividadeBemEstarRequest.toEntity(request, animal));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).body(AtividadeBemEstarResponse.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<AtividadeBemEstarResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeBemEstarResponse> findById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<AtividadeBemEstarResponse>> findByAnimal(@PathVariable UUID animalId) {
        return ResponseEntity.ok(service.findAllByAnimalId(animalId));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AtividadeBemEstarResponse>> buscarPorAtividade(
            @RequestParam UUID animalId, @RequestParam String atividade) {
        return ResponseEntity.ok(service.buscarPorAtividade(animalId, atividade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeBemEstarResponse> update(@PathVariable UUID id,
                                                            @Valid @RequestBody AtividadeBemEstarRequest request) {
        var animal = animalService.fetchById(UUID.fromString(request.getIdAnimal()));

        return service.update(id, request, animal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}