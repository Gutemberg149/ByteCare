package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistroCuidadoRequest {

    @NotNull(message = "A categoria é obrigatória")
    private CategoriaCuidadoEnum categoria;

    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
    private String observacao;

    @NotBlank(message = "O ID do animal é obrigatório")
    private String idAnimal;

    // Converter de DTO para Entity
    public RegistroCuidado toEntity(Animal animal) {
        RegistroCuidado registro = new RegistroCuidado();
        registro.setCategoria(this.categoria);
        registro.setObservacao(this.observacao);
        registro.setAnimal(animal);
        return registro;
    }
}