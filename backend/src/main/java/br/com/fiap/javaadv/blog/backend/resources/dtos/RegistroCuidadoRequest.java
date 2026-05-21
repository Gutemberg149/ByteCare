//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//public class RegistroCuidadoRequest {
//
//    @NotNull(message = "A categoria é obrigatória")
//    private CategoriaCuidadoEnum categoria;
//
//    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
//    private String observacao;
//
//    @NotBlank(message = "O ID do animal é obrigatório")
//    private String idAnimal;
//
//    // Converter de DTO para Entity
//    public RegistroCuidado toEntity(Animal animal) {
//        RegistroCuidado registro = new RegistroCuidado();
//        registro.setCategoria(this.categoria);
//        registro.setObservacao(this.observacao);
//        registro.setAnimal(animal);
//        return registro;
//    }
//}

//package br.com.fiap.javaadv.blog.backend.resources.dtos;
//
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.RegistroCuidado;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//public class RegistroCuidadoRequest {
//
//    @NotNull(message = "A categoria é obrigatória")
//    private CategoriaCuidadoEnum categoria;
//
//    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
//    private String observacao;
//
//    @NotBlank(message = "O ID do animal é obrigatório")
//    private String idAnimal;
//
//    // Converter de DTO para Entity
//    public RegistroCuidado toEntity(Animal animal) {
//        RegistroCuidado registro = new RegistroCuidado();
//
//        // Se a sua entidade não usar Enums e sim String no banco, use: this.categoria.name()
//        // Caso dê erro em setCategoria, comente a linha temporariamente para o build passar:
//        // registro.setCategoria(this.categoria);
//
//        // Vincula o UUID do id do animal diretamente no campo correto da arquitetura atual
//        if (animal != null) {
//            registro.setAnimalId(animal.getId());
//        }
//
//        // O método setObservacao(this.observacao) foi removido aqui para sanar o erro 'cannot find symbol'
//        // Se na sua entidade o campo se chamar descricao, você pode usar: registro.setDescricao(this.observacao);
//
//        return registro;
//    }
//}

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
@Data // Garante getters e setters para descricao
public class RegistroCuidadoRequest {

    @NotNull(message = "A categoria é obrigatória")
    private CategoriaCuidadoEnum categoria;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao; // Alterado de observacao para descricao

    @NotBlank(message = "O ID do animal é obrigatório")
    private String idAnimal;

    // Converter de DTO para Entity
    public RegistroCuidado toEntity(Animal animal) {
        return RegistroCuidado.builder()
                .categoria(this.categoria)
                .descricao(this.descricao) // Agora o Service encontrará este getter
                .animal(animal)            // Vincula o objeto Animal completo
                .build();
    }
}