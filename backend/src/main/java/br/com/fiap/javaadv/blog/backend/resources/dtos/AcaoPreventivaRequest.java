package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AcaoPreventivaRequest {

    @NotNull(message = "O ID do animal é obrigatório")
    private UUID idAnimal;

    @NotBlank(message = "O nome do serviço preventivo é obrigatório")
    @Size(min = 2, max = 150, message = "O nome do serviço deve ter entre 2 e 150 caracteres")
    private String nomeServico;

    @NotBlank(message = "A descrição do serviço preventivo é obrigatória")
    @Size(min = 2, max = 500, message = "A descrição deve ter entre 2 e 500 caracteres")
    private String descricao;

    private String proximoPrevisto;

    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
    private String observacao;

    // Adicionado para permitir o envio da categoria pelo Postman
    private String categoria;

    /**
     * Converte o DTO para a Entidade, garantindo que todos os campos
     * (inclusive Categoria e Data) sejam populados.
     */
    public static AcaoPreventiva toEntity(final AcaoPreventivaRequest dto, final Animal animal) {
        return AcaoPreventiva.preventivoBuilder()
                .nomeServico(dto.getNomeServico())
                .descricao(dto.getDescricao())
                .proximoPrevisto(dto.getProximoPrevisto())
                .observacao(dto.getObservacao())
                .animal(animal)
                // Converte a String do JSON para o Enum correspondente
                .categoria(dto.getCategoria() != null ? CategoriaCuidadoEnum.valueOf(dto.getCategoria().toUpperCase()) : null)
                // Define a data atual caso não tenha sido enviada
                .dataHoraRegistro(LocalDateTime.now())
                .build();
    }
}