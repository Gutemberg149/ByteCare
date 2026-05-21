package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TratamentoTerapeuticoRequest {

    private String idAnimal;
    private String medicamento;
    private String dosagem;
    private String frequencia;
    private String duracaoTratamento;
    private String observacao;
    private String categoria;

    /**
     * Converte o Request DTO para a Entidade JPA.
     * O Hibernate extrai automaticamente a chave estrangeira do objeto 'animal'.
     */
    public static TratamentoTerapeutico toEntity(TratamentoTerapeuticoRequest request, Animal animal) {
        return TratamentoTerapeutico.builder()
                .medicamento(request.getMedicamento())
                .dosagem(request.getDosagem())
                .frequencia(request.getFrequencia())
                .duracaoTratamento(request.getDuracaoTratamento())
                .observacao(request.getObservacao())
                .categoria(request.getCategoria())
                .animal(animal) // O Hibernate mapeia isso para ANIMAL_ID no banco
                .dataHoraRegistro(LocalDateTime.now())
                .build();
    }
}