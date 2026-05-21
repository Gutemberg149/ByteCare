package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TRATAMENTO_TERAPEUTICO")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class TratamentoTerapeutico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "MEDICAMENTO", nullable = false, length = 150)
    private String medicamento;

    @Column(name = "DOSAGEM")
    private String dosagem;

    @Column(name = "FREQUENCIA")
    private String frequencia;

    @Column(name = "DURACAO_TRATAMENTO")
    private String duracaoTratamento;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "CATEGORIA")
    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANIMAL_ID", nullable = false)
    private Animal animal;

    @Column(name = "DATA_HORA_REGISTRO", nullable = false)
    private LocalDateTime dataHoraRegistro;

    @PrePersist
    protected void onCreate() {
        if (dataHoraRegistro == null) {
            dataHoraRegistro = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TratamentoTerapeutico that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}