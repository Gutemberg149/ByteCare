//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class TratamentoTerapeuticoRequest {
//
//    private @Getter @Setter
//    @NotBlank(message = "O ID do animal é obrigatório")
//    String idAnimal;
//
//    private @Getter @Setter
//    @NotBlank(message = "O nome do medicamento é obrigatório")
//    @Size(min = 2, max = 150, message = "O nome do medicamento deve ter entre 2 e 150 caracteres")
//    String nomeMedicamento;
//
//    private @Getter @Setter
//    @NotBlank(message = "A dosagem é obrigatória")
//    @Size(min = 1, max = 100, message = "A dosagem deve ter entre 1 e 100 caracteres")
//    String dosagem;
//
//    private @Getter @Setter
//    @Size(max = 100, message = "A frequência deve ter no máximo 100 caracteres")
//    String frequencia;
//
//    private @Getter @Setter
//    @Size(max = 50, message = "A duração do tratamento deve ter no máximo 50 caracteres")
//    String duracaoTratamento;
//
//    private @Getter @Setter
//    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
//    String observacao;
//
//    // Converter de DTO para Entity
//    public static TratamentoTerapeutico toEntity(final TratamentoTerapeuticoRequest dto, final Animal animal) {
//        return TratamentoTerapeutico.terapeuticoBuilder()
//                .nomeMedicamento(dto.getNomeMedicamento())
//                .dosagem(dto.getDosagem())
//                .frequencia(dto.getFrequencia())
//                .duracaoTratamento(dto.getDuracaoTratamento())
//                .observacao(dto.getObservacao())
//                .animal(animal)
//                .build();
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TratamentoTerapeuticoRequest {

    private @Getter @Setter
    @NotBlank(message = "O ID do animal é obrigatório")
    String idAnimal;

    private @Getter @Setter
    @NotBlank(message = "O nome do medicamento é obrigatório")
    @Size(min = 2, max = 150, message = "O nome do medicamento deve ter entre 2 e 150 caracteres")
    String nomeMedicamento;

    private @Getter @Setter
    @NotBlank(message = "A dosagem é obrigatória")
    @Size(min = 1, max = 100, message = "A dosagem deve ter entre 1 e 100 caracteres")
    String dosagem;

    private @Getter @Setter
    @Size(max = 100, message = "A frequência deve ter no máximo 100 caracteres")
    String frequencia;

    private @Getter @Setter
    @Size(max = 50, message = "A duração do tratamento deve ter no máximo 50 caracteres")
    String duracaoTratamento;

    private @Getter @Setter
    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
    String observacao;

    // Converter de DTO para Entity
    public static TratamentoTerapeutico toEntity(final TratamentoTerapeuticoRequest dto, final Animal animal) {
        return TratamentoTerapeutico.builder() // Alterado de terapeuticoBuilder() para builder()
                .medicamento(dto.getNomeMedicamento()) // Mapeado para o atributo correto 'medicamento'
                .animalId(animal != null ? animal.getId() : null) // Vincula o UUID do id do animal diretamente
                .build();
    }
}