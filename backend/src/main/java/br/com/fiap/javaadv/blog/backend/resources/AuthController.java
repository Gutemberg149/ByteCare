package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.UsuarioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para autenticação e gerenciamento de usuários")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @Operation(summary = "Registrar novo usuário")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        Map<String, String> response = new HashMap<>();

        if (usuarioRepository.existsByUsername(request.getUsername())) {
            response.put("error", "Usuário já existe");
            return ResponseEntity.badRequest().body(response);
        }

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole().toUpperCase() : "USER")
                .ativo(true)
                .build();

        usuarioRepository.save(usuario);

        response.put("message", "Usuário registrado com sucesso");
        response.put("username", usuario.getUsername());
        response.put("role", usuario.getRole());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    @Operation(summary = "Obter informações do usuário autenticado")
    public ResponseEntity<Map<String, String>> getCurrentUser(@RequestParam String username) {
        Map<String, String> response = new HashMap<>();

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        response.put("username", usuario.getUsername());
        response.put("role", usuario.getRole());
        response.put("ativo", String.valueOf(usuario.isAtivo()));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/activate/{id}")
    @Operation(summary = "Ativar/desativar usuário")
    public ResponseEntity<Map<String, String>> toggleUserStatus(@PathVariable UUID id) {
        Map<String, String> response = new HashMap<>();

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setAtivo(!usuario.isAtivo());
        usuarioRepository.save(usuario);

        response.put("message", "Status do usuário atualizado");
        response.put("username", usuario.getUsername());
        response.put("ativo", String.valueOf(usuario.isAtivo()));

        return ResponseEntity.ok(response);
    }
}

