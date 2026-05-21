//package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Entity
//@Table(name = "BC_ANIMAL")
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//@ToString(exclude = "registros")
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class Animal {
//
//    @Id
//    @Column(name = "ID_ANIMAL", length = 36)
//    @EqualsAndHashCode.Include
//    private String id;
//
//    @NotBlank(message = "O nome do animal é obrigatório")
//    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
//    @Column(name = "NOME_ANIMAL", length = 100, nullable = false)
//    private String nome;
//
//    @NotNull(message = "O tipo do animal é obrigatório")
//    @Enumerated(EnumType.STRING)
//    @Column(name = "TIPO_ANIMAL", nullable = false)
//    private TipoAnimalEnum tipo;
//
//    @Size(max = 100, message = "A raça deve ter no máximo 100 caracteres")
//    @Column(name = "RACA_ANIMAL", length = 100)
//    private String raca;
//
//    @Column(name = "DATA_NASC_ANIMAL")
//    private LocalDate dataNascimento;
//
//    @Size(max = 200, message = "A observação deve ter no máximo 200 caracteres")
//    @Column(name = "OBS_ANIMAL", length = 200)
//    private String observacaoGeral;
//
//    @Column(name = "ATIVO_ANIMAL", nullable = false)
//    private boolean ativo = true;
//
//    @JsonManagedReference
//    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    @Builder.Default
//    private List<RegistroCuidado> registros = new ArrayList<>();
//
//    @PrePersist
//    public void gerarId() {
//        if (id == null) {
//            id = UUID.randomUUID().toString();
//        }
//    }
//}

//package br.com.fiap.javaadv.blog.backend.domainmodel.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.Objects;
//import java.util.UUID;
//
//@Entity
//@Table(name = "ANIMAL")
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Builder
//public class Animal {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private @Getter @Setter UUID id;
//
//    @Column(name = "NOME", nullable = false, length = 100)
//    private @Getter @Setter String nome;
//
//    @Column(name = "TIPO", nullable = false, length = 50)
//    private @Getter @Setter String tipo;
//
//    @Column(name = "ATIVO", nullable = false)
//    private @Getter @Setter boolean ativo;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Animal animal = (Animal) o;
//        return Objects.equals(id, animal.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
//
//
//}

package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate; // Importante!
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ANIMAL")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter @Setter // Adicione aqui para todos os campos da classe
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "RACA", length = 50) // Adicionado
    private String raca;

    @Column(name = "DATA_NASCIMENTO") // Adicionado
    private LocalDate dataNascimento;

    @Column(name = "OBSERVACAO_GERAL", length = 500) // Adicionado
    private String observacaoGeral;

    @Column(name = "ATIVO", nullable = false)
    private boolean ativo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}