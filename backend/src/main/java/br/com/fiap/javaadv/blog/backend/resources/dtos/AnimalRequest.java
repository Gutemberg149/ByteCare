//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//import java.time.LocalDate;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//public class AnimalRequest {
//
//    @NotBlank(message = "O nome do animal é obrigatório")
//    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
//    private String nome;
//
//    @NotNull(message = "O tipo do animal é obrigatório")
//    private TipoAnimalEnum tipo;
//
//    @Size(max = 100, message = "A raça deve ter no máximo 100 caracteres")
//    private String raca;
//
//    private LocalDate dataNascimento;
//
//    @Size(max = 200, message = "A observação deve ter no máximo 200 caracteres")
//    private String observacaoGeral;
//
//    // Converter de DTO para Entity
//    public Animal toEntity() {
//        return Animal.builder()
//                .nome(this.nome)
//                .tipo(String.valueOf(this.tipo))
//                .raca(this.raca)
//                .dataNascimento(this.dataNascimento)
//                .observacaoGeral(this.observacaoGeral)
//                .ativo(true)
//                .build();
//    }
//
//
//    public static AnimalRequest toDto(Animal animal) {
//        return AnimalRequest.builder()
//                .nome(animal.getNome())
//                .tipo(TipoAnimalEnum.valueOf(animal.getTipo()))
//                .raca(animal.getRaca())
//                .dataNascimento(animal.getDataNascimento())
//                .observacaoGeral(animal.getObservacaoGeral())
//                .build();
//    }
//}

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
                // Converte o Enum enviado pelo cliente em String para salvar no banco
                .tipo(this.tipo != null ? this.tipo.name() : null)
                .ativo(true) // Define ativo como true por padrão na criação
                // raca, dataNascimento e observacaoGeral não são passados aqui porque não existem na entidade Animal
                .build();
    }

    public static AnimalRequest toDto(Animal animal) {
        if (animal == null) {
            return null;
        }

        return AnimalRequest.builder()
                .nome(animal.getNome())
                // Converte a String vinda do banco de volta para o Enum do DTO de forma segura
                .tipo(animal.getTipo() != null ? TipoAnimalEnum.valueOf(animal.getTipo().toUpperCase()) : null)
                // Como não existem na entidade, esses campos do DTO ficam vazios ou nulos na resposta de conversão
                .raca(null)
                .dataNascimento(null)
                .observacaoGeral(null)
                .build();
    }
}