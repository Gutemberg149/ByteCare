

package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtividadeBemEstarResponse {

    private String id;
    private String nomeAtividade;
    private String observacaoAtividade;
    private String duracao;
    private String observacao;
    private String categoria;
    private String idAnimal;
    private String nomeAnimal;

    public static AtividadeBemEstarResponse toDto(final AtividadeBemEstar entity) {
        if (entity == null) {
            return null;
        }

        return AtividadeBemEstarResponse.builder()
                .id(entity.getId() != null ? entity.getId().toString() : null)
                .nomeAtividade(entity.getAtividade())
                .observacaoAtividade(entity.getObservacaoAtividade())
                .duracao(entity.getDuracao())
                .observacao(entity.getObservacao())
                .categoria(entity.getCategoria())
                .idAnimal(entity.getAnimalId() != null ? entity.getAnimalId().toString() : null)
                .nomeAnimal(entity.getAnimal() != null ? entity.getAnimal().getNome() : null)
                .build();
    }
}