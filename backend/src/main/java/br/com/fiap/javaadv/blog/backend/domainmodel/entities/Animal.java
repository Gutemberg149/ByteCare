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
@Getter @Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "RACA", length = 50)
    private String raca;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "OBSERVACAO_GERAL", length = 500)
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