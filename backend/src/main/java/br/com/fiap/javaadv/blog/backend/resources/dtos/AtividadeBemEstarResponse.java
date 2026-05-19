package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AtividadeBemEstarResponse {

    private String id;
    private String nomeAtividade;
    private String observacaoAtividade;
    private String duracao;
    private String observacao;
    private String categoria;
    private String idAnimal;
    private String nomeAnimal;

    // Converter de Entity para Response
    public static AtividadeBemEstarResponse toDto(final AtividadeBemEstar atividadeBemEstar) {
        return AtividadeBemEstarResponse.builder()
                .id(atividadeBemEstar.getId())
                .nomeAtividade(atividadeBemEstar.getNomeAtividade())
                .observacaoAtividade(atividadeBemEstar.getObservacaoAtividade())
                .duracao(atividadeBemEstar.getDuracao())
                .observacao(atividadeBemEstar.getObservacao())
                .categoria(atividadeBemEstar.getCategoria().name())
                .idAnimal(atividadeBemEstar.getAnimal() != null ? atividadeBemEstar.getAnimal().getId() : null)
                .nomeAnimal(atividadeBemEstar.getAnimal() != null ? atividadeBemEstar.getAnimal().getNome() : null)
                .build();
    }
}