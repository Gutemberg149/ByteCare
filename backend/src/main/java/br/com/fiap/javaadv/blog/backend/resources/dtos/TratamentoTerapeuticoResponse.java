package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TratamentoTerapeuticoResponse {

    private String id;
    private String nomeMedicamento;
    private String dosagem;
    private String frequencia;
    private String duracaoTratamento;
    private String observacao;
    private String categoria;
    private String idAnimal;
    private String nomeAnimal;

    public static TratamentoTerapeuticoResponse toDto(final TratamentoTerapeutico entity) {
        if (entity == null) return null;

        return TratamentoTerapeuticoResponse.builder()
                .id(entity.getId() != null ? entity.getId().toString() : null)
                .nomeMedicamento(entity.getMedicamento())
                .dosagem(entity.getDosagem())
                .frequencia(entity.getFrequencia())
                .duracaoTratamento(entity.getDuracaoTratamento())
                .observacao(entity.getObservacao())
                .categoria(entity.getCategoria())
                .idAnimal(entity.getAnimal() != null ? entity.getAnimal().getId().toString() : null)
                .nomeAnimal(entity.getAnimal() != null ? entity.getAnimal().getNome() : null)
                .build();
    }
}