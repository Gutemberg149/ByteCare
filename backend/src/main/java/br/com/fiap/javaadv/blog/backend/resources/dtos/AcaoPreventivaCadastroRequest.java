package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcaoPreventivaCadastroRequest {

    private @Getter @Setter
    @NotBlank(message = "O ID do animal é obrigatório")
    String idAnimal;

    private @Getter @Setter
    @NotBlank(message = "O nome do serviço preventivo é obrigatório")
    @Size(min = 2, max = 150, message = "O nome do serviço deve ter entre 2 e 150 caracteres")
    String nomeServico;

    private @Getter @Setter
    @NotBlank(message = "A descrição do serviço preventivo é obrigatória")
    @Size(min = 2, max = 500, message = "A descrição deve ter entre 2 e 500 caracteres")
    String descricao;

    private @Getter @Setter
    String proximoPrevisto;

    private @Getter @Setter
    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
    String observacao;

    // Converter de DTO para Entity
    public static AcaoPreventiva toEntity(final AcaoPreventivaCadastroRequest dto, final Animal animal) {
        return AcaoPreventiva.preventivoBuilder()
                .nomeServico(dto.getNomeServico())
                .descricao(dto.getDescricao())
                .proximoPrevisto(dto.getProximoPrevisto())
                .observacao(dto.getObservacao())
                .animal(animal)
                .build();
    }
}