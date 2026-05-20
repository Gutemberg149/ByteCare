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
//package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//@Entity
//@Table(name = "BC_ACAO_PREVENTIVA")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString(callSuper = true)
//public class AcaoPreventiva extends RegistroCuidado {
//
//    @NotBlank(message = "O nome do serviço preventivo é obrigatório")
//    @Size(min = 2, max = 150, message = "O nome do serviço deve ter entre 2 e 150 caracteres")
//    @Column(name = "NOME_SERVICO", length = 150, nullable = false)
//    private String nomeServico;
//
//    @NotBlank(message = "A descrição do serviço preventivo é obrigatória")
//    @Size(min = 2, max = 500, message = "A descrição deve ter entre 2 e 500 caracteres")
//    @Column(name = "DESCRICAO_SERVICO", length = 500, nullable = false)
//    private String descricao;
//
//    @Column(name = "PROXIMO_PREVISTO")
//    private String proximoPrevisto;
//
//    @Builder(builderMethodName = "preventivoBuilder")
//    public AcaoPreventiva(String nomeServico, String descricao, String proximoPrevisto,
//                          String observacao, Animal animal) {
//        super();
//        setCategoria(CategoriaCuidadoEnum.PREVENTIVO);
//        setObservacao(observacao);
//        setAnimal(animal);
//        this.nomeServico = nomeServico;
//        this.descricao = descricao;
//        this.proximoPrevisto = proximoPrevisto;
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
@DiscriminatorValue("PREVENTIVO") // Grava "PREVENTIVO" na coluna TIPO_REGISTRO da tabela única
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class AcaoPreventiva extends RegistroCuidado {

    @NotBlank(message = "O nome do serviço preventivo é obrigatório")
    @Size(min = 2, max = 150, message = "O nome do serviço deve ter entre 2 e 150 caracteres")
    @Column(name = "NOME_SERVICO", length = 150)
    private String nomeServico;

    @NotBlank(message = "A descrição do serviço preventivo é obrigatória")
    @Size(min = 2, max = 500, message = "A descrição deve ter entre 2 e 500 caracteres")
    @Column(name = "DESCRICAO_SERVICO", length = 500)
    private String descricao;

    @Column(name = "PROXIMO_PREVISTO")
    private String proximoPrevisto;

    @Builder(builderMethodName = "preventivoBuilder")
    public AcaoPreventiva(String id, LocalDateTime dataHoraRegistro, String observacao, Animal animal,
                          String nomeServico, String descricao, String proximoPrevisto) {
        // Envia as propriedades herdadas diretamente para o construtor da superclasse
        super(id, CategoriaCuidadoEnum.PREVENTIVO, dataHoraRegistro, observacao, animal);
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.proximoPrevisto = proximoPrevisto;
    }
}