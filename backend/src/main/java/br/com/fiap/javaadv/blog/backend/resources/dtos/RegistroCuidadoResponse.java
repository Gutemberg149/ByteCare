package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistroCuidadoResponse {

    private String id;
    private String categoria;
    private LocalDateTime dataHoraRegistro;

    /**
     * O @JsonProperty garante que, independentemente do nome da variável
     * interna ser "observacao", o JSON de saída sempre terá a chave "observacao".
     */
    @JsonProperty("observacao")
    private String observacao;

    private String idAnimal;
    private String nomeAnimal;

    public static RegistroCuidadoResponse toDto(final RegistroCuidado entity) {
        if (entity == null) return null;

        return RegistroCuidadoResponse.builder()
                .id(entity.getId() != null ? entity.getId().toString() : null)
                .categoria(entity.getCategoria() != null ? entity.getCategoria().name() : null)
                .dataHoraRegistro(entity.getDataHoraRegistro())
                .observacao(entity.getDescricao()) // Pega o dado real do banco (campo 'descricao')
                .idAnimal(entity.getAnimal() != null ? entity.getAnimal().getId().toString() : null)
                .nomeAnimal(entity.getAnimal() != null ? entity.getAnimal().getNome() : null)
                .build();
    }
}