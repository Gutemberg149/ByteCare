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


    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA")
    private @Getter @Setter CategoriaCuidadoEnum categoria;


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