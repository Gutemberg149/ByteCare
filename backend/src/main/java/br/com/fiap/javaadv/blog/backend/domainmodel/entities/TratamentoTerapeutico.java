////package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
////
////
////import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
////import jakarta.persistence.*;
////import jakarta.validation.constraints.NotBlank;
////import jakarta.validation.constraints.Size;
////import lombok.*;
////
////@Entity
////@Table(name = "BC_TRATAMENTO_TERAPEUTICO")
////@AllArgsConstructor
////@NoArgsConstructor
////@Getter
////@Setter
////@ToString(callSuper = true)
////public class TratamentoTerapeutico extends RegistroCuidado {
////
////    @NotBlank(message = "O nome do medicamento é obrigatório")
////    @Size(min = 2, max = 150, message = "O nome do medicamento deve ter entre 2 e 150 caracteres")
////    @Column(name = "NOME_MEDICAMENTO", length = 150, nullable = false)
////    private String nomeMedicamento;
////
////    @NotBlank(message = "A dosagem é obrigatória")
////    @Size(min = 1, max = 100, message = "A dosagem deve ter entre 1 e 100 caracteres")
////    @Column(name = "DOSAGEM", length = 100, nullable = false)
////    private String dosagem;
////
////    @Size(max = 100, message = "A frequência deve ter no máximo 100 caracteres")
////    @Column(name = "FREQUENCIA", length = 100)
////    private String frequencia;
////
////    @Size(max = 50, message = "A duração deve ter no máximo 50 caracteres")
////    @Column(name = "DURACAO_TRATAMENTO", length = 50)
////    private String duracaoTratamento;
////
////    @Builder(builderMethodName = "terapeuticoBuilder")
////    public TratamentoTerapeutico(String nomeMedicamento, String dosagem, String frequencia,
////                                 String duracaoTratamento, String observacao, Animal animal) {
////        super();
////        setCategoria(CategoriaCuidadoEnum.TERAPEUTICO);
////        setObservacao(observacao);
////        setAnimal(animal);
////        this.nomeMedicamento = nomeMedicamento;
////        this.dosagem = dosagem;
////        this.frequencia = frequencia;
////        this.duracaoTratamento = duracaoTratamento;
////    }
////}
//package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@DiscriminatorValue("TERAPEUTICO") // Grava "TERAPEUTICO" na coluna TIPO_REGISTRO da tabela única
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString(callSuper = true)
//public class TratamentoTerapeutico extends RegistroCuidado {
//
//    @NotBlank(message = "O nome do medicamento é obrigatório")
//    @Size(min = 2, max = 150, message = "O nome do medicamento deve ter entre 2 e 150 caracteres")
//    @Column(name = "NOME_MEDICAMENTO", length = 150)
//    private String nomeMedicamento;
//
//    @NotBlank(message = "A dosagem é obrigatória")
//    @Size(min = 1, max = 100, message = "A dosagem deve ter entre 1 e 100 caracteres")
//    @Column(name = "DOSAGEM", length = 100)
//    private String dosagem;
//
//    @Size(max = 100, message = "A frequência deve ter no máximo 100 caracteres")
//    @Column(name = "FREQUENCIA", length = 100)
//    private String frequencia;
//
//    @Size(max = 50, message = "A duração deve ter no máximo 50 caracteres")
//    @Column(name = "DURACAO_TRATAMENTO", length = 50)
//    private String duracaoTratamento;
//
//    @Builder(builderMethodName = "terapeuticoBuilder")
//    public TratamentoTerapeutico(String id, LocalDateTime dataHoraRegistro, String observacao, Animal animal,
//                                 String nomeMedicamento, String dosagem, String frequencia, String duracaoTratamento) {
//        // Envia os campos herdados diretamente para inicializar a superclasse
//        super(id, CategoriaCuidadoEnum.TERAPEUTICO, dataHoraRegistro, observacao, animal);
//        this.nomeMedicamento = nomeMedicamento;
//        this.dosagem = dosagem;
//        this.frequencia = frequencia;
//        this.duracaoTratamento = duracaoTratamento;
//    }
//}

package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TRATAMENTO_TERAPEUTICO")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TratamentoTerapeutico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @Column(name = "MEDICAMENTO", nullable = false, length = 150)
    private @Getter @Setter String medicamento;

    @Column(name = "ANIMAL_ID", nullable = false)
    private @Getter @Setter UUID animalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TratamentoTerapeutico that = (TratamentoTerapeutico) o;
        return Objects.equals(id, that.id);
    }

    @Column(name = "DATA_HORA_REGISTRO", nullable = false)
    private @Getter @Setter java.time.LocalDateTime dataHoraRegistro;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}