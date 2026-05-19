package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnimalRequest {

    @NotBlank(message = "O nome do animal é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotNull(message = "O tipo do animal é obrigatório")
    private TipoAnimalEnum tipo;

    @Size(max = 100, message = "A raça deve ter no máximo 100 caracteres")
    private String raca;

    private LocalDate dataNascimento;

    @Size(max = 200, message = "A observação deve ter no máximo 200 caracteres")
    private String observacaoGeral;

    // Converter de DTO para Entity
    public Animal toEntity() {
        return Animal.builder()
                .nome(this.nome)
                .tipo(this.tipo)
                .raca(this.raca)
                .dataNascimento(this.dataNascimento)
                .observacaoGeral(this.observacaoGeral)
                .ativo(true)
                .build();
    }

    // Converter de Entity para DTO
    public static AnimalRequest toDto(Animal animal) {
        return AnimalRequest.builder()
                .nome(animal.getNome())
                .tipo(animal.getTipo())
                .raca(animal.getRaca())
                .dataNascimento(animal.getDataNascimento())
                .observacaoGeral(animal.getObservacaoGeral())
                .build();
    }
}