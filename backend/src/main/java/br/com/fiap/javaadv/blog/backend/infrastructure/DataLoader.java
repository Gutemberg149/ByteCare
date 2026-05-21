package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AnimalRepository animalRepository;
    private final AcaoPreventivaRepository acaoPreventivaRepository;
    private final AtividadeBemEstarRepository atividadeBemEstarRepository;
    private final TratamentoTerapeuticoRepository tratamentoTerapeuticoRepository;
    private final RegistroCuidadoRepository registroCuidadoRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (animalRepository.count() > 0) {
            System.out.println("=== Banco já populado. Carga inicial ignorada. ===");
            return;
        }

        // 1. Persistência de Animais
        Animal a1 = animalRepository.save(Animal.builder().nome("Rex").tipo(TipoAnimalEnum.CACHORRO.name()).ativo(true).build());
        Animal a2 = animalRepository.save(Animal.builder().nome("Mimi").tipo(TipoAnimalEnum.GATO.name()).ativo(true).build());
        Animal a3 = animalRepository.save(Animal.builder().nome("Thor").tipo(TipoAnimalEnum.CACHORRO.name()).ativo(true).build());
        Animal a4 = animalRepository.save(Animal.builder().nome("Luna").tipo(TipoAnimalEnum.GATO.name()).ativo(false).build());
        Animal a5 = animalRepository.save(Animal.builder().nome("Spike").tipo(TipoAnimalEnum.CACHORRO.name()).ativo(true).build());

        // 2. Persistência de Ações Preventivas
        saveAcao("Vacinação anual", "V10", "2025-05-20", "Aplicar no próximo semestre", a1);
        saveAcao("Vermifugação", "Oral", "2025-06-15", "Repetir a cada 3 meses", a2);
        saveAcao("Check-up", "Clínico", "2025-07-01", "Levar exames", a3);

        // 3. Persistência de Atividades de Bem-Estar
        saveAtividade("Caminhada diária", "Caminhada leve", "30 min", "Manter ritmo", "Físico", a1);
        saveAtividade("Brincadeiras", "Busca de bolinha", "15 min", "Evitar calor", "Social", a2);
        saveAtividade("Natação", "Hidroterapia", "20 min", "Secar bem", "Saúde", a3);
        saveAtividade("Massagem", "Relaxamento", "10 min", "Música calma", "Bem-estar", a5);

        // 4. Persistência de Tratamentos Terapêuticos
        saveTratamento("Amoxicilina", "500mg", "12/12h", "7 dias", "Tomar após refeição", "Antibiótico", a1);
        saveTratamento("Dipirona", "200mg", "8/8h", "5 dias", "Para febre", "Analgésico", a2);
        saveTratamento("Prediderm", "25mg", "24/24h", "10 dias", "Para alergias", "Anti-inflamatório", a3);
        saveTratamento("Omeprazol", "20mg", "24/24h", "14 dias", "Administrar em jejum", "Gastroprotetor", a5);

        // 5. Persistência de Registros
        saveRegistro("Banho completo", CategoriaCuidadoEnum.BEM_ESTAR, a1);
        saveRegistro("Corte de unhas", CategoriaCuidadoEnum.BEM_ESTAR, a2);
        saveRegistro("Consulta de rotina", CategoriaCuidadoEnum.PREVENTIVO, a3);

        System.out.println("=== Carga inicial concluída com sucesso! ===");
    }

    // Métodos Auxiliares
    private void saveAcao(String n, String d, String p, String o, Animal a) {
        acaoPreventivaRepository.save(AcaoPreventiva.preventivoBuilder()
                .nomeServico(n).descricao(d).proximoPrevisto(p).observacao(o).animal(a).dataHoraRegistro(LocalDateTime.now()).build());
    }

    private void saveAtividade(String n, String obs, String dur, String o, String cat, Animal a) {
        atividadeBemEstarRepository.save(AtividadeBemEstar.builder()
                .atividade(n)
                .observacaoAtividade(obs)
                .duracao(dur)
                .observacao(o)
                .categoria(cat)
                .dataHoraRegistro(LocalDateTime.now())
                .animal(a) // O Hibernate ignora isso no insert por causa do insertable=false
                .animalId(a.getId())
                .build());
    }

    private void saveTratamento(String m, String dos, String fre, String dur, String obs, String cat, Animal a) {
        tratamentoTerapeuticoRepository.save(TratamentoTerapeutico.builder()
                .medicamento(m).dosagem(dos).frequencia(fre).duracaoTratamento(dur).observacao(obs).categoria(cat).dataHoraRegistro(LocalDateTime.now()).animal(a).build());
    }

    private void saveRegistro(String desc, CategoriaCuidadoEnum cat, Animal a) {
        registroCuidadoRepository.save(RegistroCuidado.builder()
                .descricao(desc).categoria(cat).animal(a).dataHoraRegistro(LocalDateTime.now()).build());
    }
}