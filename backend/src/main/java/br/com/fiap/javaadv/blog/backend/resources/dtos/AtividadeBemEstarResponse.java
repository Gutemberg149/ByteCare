////
////
////package br.com.fiap.javaadv.blog.backend.resources.dtos;
////
////import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
////import lombok.*;
////
////@Getter
////@Setter
////@AllArgsConstructor
////@NoArgsConstructor
////@Builder
////public class AtividadeBemEstarResponse {
////
////    private String id;
////    private String nomeAtividade;
////    private String observacaoAtividade;
////    private String duracao;
////    private String observacao;
////    private String categoria;
////    private String idAnimal;
////    private String nomeAnimal;
////
////    public static AtividadeBemEstarResponse toDto(final AtividadeBemEstar entity) {
////        if (entity == null) {
////            return null;
////        }
////
////        return AtividadeBemEstarResponse.builder()
////                .id(entity.getId() != null ? entity.getId().toString() : null)
////                .nomeAtividade(entity.getAtividade())
////                .observacaoAtividade(entity.getObservacaoAtividade())
////                .duracao(entity.getDuracao())
////                .observacao(entity.getObservacao())
////                .categoria(entity.getCategoria())
////                .idAnimal(entity.getAnimalId() != null ? entity.getAnimalId().toString() : null)
////                .nomeAnimal(entity.getAnimal() != null ? entity.getAnimal().getNome() : null)
////                .build();
////    }
////}
//
//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class AtividadeBemEstarResponse {
//
//    private UUID id;
//    private String atividade;
//    private String observacao;
//    private String categoria;
//    private LocalDateTime dataHoraRegistro;
//    private UUID animalId;
//
//    public static AtividadeBemEstarResponse fromEntity(AtividadeBemEstar entity) {
//        return AtividadeBemEstarResponse.builder()
//                .id(entity.getId())
//                .atividade(entity.getAtividade())
//                .observacao(entity.getObservacao())
//                .categoria(entity.getCategoria())
//                .dataHoraRegistro(entity.getDataHoraRegistro())
//                .animalId(entity.getAnimal() != null ? entity.getAnimal().getId() : null)
//                .build();
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeBemEstarResponse {

    private UUID id;
    private String atividade;
    private String observacao;
    private String categoria;
    private LocalDateTime dataHoraRegistro;
    private UUID animalId;

    public static AtividadeBemEstarResponse fromEntity(AtividadeBemEstar entity) {
        return AtividadeBemEstarResponse.builder()
                .id(entity.getId())
                .atividade(entity.getAtividade())
                .observacao(entity.getObservacao())
                .categoria(entity.getCategoria())
                .dataHoraRegistro(entity.getDataHoraRegistro())
                .animalId(entity.getAnimal() != null ? entity.getAnimal().getId() : null)
                .build();
    }
}