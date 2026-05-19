package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtividadeBemEstarRequest {

    private @Getter @Setter
    @NotBlank(message = "O ID do animal é obrigatório")
    String idAnimal;

    private @Getter @Setter
    @NotBlank(message = "O nome da atividade é obrigatório")
    @Size(min = 2, max = 150, message = "O nome da atividade deve ter entre 2 e 150 caracteres")
    String nomeAtividade;

    private @Getter @Setter
    @Size(max = 500, message = "A observação da atividade deve ter no máximo 500 caracteres")
    String observacaoAtividade;

    private @Getter @Setter
    @Size(max = 50, message = "A duração deve ter no máximo 50 caracteres")
    String duracao;

    private @Getter @Setter
    @Size(max = 500, message = "A observação geral deve ter no máximo 500 caracteres")
    String observacao;

    // Converter de DTO para Entity
    public static AtividadeBemEstar toEntity(final AtividadeBemEstarRequest dto, final Animal animal) {
        return AtividadeBemEstar.bemEstarBuilder()
                .nomeAtividade(dto.getNomeAtividade())
                .observacaoAtividade(dto.getObservacaoAtividade())
                .duracao(dto.getDuracao())
                .observacao(dto.getObservacao())
                .animal(animal)
                .build();
    }
}