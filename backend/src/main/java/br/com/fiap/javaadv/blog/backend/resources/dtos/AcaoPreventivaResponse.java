package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcaoPreventivaResponse {

    private UUID id;
    private String nomeServico;
    private String descricao;
    private String proximoPrevisto;
    private String observacao;
    private String categoria;
    private LocalDateTime dataHoraRegistro;
    private UUID idAnimal;
    private String nomeAnimal;

    public static AcaoPreventivaResponse toDto(final AcaoPreventiva entity) {
        if (entity == null) return null;

        var animal = entity.getAnimal();

        return AcaoPreventivaResponse.builder()
                .id(entity.getId())
                .nomeServico(entity.getNomeServico())
                .descricao(entity.getDescricao())
                .proximoPrevisto(entity.getProximoPrevisto())
                .observacao(entity.getObservacao())
                .categoria(entity.getCategoria() != null ? entity.getCategoria().name() : null)
                .dataHoraRegistro(entity.getDataHoraRegistro())
                .idAnimal(animal != null ? animal.getId() : null)
                .nomeAnimal(animal != null ? animal.getNome() : null)
                .build();
    }
}