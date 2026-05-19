package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RegistroCuidadoListResponse {

    private String id;
    private String categoria;
    private LocalDateTime dataHoraRegistro;
    private String idAnimal;
    private String nomeAnimal;

    public static RegistroCuidadoListResponse toDto(final RegistroCuidado registroCuidado) {
        return RegistroCuidadoListResponse.builder()
                .id(registroCuidado.getId())
                .categoria(registroCuidado.getCategoria() != null ? registroCuidado.getCategoria().name() : null)
                .dataHoraRegistro(registroCuidado.getDataHoraRegistro())
                .idAnimal(registroCuidado.getAnimal() != null ? registroCuidado.getAnimal().getId() : null)
                .nomeAnimal(registroCuidado.getAnimal() != null ? registroCuidado.getAnimal().getNome() : null)
                .build();
    }

    public static List<RegistroCuidadoListResponse> toListDto(final List<RegistroCuidado> registros) {
        return registros.stream()
                .map(RegistroCuidadoListResponse::toDto)
                .collect(Collectors.toList());
    }
}