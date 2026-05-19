package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AcaoPreventivaResponse {

    private String id;
    private String nomeServico;
    private String descricao;
    private String proximoPrevisto;
    private String observacao;
    private String categoria;
    private LocalDateTime dataHoraRegistro;
    private String idAnimal;
    private String nomeAnimal;

    public static AcaoPreventivaResponse toDto(final AcaoPreventiva acaoPreventiva) {
        return AcaoPreventivaResponse.builder()
                .id(acaoPreventiva.getId())
                .nomeServico(acaoPreventiva.getNomeServico())
                .descricao(acaoPreventiva.getDescricao())
                .proximoPrevisto(acaoPreventiva.getProximoPrevisto())
                .observacao(acaoPreventiva.getObservacao())
                .categoria(acaoPreventiva.getCategoria() != null ? acaoPreventiva.getCategoria().name() : null)
                .dataHoraRegistro(acaoPreventiva.getDataHoraRegistro())
                .idAnimal(acaoPreventiva.getAnimal() != null ? acaoPreventiva.getAnimal().getId() : null)
                .nomeAnimal(acaoPreventiva.getAnimal() != null ? acaoPreventiva.getAnimal().getNome() : null)
                .build();
    }
}