package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
import lombok.*;

import java.time.LocalDateTime;

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

    // Converter de Entity para Response
    public static RegistroCuidadoResponse toDto(final RegistroCuidado registroCuidado) {
        return RegistroCuidadoResponse.builder()
                .id(registroCuidado.getId())
                .categoria(registroCuidado.getCategoria() != null ? registroCuidado.getCategoria().name() : null)
                .dataHoraRegistro(registroCuidado.getDataHoraRegistro())
                .observacao(registroCuidado.getObservacao())
                .idAnimal(registroCuidado.getAnimal() != null ? registroCuidado.getAnimal().getId() : null)
                .nomeAnimal(registroCuidado.getAnimal() != null ? registroCuidado.getAnimal().getNome() : null)
                .build();
    }
}