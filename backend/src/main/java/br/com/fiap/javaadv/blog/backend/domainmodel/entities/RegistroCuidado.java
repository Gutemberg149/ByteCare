////package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
////
////
////import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
////import com.fasterxml.jackson.annotation.JsonBackReference;
////import jakarta.persistence.*;
////import jakarta.validation.constraints.NotNull;
////import lombok.*;
////
////import java.time.LocalDateTime;
////import java.util.Objects;
////import java.util.UUID;
////
////@Entity
////@Table(name = "BC_REGISTRO_CUIDADO")
////@Inheritance(strategy = InheritanceType.JOINED)
////@AllArgsConstructor
////@NoArgsConstructor
////@Builder
////@ToString(exclude = "animal")
////public class RegistroCuidado {
////
////    @Id
////    @Column(name = "ID_REGISTRO", length = 36)
////    private @Getter @Setter String id;
////
////    @NotNull(message = "A categoria é obrigatória")
////    @Enumerated(EnumType.STRING)
////    @Column(name = "CATEGORIA_REGISTRO", nullable = false)
////    private @Getter @Setter CategoriaCuidadoEnum categoria;
////
////    @Column(name = "DT_REGISTRO", nullable = false)
////    private @Getter @Setter LocalDateTime dataHoraRegistro;
////
////    @Column(name = "OBS_REGISTRO", length = 500)
////    private @Getter @Setter String observacao;
////
////    @JsonBackReference
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "ID_ANIMAL_FK", nullable = false)
////    private @Getter @Setter Animal animal;
////
////    @PrePersist
////    public void prePersist() {
////        if (id == null) {
////            id = UUID.randomUUID().toString();
////        }
////        if (dataHoraRegistro == null) {
////            dataHoraRegistro = LocalDateTime.now();
////        }
////    }
////
////    @Override
////    public boolean equals(Object o) {
////        if (o == null || getClass() != o.getClass()) return false;
////        RegistroCuidado that = (RegistroCuidado) o;
////        return Objects.equals(id, that.id);
////    }
////
////    @Override
////    public int hashCode() {
////        return Objects.hashCode(id);
////    }
////}
//package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.*;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//import java.util.UUID;
//
//@Entity
//@Table(name = "BC_REGISTRO_CUIDADO")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // <-- Mude de JOINED para SINGLE_TABLE aqui
//@DiscriminatorColumn(name = "TIPO_REGISTRO", discriminatorType = DiscriminatorType.STRING) // <-- Define a coluna que o Hibernate usará para saber qual é a classe filha
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@ToString(exclude = "animal")
//public class RegistroCuidado {
//
//    @Id
//    @Column(name = "ID_REGISTRO", length = 36)
//    private @Getter @Setter String id;
//
//    // Você já tem esse Enum (que é ótimo), mas para herança SINGLE_TABLE,
//    // o Hibernate gerencia o tipo usando a DiscriminatorColumn acima.
//    // Mantivemos o seu Enum aqui caso você o use para regras de negócio!
//    @NotNull(message = "A categoria é obrigatória")
//    @Enumerated(EnumType.STRING)
//    @Column(name = "CATEGORIA_REGISTRO", nullable = false)
//    private @Getter @Setter CategoriaCuidadoEnum categoria;
//
//    @Column(name = "DT_REGISTRO", nullable = false)
//    private @Getter @Setter LocalDateTime dataHoraRegistro;
//
//    @Column(name = "OBS_REGISTRO", length = 500)
//    private @Getter @Setter String observacao;
//
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_ANIMAL_FK", nullable = false)
//    private @Getter @Setter Animal animal;
//
//    @PrePersist
//    public void prePersist() {
//        if (id == null) {
//            id = UUID.randomUUID().toString();
//        }
//        if (dataHoraRegistro == null) {
//            dataHoraRegistro = LocalDateTime.now();
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        RegistroCuidado that = (RegistroCuidado) o;
//        return Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
//}

//package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import jakarta.persistence.*;
//import lombok.*;
//import java.time.LocalDateTime; // Import necessário
//import java.util.Objects;
//import java.util.UUID;
//
//@Entity
//@Table(name = "REGISTRO_CUIDADO")
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Builder
//@Getter // Adicionado para toda a classe
//@Setter // Adicionado para toda a classe
//public class RegistroCuidado {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "CATEGORIA", nullable = false, length = 50)
//    private CategoriaCuidadoEnum categoria;
//
//    @Column(name = "ANIMAL_ID", nullable = false)
//    private UUID animalId;
//
//    // Adicionado os campos que estavam faltando
//    @Column(name = "DESCRICAO", length = 500)
//    private String descricao;
//
//    @Column(name = "DATA_HORA_REGISTRO")
//    private LocalDateTime dataHoraRegistro;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RegistroCuidado that = (RegistroCuidado) o;
//        return Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
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
@Table(name = "REGISTRO_CUIDADO")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class RegistroCuidado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA", nullable = false, length = 50)
    private CategoriaCuidadoEnum categoria;

    // Refatorado: Mapeamento de relacionamento real
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANIMAL_ID", nullable = false)
    private Animal animal;

    @Column(name = "DESCRICAO", length = 500)
    private String descricao;

    @Column(name = "DATA_HORA_REGISTRO", nullable = false)
    private LocalDateTime dataHoraRegistro;

    // Garante que a data seja preenchida automaticamente ao persistir
    @PrePersist
    protected void onCreate() {
        dataHoraRegistro = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistroCuidado that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}