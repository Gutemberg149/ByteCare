package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaResponse;
import br.com.fiap.javaadv.blog.backend.services.AcaoPreventivaService;
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
@RequestMapping("/api/acoes-preventivas")
@RequiredArgsConstructor
public class AcaoPreventivaResource {

    private final AcaoPreventivaService acaoPreventivaService;
    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AcaoPreventivaResponse> create(@Valid @RequestBody AcaoPreventivaRequest request) {
        Animal animal = animalService.fetchById(request.getIdAnimal())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        AcaoPreventiva acao = AcaoPreventivaRequest.toEntity(request, animal);
        AcaoPreventiva saved = acaoPreventivaService.create(acao);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(AcaoPreventivaResponse.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<AcaoPreventivaResponse>> findAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                acaoPreventivaService.findAll(pageable)
                        .stream()
                        .map(AcaoPreventivaResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoPreventivaResponse> findById(@PathVariable String id) {
        return acaoPreventivaService.findById(id)
                .map(AcaoPreventivaResponse::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<AcaoPreventivaResponse>> findByAnimal(@PathVariable String animalId,
                                                                     @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                acaoPreventivaService.findByAnimalId(animalId, pageable)
                        .stream()
                        .map(AcaoPreventivaResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoPreventivaResponse> update(@PathVariable String id,
                                                         @Valid @RequestBody AcaoPreventivaRequest request) {
        Animal animal = animalService.fetchById(request.getIdAnimal())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));

        AcaoPreventiva acaoExistente = acaoPreventivaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Ação preventiva não encontrada com ID: " + id));

        AcaoPreventiva acaoAtualizada = AcaoPreventivaRequest.toEntity(request, animal);
        acaoAtualizada.setId(id);
        acaoAtualizada.setDataHoraRegistro(acaoExistente.getDataHoraRegistro());
        acaoAtualizada.setCategoria(acaoExistente.getCategoria());

        AcaoPreventiva saved = acaoPreventivaService.update(id, acaoAtualizada)
                .orElseThrow(() -> new RuntimeException("Erro ao atualizar ação preventiva"));

        return ResponseEntity.ok(AcaoPreventivaResponse.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (acaoPreventivaService.existsById(id)) {
            acaoPreventivaService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}