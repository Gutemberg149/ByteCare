package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ATIVIDADE_BEM_ESTAR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AtividadeBemEstar {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ATIVIDADE", nullable = false, length = 150)
    private String atividade;

    @Column(name = "OBSERVACAO_ATIVIDADE", length = 500)
    private String observacaoAtividade;

    @Column(name = "DURACAO", length = 50)
    private String duracao;

    @Column(name = "OBSERVACAO", length = 500)
    private String observacao;

    @Column(name = "CATEGORIA", length = 100)
    private String categoria;

    @Column(name = "DATA_HORA_REGISTRO", nullable = false)
    private LocalDateTime dataHoraRegistro;

    
    @Column(name = "ANIMAL_ID", nullable = false)
    private UUID animalId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANIMAL_ID", insertable = false, updatable = false)
    private Animal animal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtividadeBemEstar that = (AtividadeBemEstar) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}