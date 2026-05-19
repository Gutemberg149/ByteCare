package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AcaoPreventivaListResponse {

    private String id;
    private String nomeServico;
    private String descricao;
    private String proximoPrevisto;
    private String idAnimal;
    private String nomeAnimal;

    public static AcaoPreventivaListResponse toDto(final AcaoPreventiva acaoPreventiva) {
        return AcaoPreventivaListResponse.builder()
                .id(acaoPreventiva.getId())
                .nomeServico(acaoPreventiva.getNomeServico())
                .descricao(acaoPreventiva.getDescricao())
                .proximoPrevisto(acaoPreventiva.getProximoPrevisto())
                .idAnimal(acaoPreventiva.getAnimal() != null ? acaoPreventiva.getAnimal().getId() : null)
                .nomeAnimal(acaoPreventiva.getAnimal() != null ? acaoPreventiva.getAnimal().getNome() : null)
                .build();
    }

    public static List<AcaoPreventivaListResponse> toListDto(final List<AcaoPreventiva> acoes) {
        return acoes.stream()
                .map(AcaoPreventivaListResponse::toDto)
                .collect(Collectors.toList());
    }
}