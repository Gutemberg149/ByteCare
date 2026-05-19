//package br.com.fiap.javaadv.blog.backend.resources;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
//import br.com.fiap.javaadv.blog.backend.services.AtividadeBemEstarService;
//import br.com.fiap.javaadv.blog.backend.services.AnimalService;
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
//@RequestMapping("/api/atividades-bem-estar")
//@RequiredArgsConstructor
//public class AtividadeBemEstarResource {
//
//    private final AtividadeBemEstarService atividadeBemEstarService;
//    private final AnimalService animalService;
//
//    @PostMapping
//    public ResponseEntity<AtividadeBemEstarResponse> create(@Valid @RequestBody AtividadeBemEstarRequest request) {
//        Animal animal = animalService.fetchById(request.getIdAnimal())
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
//        AtividadeBemEstar atividade = AtividadeBemEstarRequest.toEntity(request, animal);
//        AtividadeBemEstar saved = atividadeBemEstarService.create(atividade);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saved.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).body(AtividadeBemEstarResponse.toDto(saved));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<AtividadeBemEstarResponse> update(@PathVariable String id,
//                                                            @Valid @RequestBody AtividadeBemEstarRequest request) {
//        Animal animal = animalService.fetchById(request.getIdAnimal())
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
//
//        AtividadeBemEstar atividadeExistente = atividadeBemEstarService.findById(id)
//                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + id));
//
//        AtividadeBemEstar atividadeAtualizada = AtividadeBemEstarRequest.toEntity(request, animal);
//        atividadeAtualizada.setId(id);
//
//        AtividadeBemEstar saved = atividadeBemEstarService.update(id, atividadeAtualizada)
//                .orElseThrow(() -> new RuntimeException("Erro ao atualizar atividade"));
//
//        return ResponseEntity.ok(AtividadeBemEstarResponse.toDto(saved));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable String id) {
//        if (atividadeBemEstarService.existsById(id)) {
//            atividadeBemEstarService.delete(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @GetMapping("/listar")
//    public ResponseEntity<List<AtividadeBemEstarResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                atividadeBemEstarService.findAll(pageable)
//                        .stream()
//                        .map(AtividadeBemEstarResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AtividadeBemEstarResponse> fetchById(@PathVariable String id) {
//        return atividadeBemEstarService.findById(id)
//                .map(AtividadeBemEstarResponse::toDto)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/animal/{animalId}")
//    public ResponseEntity<List<AtividadeBemEstarResponse>> fetchByAnimal(@PathVariable String animalId,
//                                                                         @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                atividadeBemEstarService.findByAnimalId(animalId, pageable)
//                        .stream()
//                        .map(AtividadeBemEstarResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/buscar")
//    public ResponseEntity<List<AtividadeBemEstarResponse>> buscarPorAtividade(
//            @RequestParam String animalId,
//            @RequestParam String atividade,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                atividadeBemEstarService.buscarPorAtividade(animalId, atividade, pageable)
//                        .stream()
//                        .map(AtividadeBemEstarResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AtividadeBemEstarResponse;
import br.com.fiap.javaadv.blog.backend.services.AtividadeBemEstarService;
import br.com.fiap.javaadv.blog.backend.services.AnimalService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/atividades-bem-estar")
@RequiredArgsConstructor
public class AtividadeBemEstarResource {

    private final AtividadeBemEstarService atividadeBemEstarService;
    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AtividadeBemEstarResponse> create(@Valid @RequestBody AtividadeBemEstarRequest request) {
        Animal animal = animalService.fetchById(request.getIdAnimal())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
        AtividadeBemEstar atividade = AtividadeBemEstarRequest.toEntity(request, animal);
        AtividadeBemEstar saved = atividadeBemEstarService.create(atividade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(AtividadeBemEstarResponse.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<AtividadeBemEstarResponse>> findAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                atividadeBemEstarService.findAll(pageable)
                        .stream()
                        .map(AtividadeBemEstarResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeBemEstarResponse> findById(@PathVariable String id) {
        return atividadeBemEstarService.findById(id)
                .map(AtividadeBemEstarResponse::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<AtividadeBemEstarResponse>> findByAnimal(@PathVariable String animalId,
                                                                        @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                atividadeBemEstarService.findByAnimalId(animalId, pageable)
                        .stream()
                        .map(AtividadeBemEstarResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AtividadeBemEstarResponse>> buscarPorAtividade(
            @RequestParam String animalId,
            @RequestParam String atividade,
            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                atividadeBemEstarService.buscarPorAtividade(animalId, atividade, pageable)
                        .stream()
                        .map(AtividadeBemEstarResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeBemEstarResponse> update(@PathVariable String id,
                                                            @Valid @RequestBody AtividadeBemEstarRequest request) {
        // Verificar se a atividade existe
        AtividadeBemEstar atividadeExistente = atividadeBemEstarService.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + id));

        // Buscar o animal
        Animal animal = animalService.fetchById(request.getIdAnimal())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));

        // Criar atividade atualizada
        AtividadeBemEstar atividadeAtualizada = AtividadeBemEstarRequest.toEntity(request, animal);

        // Preservar a data original e o ID
        atividadeAtualizada.setId(id);
        atividadeAtualizada.setDataHoraRegistro(atividadeExistente.getDataHoraRegistro());
        atividadeAtualizada.setCategoria(atividadeExistente.getCategoria());

        // Salvar
        AtividadeBemEstar saved = atividadeBemEstarService.update(id, atividadeAtualizada)
                .orElseThrow(() -> new RuntimeException("Erro ao atualizar atividade"));

        return ResponseEntity.ok(AtividadeBemEstarResponse.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (atividadeBemEstarService.existsById(id)) {
            atividadeBemEstarService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}