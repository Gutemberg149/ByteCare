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

    // Converter de Entity para Response
    public static TratamentoTerapeuticoResponse toDto(final TratamentoTerapeutico tratamentoTerapeutico) {
        return TratamentoTerapeuticoResponse.builder()
                .id(String.valueOf(tratamentoTerapeutico.getId()))
                .nomeMedicamento(tratamentoTerapeutico.getMedicamento()) // Corrigido para getMedicamento()
                .idAnimal(String.valueOf(tratamentoTerapeutico.getAnimalId())) // Corrigido para buscar o ID direto da entidade
                // Como a entidade foi simplificada, os campos abaixo não existem mais nela.
                // Mantemos os campos no DTO retornando null (ou valores padrão) para não quebrar o seu Frontend/Contrato da API.
                .dosagem(null)
                .frequencia(null)
                .duracaoTratamento(null)
                .observacao(null)
                .categoria(null)
                .nomeAnimal(null)
                .build();
    }
}