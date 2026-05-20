//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import lombok.*;
//
//import java.time.LocalDate;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//public class AnimalResponse {
//
//    private String id;
//    private String nome;
//    private TipoAnimalEnum tipo;
//    private String raca;
//    private LocalDate dataNascimento;
//    private String observacaoGeral;
//    private boolean ativo;
//
//
//    public static AnimalResponse toDto(Animal animal) {
//        return AnimalResponse.builder()
//                .id(String.valueOf(animal.getId()))
//                .nome(animal.getNome())
//                .tipo(TipoAnimalEnum.valueOf(animal.getTipo()))
//                .raca(animal.getRaca())
//                .dataNascimento(animal.getDataNascimento())
//                .observacaoGeral(animal.getObservacaoGeral())
//                .ativo(animal.isAtivo())
//                .build();
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnimalResponse {

    private String id;
    private String nome;
    private TipoAnimalEnum tipo;
    private String raca;
    private LocalDate dataNascimento;
    private String observacaoGeral;
    private boolean ativo;

    public static AnimalResponse toDto(Animal animal) {
        if (animal == null) {
            return null;
        }

        return AnimalResponse.builder()
                .id(animal.getId() != null ? String.valueOf(animal.getId()) : null)
                .nome(animal.getNome())
                // Converte a String vinda do banco para o TipoAnimalEnum mapeado no DTO
                .tipo(animal.getTipo() != null ? TipoAnimalEnum.valueOf(animal.getTipo().toUpperCase()) : null)
                // Esses campos não existem na sua entidade atual, então retornamos null para o JSON
                .raca(null)
                .dataNascimento(null)
                .observacaoGeral(null)
                .ativo(animal.isAtivo())
                .build();
    }
}