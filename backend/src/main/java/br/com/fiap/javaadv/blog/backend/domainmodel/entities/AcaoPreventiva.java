//////package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
//////
//////import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//////import jakarta.persistence.*;
//////import jakarta.validation.constraints.NotBlank;
//////import jakarta.validation.constraints.Size;
//////import lombok.*;
//////
//////@Entity
//////@Table(name = "BC_ACAO_PREVENTIVA")
//////@AllArgsConstructor
//////@NoArgsConstructor
//////@Getter
//////@Setter
//////@ToString(callSuper = true)
//////public class AcaoPreventiva extends RegistroCuidado {
//////
//////    @NotBlank(message = "O nome do serviço preventivo é obrigatório")
//////    @Size(min = 2, max = 150, message = "O nome do serviço deve ter entre 2 e 150 caracteres")
//////    @Column(name = "NOME_SERVICO", length = 150, nullable = false)
//////    private String nomeServico;
//////
//////    @NotBlank(message = "A descrição do serviço preventivo é obrigatória")
//////    @Size(min = 2, max = 500, message = "A descrição deve ter entre 2 e 500 caracteres")
//////    @Column(name = "DESCRICAO_SERVICO", length = 500, nullable = false)
//////    private String descricao;
//////
//////    @Column(name = "PROXIMO_PREVISTO")
//////    private String proximoPrevisto;
//////
//////    @Builder(builderMethodName = "preventivoBuilder")
//////    public AcaoPreventiva(String nomeServico, String descricao, String proximoPrevisto,
//////                          String observacao, Animal animal) {
//////        super();
//////        setCategoria(CategoriaCuidadoEnum.PREVENTIVO);
//////        setObservacao(observacao);
//////        setAnimal(animal);
//////        this.nomeServico = nomeServico;
//////        this.descricao = descricao;
//////        this.proximoPrevisto = proximoPrevisto;
//////    }
//////}
////package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
////
////import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
////import jakarta.persistence.*;
////import jakarta.validation.constraints.NotBlank;
////import jakarta.validation.constraints.Size;
////import lombok.*;
////
////@Entity
////@Table(name = "BC_ACAO_PREVENTIVA")
////@AllArgsConstructor
////@NoArgsConstructor
////@Getter
////@Setter
////@ToString(callSuper = true)
////public class AcaoPreventiva extends RegistroCuidado {
////
////    @NotBlank(message = "O nome do serviço preventivo é obrigatório")
////    @Size(min = 2, max = 150, message = "O nome do serviço deve ter entre 2 e 150 caracteres")
////    @Column(name = "NOME_SERVICO", length = 150, nullable = false)
////    private String nomeServico;
////
////    @NotBlank(message = "A descrição do serviço preventivo é obrigatória")
////    @Size(min = 2, max = 500, message = "A descrição deve ter entre 2 e 500 caracteres")
////    @Column(name = "DESCRICAO_SERVICO", length = 500, nullable = false)
////    private String descricao;
////
////    @Column(name = "PROXIMO_PREVISTO")
////    private String proximoPrevisto;
////
////    @Builder(builderMethodName = "preventivoBuilder")
////    public AcaoPreventiva(String nomeServico, String descricao, String proximoPrevisto,
////                          String observacao, Animal animal) {
////        super();
////        setCategoria(CategoriaCuidadoEnum.PREVENTIVO);
////        setObservacao(observacao);
////        setAnimal(animal);
////        this.nomeServico = nomeServico;
////        this.descricao = descricao;
////        this.proximoPrevisto = proximoPrevisto;
////    }
////}
//
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
//@DiscriminatorValue("PREVENTIVO") // Grava "PREVENTIVO" na coluna TIPO_REGISTRO da tabela única
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString(callSuper = true)
//public class AcaoPreventiva extends RegistroCuidado {
//
//    @NotBlank(message = "O nome do serviço preventivo é obrigatório")
//    @Size(min = 2, max = 150, message = "O nome do serviço deve ter entre 2 e 150 caracteres")
//    @Column(name = "NOME_SERVICO", length = 150)
//    private String nomeServico;
//
//    @NotBlank(message = "A descrição do serviço preventivo é obrigatória")
//    @Size(min = 2, max = 500, message = "A descrição deve ter entre 2 e 500 caracteres")
//    @Column(name = "DESCRICAO_SERVICO", length = 500)
//    private String descricao;
//
//    @Column(name = "PROXIMO_PREVISTO")
//    private String proximoPrevisto;
//
//    @Builder(builderMethodName = "preventivoBuilder")
//    public AcaoPreventiva(String id, LocalDateTime dataHoraRegistro, String observacao, Animal animal,
//                          String nomeServico, String descricao, String proximoPrevisto) {
//        // Envia as propriedades herdadas diretamente para o construtor da superclasse
//        super(id, CategoriaCuidadoEnum.PREVENTIVO, dataHoraRegistro, observacao, animal);
//        this.nomeServico = nomeServico;
//        this.descricao = descricao;
//        this.proximoPrevisto = proximoPrevisto;
//    }
//}

package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ACAO_PREVENTIVA")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(builderMethodName = "preventivoBuilder")
public class AcaoPreventiva {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Getter @Setter UUID id;

    @Column(name = "NOME_SERVICO", nullable = false, length = 150)
    private @Getter @Setter String nomeServico;

    @Column(name = "DESCRICAO", nullable = false, length = 500)
    private @Getter @Setter String descricao;

    @Column(name = "PROXIMO_PREVISTO")
    private @Getter @Setter String proximoPrevisto;

    @Column(name = "OBSERVACAO", length = 500)
    private @Getter @Setter String observacao;

    // === ADICIONADO: Campo Categoria para resolver o getCategoria() ===
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA")
    private @Getter @Setter CategoriaCuidadoEnum categoria;

    // === ADICIONADO: Campo Data/Hora para resolver o getDataHoraRegistro() ===
    @Column(name = "DATA_HORA_REGISTRO")
    private @Getter @Setter LocalDateTime dataHoraRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANIMAL_ID", nullable = false)
    private @Getter @Setter Animal animal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcaoPreventiva that = (AcaoPreventiva) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}