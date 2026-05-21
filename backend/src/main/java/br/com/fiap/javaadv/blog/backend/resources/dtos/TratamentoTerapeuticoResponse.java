//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Setter
//public class TratamentoTerapeuticoResponse {
//
//    private String id;
//    private String nomeMedicamento;
//    private String dosagem;
//    private String frequencia;
//    private String duracaoTratamento;
//    private String observacao;
//    private String categoria;
//    private String idAnimal;
//    private String nomeAnimal;
//
//    // Converter de Entity para Response
//    public static TratamentoTerapeuticoResponse toDto(final TratamentoTerapeutico tratamentoTerapeutico) {
//        return TratamentoTerapeuticoResponse.builder()
//                .id(String.valueOf(tratamentoTerapeutico.getId()))
//                .nomeMedicamento(tratamentoTerapeutico.getNomeMedicamento())
//                .dosagem(tratamentoTerapeutico.getDosagem())
//                .frequencia(tratamentoTerapeutico.getFrequencia())
//                .duracaoTratamento(tratamentoTerapeutico.getDuracaoTratamento())
//                .observacao(tratamentoTerapeutico.getObservacao())
//                .categoria(tratamentoTerapeutico.getCategoria() != null ? tratamentoTerapeutico.getCategoria().name() : null)
//                .idAnimal(tratamentoTerapeutico.getAnimal() != null ? tratamentoTerapeutico.getAnimal().getId() : null)
//                .nomeAnimal(tratamentoTerapeutico.getAnimal() != null ? tratamentoTerapeutico.getAnimal().getNome() : null)
//                .build();
//    }
//}
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
                // Acessamos o animal, verificamos se não é nulo e pegamos o ID
                .idAnimal(entity.getAnimal() != null ? entity.getAnimal().getId().toString() : null)
                // O nomeAnimal será preenchido no Service, mas já deixamos o getter aqui:
                .nomeAnimal(entity.getAnimal() != null ? entity.getAnimal().getNome() : null)
                .build();
    }
}