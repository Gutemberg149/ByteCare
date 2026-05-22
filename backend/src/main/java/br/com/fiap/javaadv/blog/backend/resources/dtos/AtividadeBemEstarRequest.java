package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtividadeBemEstarRequest {

    @NotBlank(message = "O ID do animal é obrigatório")
    private String idAnimal;

    @NotBlank(message = "O nome da atividade é obrigatório")
    @Size(min = 2, max = 150, message = "O nome da atividade deve ter entre 2 e 150 caracteres")
    private String nomeAtividade;

    @Size(max = 500, message = "A observação da atividade não pode exceder 500 caracteres")
    private String observacaoAtividade;

    private String duracao;

    @Size(max = 500, message = "A observação geral não pode exceder 500 caracteres")
    private String observacao;

    @NotNull(message = "A categoria é obrigatória")
    @NotBlank(message = "A categoria não pode estar vazia")
    private String categoria;

    public static AtividadeBemEstar toEntity(final AtividadeBemEstarRequest dto, final Animal animal) {
        return AtividadeBemEstar.builder()
                .atividade(dto.getNomeAtividade())
                .observacaoAtividade(dto.getObservacaoAtividade())
                .duracao(dto.getDuracao())
                .observacao(dto.getObservacao())
                .categoria(dto.getCategoria())
                .dataHoraRegistro(LocalDateTime.now())
                .animalId(animal.getId())
                .animal(animal)
                .build();
    }
}