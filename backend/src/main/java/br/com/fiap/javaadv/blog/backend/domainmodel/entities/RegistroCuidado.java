package br.com.fiap.javaadv.blog.backend.domainmodel.entities;


import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "BC_REGISTRO_CUIDADO")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "animal")
public class RegistroCuidado {

    @Id
    @Column(name = "ID_REGISTRO", length = 36)
    private @Getter @Setter String id;

    @NotNull(message = "A categoria é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA_REGISTRO", nullable = false)
    private @Getter @Setter CategoriaCuidadoEnum categoria;

    @Column(name = "DT_REGISTRO", nullable = false)
    private @Getter @Setter LocalDateTime dataHoraRegistro;

    @Column(name = "OBS_REGISTRO", length = 500)
    private @Getter @Setter String observacao;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANIMAL_FK", nullable = false)
    private @Getter @Setter Animal animal;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        if (dataHoraRegistro == null) {
            dataHoraRegistro = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RegistroCuidado that = (RegistroCuidado) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
