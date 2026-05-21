package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import jakarta.validation.constraints.NotBlank;
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
    @Size(min = 2, max = 150)
    private String nomeAtividade;

    private String observacaoAtividade;
    private String duracao;
    private String observacao;
    private String categoria;

    /**
     * Converte o DTO para a Entidade.
     * Como a entidade espera uma String na categoria, passamos o valor direto.
     */
    public static AtividadeBemEstar toEntity(final AtividadeBemEstarRequest dto, final Animal animal) {
        return AtividadeBemEstar.builder()
                .atividade(dto.getNomeAtividade())
                .observacaoAtividade(dto.getObservacaoAtividade())
                .duracao(dto.getDuracao())
                .observacao(dto.getObservacao())
                .categoria(dto.getCategoria()) // Passando a String diretamente
                .dataHoraRegistro(LocalDateTime.now())
                .animalId(animal.getId())
                .animal(animal)
                .build();
    }
}