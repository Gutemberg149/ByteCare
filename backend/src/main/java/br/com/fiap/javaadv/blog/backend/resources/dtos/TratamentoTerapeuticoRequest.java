package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TratamentoTerapeuticoRequest {

    @NotNull(message = "O ID do animal é obrigatório")
    private String idAnimal;

    @JsonProperty("nomeAnimal")
    private String nomeAnimal;

    @JsonProperty("nomeMedicamento")
    @NotBlank(message = "O nome do medicamento é obrigatório")
    private String medicamento;

    private String dosagem;
    private String frequencia;
    private String duracaoTratamento;
    private String observacao;
    private String categoria;


    public TratamentoTerapeutico toEntity(Animal animal) {
        return TratamentoTerapeutico.builder()
                .medicamento(this.medicamento)
                .dosagem(this.dosagem)
                .frequencia(this.frequencia)
                .duracaoTratamento(this.duracaoTratamento)
                .observacao(this.observacao)
                .categoria(this.categoria)
                .animal(animal)
                .dataHoraRegistro(LocalDateTime.now())
                .build();
    }
}