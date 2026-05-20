//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Setter
//public class AtividadeBemEstarResponse {
//
//    private String id;
//    private String nomeAtividade;
//    private String observacaoAtividade;
//    private String duracao;
//    private String observacao;
//    private String categoria;
//    private String idAnimal;
//    private String nomeAnimal;
//
//    // Converter de Entity para Response
//    public static AtividadeBemEstarResponse toDto(final AtividadeBemEstar atividadeBemEstar) {
//        return AtividadeBemEstarResponse.builder()
//                .id(String.valueOf(atividadeBemEstar.getId()))
//                .nomeAtividade(atividadeBemEstar.getNomeAtividade())
//                .observacaoAtividade(atividadeBemEstar.getObservacaoAtividade())
//                .duracao(atividadeBemEstar.getDuracao())
//                .observacao(atividadeBemEstar.getObservacao())
//                .categoria(atividadeBemEstar.getCategoria().name())
//                .idAnimal(atividadeBemEstar.getAnimal() != null ? atividadeBemEstar.getAnimal().getId() : null)
//                .nomeAnimal(atividadeBemEstar.getAnimal() != null ? atividadeBemEstar.getAnimal().getNome() : null)
//                .build();
//    }
//}

//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Setter
//public class AtividadeBemEstarResponse {
//
//    private String id;
//    private String nomeAtividade;
//    private String observacaoAtividade;
//    private String duracao;
//    private String observacao;
//    private String categoria;
//    private String idAnimal;
//    private String nomeAnimal;
//
//    // Converter de Entity para Response
//    public static AtividadeBemEstarResponse toDto(final AtividadeBemEstar atividadeBemEstar) {
//        if (atividadeBemEstar == null) {
//            return null;
//        }
//
//        return AtividadeBemEstarResponse.builder()
//                .id(atividadeBemEstar.getId() != null ? String.valueOf(atividadeBemEstar.getId()) : null)
//                .nomeAtividade(atividadeBemEstar.getAtividade()) // Mapeado de getNomeAtividade() para getAtividade()
//                .idAnimal(atividadeBemEstar.getAnimalId() != null ? String.valueOf(atividadeBemEstar.getAnimalId()) : null) // Mapeado para o UUID do animalId
//                // Como esses campos não existem na sua entidade atual, retornamos null para o DTO
//                // Isso evita quebrar o JSON esperado pelo seu frontend
//                .observacaoAtividade(null)
//                .duracao(null)
//                .observacao(null)
//                .categoria(null)
//                .nomeAnimal(null)
//                .build();
//    }
//}

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

    /**
     * Converte de Entity para Response.
     * Agora busca os valores reais da entidade, garantindo que o JSON não venha com campos nulos.
     */
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
                // Busca o nome através da relação @ManyToOne (se o objeto animal estiver carregado)
                .nomeAnimal(entity.getAnimal() != null ? entity.getAnimal().getNome() : null)
                .build();
    }
}