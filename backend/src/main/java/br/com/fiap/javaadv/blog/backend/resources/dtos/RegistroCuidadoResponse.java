//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Setter
//public class RegistroCuidadoResponse {
//
//    private String id;
//    private String categoria;
//    private LocalDateTime dataHoraRegistro;
//    private String observacao;
//    private String idAnimal;
//    private String nomeAnimal;
//
//    // Converter de Entity para Response
//    public static RegistroCuidadoResponse toDto(final RegistroCuidado registroCuidado) {
//        return RegistroCuidadoResponse.builder()
//                .id(String.valueOf(registroCuidado.getId()))
//                .categoria(registroCuidado.getCategoria() != null ? registroCuidado.getCategoria().name() : null)
//                .dataHoraRegistro(registroCuidado.getDataHoraRegistro())
//                .observacao(registroCuidado.getObservacao())
//                .idAnimal(registroCuidado.getAnimal() != null ? registroCuidado.getAnimal().getId() : null)
//                .nomeAnimal(registroCuidado.getAnimal() != null ? registroCuidado.getAnimal().getNome() : null)
//                .build();
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RegistroCuidadoResponse {

    private String id;
    private String categoria;
    private LocalDateTime dataHoraRegistro;
    private String observacao;
    private String idAnimal;
    private String nomeAnimal;

    public static RegistroCuidadoResponse toDto(final RegistroCuidado registroCuidado) {
        if (registroCuidado == null) {
            return null;
        }

        return RegistroCuidadoResponse.builder()
                .id(registroCuidado.getId() != null ? registroCuidado.getId().toString() : null)
                .categoria(registroCuidado.getCategoria() != null ? registroCuidado.getCategoria().name() : null)
                .dataHoraRegistro(registroCuidado.getDataHoraRegistro())
                .observacao(registroCuidado.getDescricao())
                .idAnimal(registroCuidado.getAnimalId() != null ? registroCuidado.getAnimalId().toString() : null)
                // O nomeAnimal continuará null aqui, pois a entidade RegistroCuidado não possui o objeto Animal completo.
                // Isso será resolvido no Service.
                .nomeAnimal(null)
                .build();
    }
}