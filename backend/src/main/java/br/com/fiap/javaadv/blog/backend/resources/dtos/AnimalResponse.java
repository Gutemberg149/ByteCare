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

    // Converter de Entity para Response
    public static AnimalResponse toDto(Animal animal) {
        return AnimalResponse.builder()
                .id(animal.getId())
                .nome(animal.getNome())
                .tipo(animal.getTipo())
                .raca(animal.getRaca())
                .dataNascimento(animal.getDataNascimento())
                .observacaoGeral(animal.getObservacaoGeral())
                .ativo(animal.isAtivo())
                .build();
    }
}