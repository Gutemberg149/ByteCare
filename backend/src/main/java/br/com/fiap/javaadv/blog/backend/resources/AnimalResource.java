package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AnimalRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AnimalResponse;
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
@RequestMapping("/api/animais")
@RequiredArgsConstructor

public class AnimalResource {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalResponse> create(@Valid @RequestBody AnimalRequest request) {
        Animal animal = request.toEntity();
        Animal savedAnimal = animalService.create(animal);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAnimal.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(AnimalResponse.toDto(savedAnimal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> update(@PathVariable String id, @Valid @RequestBody AnimalRequest request) {
        Animal animal = request.toEntity();
        animal.setId(id);

        return animalService.update(id, animal)
                .map(animalAtualizado -> ResponseEntity.ok(AnimalResponse.toDto(animalAtualizado)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        if (animalService.existsById(id)) {
            animalService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AnimalResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                animalService.fetchAll(pageable)
                        .stream()
                        .map(AnimalResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> fetchById(@PathVariable String id) {
        return animalService.fetchById(id)
                .map(AnimalResponse::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<AnimalResponse>> fetchAtivos() {
        return ResponseEntity.ok(
                animalService.findByAtivoTrue()
                        .stream()
                        .map(AnimalResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<AnimalResponse>> fetchByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(
                animalService.findByTipo(tipo)
                        .stream()
                        .map(AnimalResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<AnimalResponse>> fetchByNome(@PathVariable String nome) {
        return ResponseEntity.ok(
                animalService.findByNomeContaining(nome)
                        .stream()
                        .map(AnimalResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(@PathVariable String id) {
        return animalService.fetchById(id)
                .map(animal -> {
                    animalService.desativar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable String id) {
        return animalService.fetchById(id)
                .map(animal -> {
                    animalService.ativar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}