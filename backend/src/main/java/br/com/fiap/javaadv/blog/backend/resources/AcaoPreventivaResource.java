//package br.com.fiap.javaadv.blog.backend.resources;
//
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
//import br.com.fiap.javaadv.blog.backend.services.AcaoPreventivaService;
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
//@RequestMapping("/api/acoes-preventivas")
//@RequiredArgsConstructor
//public class AcaoPreventivaResource {
//
//    private final AcaoPreventivaService acaoPreventivaService;
//    private final AnimalService animalService;
//
//    @PostMapping
//    public ResponseEntity<AcaoPreventivaResponse> create(@Valid @RequestBody AcaoPreventivaCadastroRequest request) {
//        Animal animal = animalService.fetchById(request.getIdAnimal())
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
//        AcaoPreventiva acao = AcaoPreventivaCadastroRequest.toEntity(request, animal);
//        AcaoPreventiva saved = acaoPreventivaService.create(acao);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saved.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).body(AcaoPreventivaResponse.toDto(saved));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<AcaoPreventivaResponse> update(@PathVariable String id,
//                                                         @Valid @RequestBody AcaoPreventivaAtualizacaoRequest request) {
//        AcaoPreventiva acaoExistente = acaoPreventivaService.findById(id)
//                .orElseThrow(() -> new RuntimeException("Ação preventiva não encontrada com ID: " + id));
//        AcaoPreventiva atualizado = AcaoPreventivaAtualizacaoRequest.toEntity(request, acaoExistente);
//        AcaoPreventiva saved = acaoPreventivaService.update(id, atualizado)
//                .orElseThrow(() -> new RuntimeException("Erro ao atualizar ação preventiva"));
//        return ResponseEntity.ok(AcaoPreventivaResponse.toDto(saved));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable String id) {
//        if (acaoPreventivaService.existsById(id)) {
//            acaoPreventivaService.delete(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @GetMapping("/listar")
//    public ResponseEntity<List<AcaoPreventivaListResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                acaoPreventivaService.findAll(pageable)
//                        .stream()
//                        .map(AcaoPreventivaListResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AcaoPreventivaResponse> fetchById(@PathVariable String id) {
//        return acaoPreventivaService.findById(id)
//                .map(AcaoPreventivaResponse::toDto)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/animal/{animalId}")
//    public ResponseEntity<List<AcaoPreventivaListResponse>> fetchByAnimal(@PathVariable String animalId,
//                                                                          @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                acaoPreventivaService.findByAnimalId(animalId, pageable)
//                        .stream()
//                        .map(AcaoPreventivaListResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/buscar")
//    public ResponseEntity<List<AcaoPreventivaListResponse>> buscarPorNome(
//            @RequestParam String animalId,
//            @RequestParam String nome,
//            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
//        return ResponseEntity.ok(
//                acaoPreventivaService.buscarPorNomeServico(animalId, nome, pageable)
//                        .stream()
//                        .map(AcaoPreventivaListResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//}
//package br.com.fiap.javaadv.blog.backend.resources;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaRequest;
//import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaResponse;
//import br.com.fiap.javaadv.blog.backend.services.AcaoPreventivaService;
//import br.com.fiap.javaadv.blog.backend.services.AnimalService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
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
//@RequestMapping("/api/acoes-preventivas")
//@RequiredArgsConstructor
//public class AcaoPreventivaResource {
//
//    private final AcaoPreventivaService acaoPreventivaService;
//    private final AnimalService animalService;
//
//    @PostMapping
//    public ResponseEntity<AcaoPreventivaResponse> create(@Valid @RequestBody AcaoPreventivaRequest request) {
//        Animal animal = animalService.fetchById(request.getIdAnimal())
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
//        AcaoPreventiva acao = AcaoPreventivaRequest.toEntity(request, animal);
//        AcaoPreventiva saved = acaoPreventivaService.create(acao);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saved.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).body(AcaoPreventivaResponse.toDto(saved));
//    }
//
//    // Corrigido: Agora retorna a lista pura do service sem paginação
//    @GetMapping
//    public ResponseEntity<List<AcaoPreventivaResponse>> findAll() {
//        return ResponseEntity.ok(
//                acaoPreventivaService.findAll()
//                        .stream()
//                        .map(AcaoPreventivaResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AcaoPreventivaResponse> findById(@PathVariable UUID id) {
//        return acaoPreventivaService.findById(id)
//                .map(AcaoPreventivaResponse::toDto)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // Corrigido: Chamando o método correto (findAllByAnimalId) e sem parâmetros de página
//    @GetMapping("/animal/{animalId}")
//    public ResponseEntity<List<AcaoPreventivaResponse>> findByAnimal(@PathVariable UUID animalId) {
//        return ResponseEntity.ok(
//                acaoPreventivaService.findAllByAnimalId(animalId)
//                        .stream()
//                        .map(AcaoPreventivaResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    // Opcional/Bônus: Endpoint exposto caso seu app mobile precise usar a barra de pesquisa por nome
//    @GetMapping("/animal/{animalId}/buscar")
//    public ResponseEntity<List<AcaoPreventivaResponse>> buscarPorNomeServico(
//            @PathVariable UUID animalId,
//            @RequestParam String nome) {
//        return ResponseEntity.ok(
//                acaoPreventivaService.buscarPorNomeServico(animalId, nome)
//                        .stream()
//                        .map(AcaoPreventivaResponse::toDto)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<AcaoPreventivaResponse> update(
//            @PathVariable UUID id,
//            @Valid @RequestBody AcaoPreventivaRequest request) {
//
//        Animal animal = animalService.fetchById(request.getIdAnimal())
//                .orElseThrow(() -> new RuntimeException("Animal não encontrado com ID: " + request.getIdAnimal()));
//
//        AcaoPreventiva acaoExistente = acaoPreventivaService.findById(id)
//                .orElseThrow(() -> new RuntimeException("Ação preventiva não encontrada com ID: " + id));
//
//        AcaoPreventiva acaoAtualizada = AcaoPreventivaRequest.toEntity(request, animal);
//
//        acaoAtualizada.setId(id);
//        acaoAtualizada.setDataHoraRegistro(acaoExistente.getDataHoraRegistro());
//        acaoAtualizada.setCategoria(acaoExistente.getCategoria());
//
//        AcaoPreventiva saved = acaoPreventivaService.update(id, acaoAtualizada)
//                .orElseThrow(() -> new RuntimeException("Erro ao atualizar ação preventiva"));
//
//        return ResponseEntity.ok(AcaoPreventivaResponse.toDto(saved));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable UUID id) {
//        if (acaoPreventivaService.existsById(id)) {
//            acaoPreventivaService.delete(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AcaoPreventivaResponse;
import br.com.fiap.javaadv.blog.backend.services.AcaoPreventivaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/acoes-preventivas")
@RequiredArgsConstructor
public class AcaoPreventivaResource {

    private final AcaoPreventivaService service;

    @PostMapping
    public ResponseEntity<AcaoPreventivaResponse> create(@Valid @RequestBody AcaoPreventivaRequest request) {
        AcaoPreventivaResponse response = service.create(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AcaoPreventivaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoPreventivaResponse> findById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<AcaoPreventivaResponse>> findByAnimal(@PathVariable UUID animalId) {
        return ResponseEntity.ok(service.findAllByAnimalId(animalId));
    }

    @GetMapping("/animal/{animalId}/buscar")
    public ResponseEntity<List<AcaoPreventivaResponse>> buscarPorNome(
            @PathVariable UUID animalId,
            @RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNomeServico(animalId, nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoPreventivaResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody AcaoPreventivaRequest request) {
        return service.update(id, request)
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