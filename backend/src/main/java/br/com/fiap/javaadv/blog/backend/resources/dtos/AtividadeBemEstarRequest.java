//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class AtividadeBemEstarRequest {
//
//    private @Getter @Setter
//    @NotBlank(message = "O ID do animal é obrigatório")
//    String idAnimal;
//
//    private @Getter @Setter
//    @NotBlank(message = "O nome da atividade é obrigatório")
//    @Size(min = 2, max = 150, message = "O nome da atividade deve ter entre 2 e 150 caracteres")
//    String nomeAtividade;
//
//    private @Getter @Setter
//    @Size(max = 500, message = "A observação da atividade deve ter no máximo 500 caracteres")
//    String observacaoAtividade;
//
//    private @Getter @Setter
//    @Size(max = 50, message = "A duração deve ter no máximo 50 caracteres")
//    String duracao;
//
//    private @Getter @Setter
//    @Size(max = 500, message = "A observação geral deve ter no máximo 500 caracteres")
//    String observacao;
//
//    // Converter de DTO para Entity
//    public static AtividadeBemEstar toEntity(final AtividadeBemEstarRequest dto, final Animal animal) {
//        return AtividadeBemEstar.bemEstarBuilder()
//                .nomeAtividade(dto.getNomeAtividade())
//                .observacaoAtividade(dto.getObservacaoAtividade())
//                .duracao(dto.getDuracao())
//                .observacao(dto.getObservacao())
//                .animal(animal)
//                .build();
//    }
//}

//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class AtividadeBemEstarRequest {
//
//    private @Getter @Setter
//    @NotBlank(message = "O ID do animal é obrigatório")
//    String idAnimal;
//
//    private @Getter @Setter
//    @NotBlank(message = "O nome da atividade é obrigatório")
//    @Size(min = 2, max = 150, message = "O nome da atividade deve ter entre 2 e 150 caracteres")
//    String nomeAtividade;
//
//    private @Getter @Setter
//    @Size(max = 500, message = "A observação da atividade deve ter no máximo 500 caracteres")
//    String observacaoAtividade;
//
//    private @Getter @Setter
//    @Size(max = 50, message = "A duração deve ter no máximo 50 caracteres")
//    String duracao;
//
//    private @Getter @Setter
//    @Size(max = 500, message = "A observação geral deve ter no máximo 500 caracteres")
//    String observacao;
//
//    // Converter de DTO para Entity
//    public static AtividadeBemEstar toEntity(final AtividadeBemEstarRequest dto, final Animal animal) {
//        return AtividadeBemEstar.builder() // Alterado de bemEstarBuilder() para builder()
//                .atividade(dto.getNomeAtividade()) // Mapeia o nome enviado para o atributo real 'atividade'
//                .animalId(animal != null ? animal.getId() : null) // Vincula o UUID do id do animal diretamente
//                .build();
//    }
//}

package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtividadeBemEstarRequest {

    @NotBlank(message = "O ID do animal é obrigatório")
    private String idAnimal;

    @NotBlank(message = "O nome da atividade é obrigatório")
    @Size(min = 2, max = 150, message = "O nome da atividade deve ter entre 2 e 150 caracteres")
    private String nomeAtividade;

    @Size(max = 500, message = "A observação da atividade deve ter no máximo 500 caracteres")
    private String observacaoAtividade;

    @Size(max = 50, message = "A duração deve ter no máximo 50 caracteres")
    private String duracao;

    @Size(max = 500, message = "A observação geral deve ter no máximo 500 caracteres")
    private String observacao;

    @Size(max = 100, message = "A categoria deve ter no máximo 100 caracteres")
    private String categoria;

    /**
     * Converte o DTO para a Entidade.
     * Note que agora todos os campos são mapeados para evitar campos 'null' no banco.
     */
    public static AtividadeBemEstar toEntity(final AtividadeBemEstarRequest dto, final Animal animal) {
        return AtividadeBemEstar.builder()
                .atividade(dto.getNomeAtividade())
                .observacaoAtividade(dto.getObservacaoAtividade())
                .duracao(dto.getDuracao())
                .observacao(dto.getObservacao())
                .categoria(dto.getCategoria())
                .dataHoraRegistro(LocalDateTime.now()) // Define o momento da criação
                .animalId(animal != null ? animal.getId() : null)
                .animal(animal) // Importante para o relacionamento JPA
                .build();
    }
}