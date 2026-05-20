//package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//@Entity
//@Table(name = "BC_ATIVIDADE_BEM_ESTAR")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString(callSuper = true)
//public class AtividadeBemEstar extends RegistroCuidado {
//
//    @NotBlank(message = "O nome da atividade é obrigatório")
//    @Size(min = 2, max = 150, message = "O nome da atividade deve ter entre 2 e 150 caracteres")
//    @Column(name = "NOME_ATIVIDADE", length = 150, nullable = false)
//    private String nomeAtividade;
//
//    @Size(max = 500, message = "A observação da atividade deve ter no máximo 500 caracteres")
//    @Column(name = "OBS_ATIVIDADE", length = 500)
//    private String observacaoAtividade;
//
//    @Size(max = 50, message = "A duração deve ter no máximo 50 caracteres")
//    @Column(name = "DURACAO_ATIVIDADE", length = 50)
//    private String duracao;
//
//    @Builder(builderMethodName = "bemEstarBuilder")
//    public AtividadeBemEstar(String nomeAtividade, String observacaoAtividade,
//                             String duracao, String observacao, Animal animal) {
//        super();
//        setCategoria(CategoriaCuidadoEnum.BEM_ESTAR);
//        setObservacao(observacao);
//        setAnimal(animal);
//        this.nomeAtividade = nomeAtividade;
//        this.observacaoAtividade = observacaoAtividade;
//        this.duracao = duracao;
//    }
//}

package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("BEM_ESTAR") // Define o identificador que vai na coluna TIPO_REGISTRO da mãe
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class AtividadeBemEstar extends RegistroCuidado {

    @NotBlank(message = "O nome da atividade é obrigatório")
    @Size(min = 2, max = 150, message = "O nome da atividade deve ter entre 2 e 150 caracteres")
    @Column(name = "NOME_ATIVIDADE", length = 150) // Removido o nullable=false para evitar travamento na tabela única
    private String nomeAtividade;

    @Size(max = 500, message = "A observação da atividade deve ter no máximo 500 caracteres")
    @Column(name = "OBS_ATIVIDADE", length = 500)
    private String observacaoAtividade;

    @Size(max = 50, message = "A duração deve ter no máximo 50 caracteres")
    @Column(name = "DURACAO_ATIVIDADE", length = 50)
    private String duracao;

    @Builder(builderMethodName = "bemEstarBuilder")
    public AtividadeBemEstar(String id, LocalDateTime dataHoraRegistro, String observacao, Animal animal,
                             String nomeAtividade, String observacaoAtividade, String duracao) {
        // Passa os atributos herdados diretamente para o construtor da classe mãe
        super(id, CategoriaCuidadoEnum.BEM_ESTAR, dataHoraRegistro, observacao, animal);
        this.nomeAtividade = nomeAtividade;
        this.observacaoAtividade = observacaoAtividade;
        this.duracao = duracao;
    }
}
