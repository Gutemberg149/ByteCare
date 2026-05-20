//package br.com.fiap.javaadv.blog.backend.infrastructure;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.TratamentoTerapeuticoRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Component
//@RequiredArgsConstructor
//public class DataLoader implements CommandLineRunner {
//
//    private final AnimalRepository animalRepository;
//    private final AcaoPreventivaRepository acaoPreventivaRepository;
//    private final AtividadeBemEstarRepository atividadeBemEstarRepository;
//    private final TratamentoTerapeuticoRepository tratamentoTerapeuticoRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // Proteção para evitar duplicação de dados ao usar ddl-auto: update
//        if (animalRepository.count() > 0) {
//            System.out.println("=== Banco já contem dados. Carga inicial pulada para evitar duplicidade. ===");
//            System.out.println("Animais cadastrados: " + animalRepository.count());
//            return;
//        }
//
//        // ================= ANIMAIS =================
//
//        Animal animal1 = Animal.builder()
//                .nome("Rex")
//                .tipo(TipoAnimalEnum.CACHORRO)
//                .raca("Labrador")
//                .dataNascimento(LocalDate.of(2020, 1, 15))
//                .observacaoGeral("Muito ativo e brincalhão")
//                .ativo(true)
//                .build();
//        animalRepository.save(animal1);
//
//        Animal animal2 = Animal.builder()
//                .nome("Mimi")
//                .tipo(TipoAnimalEnum.GATO)
//                .raca("Persa")
//                .dataNascimento(LocalDate.of(2019, 5, 20))
//                .observacaoGeral("Gosta de dormir e comer")
//                .ativo(true)
//                .build();
//        animalRepository.save(animal2);
//
//        Animal animal3 = Animal.builder()
//                .nome("Thor")
//                .tipo(TipoAnimalEnum.CACHORRO)
//                .raca("Golden Retriever")
//                .dataNascimento(LocalDate.of(2018, 10, 10))
//                .observacaoGeral("Muito amigável")
//                .ativo(true)
//                .build();
//        animalRepository.save(animal3);
//
//        Animal animal4 = Animal.builder()
//                .nome("Luna")
//                .tipo(TipoAnimalEnum.GATO)
//                .raca("Siamês")
//                .dataNascimento(LocalDate.of(2021, 3, 25))
//                .observacaoGeral("Muito preguiçosa")
//                .ativo(false)
//                .build();
//        animalRepository.save(animal4);
//
//        Animal animal5 = Animal.builder()
//                .nome("Spike")
//                .tipo(TipoAnimalEnum.CACHORRO)
//                .raca("Pug")
//                .dataNascimento(LocalDate.of(2022, 7, 8))
//                .observacaoGeral("Brincalhão e energético")
//                .ativo(true)
//                .build();
//        animalRepository.save(animal5);
//
//        // ================= AÇÕES PREVENTIVAS =================
//
//        AcaoPreventiva acao1 = AcaoPreventiva.preventivoBuilder()
//                .nomeServico("Vacinação anual")
//                .descricao("Vacina V10 contra doenças virais")
//                .proximoPrevisto("2025-05-20")
//                .observacao("Aplicar no próximo semestre")
//                .animal(animal1)
//                .build();
//        acaoPreventivaRepository.save(acao1);
//
//        AcaoPreventiva acao2 = AcaoPreventiva.preventivoBuilder()
//                .nomeServico("Vermifugação")
//                .descricao("Vermífugo oral para parasitas")
//                .proximoPrevisto("2025-06-15")
//                .observacao("Repetir a cada 3 meses")
//                .animal(animal2)
//                .build();
//        acaoPreventivaRepository.save(acao2);
//
//        AcaoPreventiva acao3 = AcaoPreventiva.preventivoBuilder()
//                .nomeServico("Check-up semestral")
//                .descricao("Exame clínico completo")
//                .proximoPrevisto("2025-07-01")
//                .observacao("Levar exames anteriores")
//                .animal(animal3)
//                .build();
//        acaoPreventivaRepository.save(acao3);
//
//        // ================= ATIVIDADES BEM-ESTAR =================
//
//        AtividadeBemEstar atividade1 = AtividadeBemEstar.bemEstarBuilder()
//                .nomeAtividade("Caminhada diária")
//                .observacaoAtividade("30 minutos de caminhada")
//                .duracao("30 min")
//                .observacao("Fazer sempre pela manhã")
//                .animal(animal1)
//                .build();
//        atividadeBemEstarRepository.save(atividade1);
//
//        AtividadeBemEstar atividade2 = AtividadeBemEstar.bemEstarBuilder()
//                .nomeAtividade("Sessão de brincadeiras")
//                .observacaoAtividade("Bolinha e corda")
//                .duracao("15 min")
//                .observacao("Estimular atividade física")
//                .animal(animal2)
//                .build();
//        atividadeBemEstarRepository.save(atividade2);
//
//        AtividadeBemEstar atividade3 = AtividadeBemEstar.bemEstarBuilder()
//                .nomeAtividade("Natação")
//                .observacaoAtividade("Exercício na piscina")
//                .duracao("20 min")
//                .observacao("Ótimo para articulações")
//                .animal(animal3)
//                .build();
//        atividadeBemEstarRepository.save(atividade3);
//
//        AtividadeBemEstar atividade4 = AtividadeBemEstar.bemEstarBuilder()
//                .nomeAtividade("Massagem relaxante")
//                .observacaoAtividade("Massagem terapêutica")
//                .duracao("10 min")
//                .observacao("Ajuda na circulação")
//                .animal(animal5)
//                .build();
//        atividadeBemEstarRepository.save(atividade4);
//
//        // ================= TRATAMENTOS TERAPÊUTICOS =================
//
//        TratamentoTerapeutico tratamento1 = TratamentoTerapeutico.terapeuticoBuilder()
//                .nomeMedicamento("Amoxicilina")
//                .dosagem("500mg")
//                .frequencia("12/12h")
//                .duracaoTratamento("7 dias")
//                .observacao("Tomar após refeição")
//                .animal(animal1)
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento1);
//
//        TratamentoTerapeutico tratamento2 = TratamentoTerapeutico.terapeuticoBuilder()
//                .nomeMedicamento("Dipirona")
//                .dosagem("200mg")
//                .frequencia("8/8h")
//                .duracaoTratamento("5 dias")
//                .observacao("Para febre")
//                .animal(animal2)
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento2);
//
//        TratamentoTerapeutico tratamento3 = TratamentoTerapeutico.terapeuticoBuilder()
//                .nomeMedicamento("Prediderm")
//                .dosagem("25mg")
//                .frequencia("24/24h")
//                .duracaoTratamento("10 dias")
//                .observacao("Para alergias")
//                .animal(animal3)
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento3);
//
//        TratamentoTerapeutico tratamento4 = TratamentoTerapeutico.terapeuticoBuilder()
//                .nomeMedicamento("Vermífugo")
//                .dosagem("500mg")
//                .frequencia("Dose única")
//                .duracaoTratamento("1 dia")
//                .observacao("Repetir em 15 dias")
//                .animal(animal4)
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento4);
//
//        TratamentoTerapeutico tratamento5 = TratamentoTerapeutico.terapeuticoBuilder()
//                .nomeMedicamento("Omeprazol")
//                .dosagem("20mg")
//                .frequencia("24/24h")
//                .duracaoTratamento("14 dias")
//                .observacao("Administrar em jejum")
//                .animal(animal5)
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento5);
//
//        System.out.println("=== Dados carregados com sucesso! ===");
//        System.out.println("Animais: " + animalRepository.count());
//        System.out.println("Ações Preventivas: " + acaoPreventivaRepository.count());
//        System.out.println("Atividades Bem-Estar: " + atividadeBemEstarRepository.count());
//        System.out.println("Tratamentos Terapêuticos: " + tratamentoTerapeuticoRepository.count());
//    }
//}
//package br.com.fiap.javaadv.blog.backend.infrastructure;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.TratamentoTerapeuticoRepository;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class DataLoader implements CommandLineRunner {
//
//    private final AnimalRepository animalRepository;
//    private final AcaoPreventivaRepository acaoPreventivaRepository;
//    private final AtividadeBemEstarRepository atividadeBemEstarRepository;
//    private final TratamentoTerapeuticoRepository tratamentoTerapeuticoRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // Proteção para evitar duplicação de dados ao usar ddl-auto: update
//        if (animalRepository.count() > 0) {
//            System.out.println("=== Banco já contem dados. Carga inicial pulada para evitar duplicidade. ===");
//            System.out.println("Animais cadastrados: " + animalRepository.count());
//            return;
//        }
//
//        // ================= ANIMAIS =================
//
//        Animal animal1 = Animal.builder()
//                .nome("Rex")
//                .tipo(TipoAnimalEnum.CACHORRO.name())
//                .ativo(true)
//                .build();
//        animal1 = animalRepository.save(animal1);
//
//        Animal animal2 = Animal.builder()
//                .nome("Mimi")
//                .tipo(TipoAnimalEnum.GATO.name())
//                .ativo(true)
//                .build();
//        animal2 = animalRepository.save(animal2);
//
//        Animal animal3 = Animal.builder()
//                .nome("Thor")
//                .tipo(TipoAnimalEnum.CACHORRO.name())
//                .ativo(true)
//                .build();
//        animal3 = animalRepository.save(animal3);
//
//        Animal animal4 = Animal.builder()
//                .nome("Luna")
//                .tipo(TipoAnimalEnum.GATO.name())
//                .ativo(false)
//                .build();
//        animal4 = animalRepository.save(animal4);
//
//        Animal animal5 = Animal.builder()
//                .nome("Spike")
//                .tipo(TipoAnimalEnum.CACHORRO.name())
//                .ativo(true)
//                .build();
//        animal5 = animalRepository.save(animal5);
//
//        // ================= AÇÕES PREVENTIVAS =================
//
//        AcaoPreventiva acao1 = new AcaoPreventiva();
//        acao1.setNomeServico("Vacinação anual");
//        acao1.setDescricao("Vacina V10 contra doenças virais");
//        acao1.setProximoPrevisto("2025-05-20");
//        acao1.setObservacao("Aplicar no próximo semestre");
//        acao1.setAnimal(animal1); // Passando o objeto animal completo
//        acaoPreventivaRepository.save(acao1);
//
//        AcaoPreventiva acao2 = new AcaoPreventiva();
//        acao2.setNomeServico("Vermifugação");
//        acao2.setDescricao("Vermífugo oral para parasitas");
//        acao2.setProximoPrevisto("2025-06-15");
//        acao2.setObservacao("Repetir a cada 3 meses");
//        acao2.setAnimal(animal2); // Passando o objeto animal completo
//        acaoPreventivaRepository.save(acao2);
//
//        AcaoPreventiva acao3 = new AcaoPreventiva();
//        acao3.setNomeServico("Check-up semestral");
//        acao3.setDescricao("Exame clínico completo");
//        acao3.setProximoPrevisto("2025-07-01");
//        acao3.setObservacao("Levar exames anteriores");
//        acao3.setAnimal(animal3); // Passando o objeto animal completo
//        acaoPreventivaRepository.save(acao3);
//
//        // ================= ATIVIDADES BEM-ESTAR =================
//
//        AtividadeBemEstar atividade1 = AtividadeBemEstar.builder()
//                .atividade("Caminhada diária - 30 min (Manhã)")
//                .animalId(animal1.getId())
//                .build();
//        atividadeBemEstarRepository.save(atividade1);
//
//        AtividadeBemEstar atividade2 = AtividadeBemEstar.builder()
//                .atividade("Sessão de brincadeiras - 15 min")
//                .animalId(animal2.getId())
//                .build();
//        atividadeBemEstarRepository.save(atividade2);
//
//        AtividadeBemEstar atividade3 = AtividadeBemEstar.builder()
//                .atividade("Natação - 20 min (Articulações)")
//                .animalId(animal3.getId())
//                .build();
//        atividadeBemEstarRepository.save(atividade3);
//
//        AtividadeBemEstar atividade4 = AtividadeBemEstar.builder()
//                .atividade("Massagem relaxante - 10 min")
//                .animalId(animal5.getId())
//                .build();
//        atividadeBemEstarRepository.save(atividade4);
//
//        // ================= TRATAMENTOS TERAPÊUTICOS =================
//        // Refatorado para corresponder exatamente à sua entidade simplificada atual
//
//        TratamentoTerapeutico tratamento1 = TratamentoTerapeutico.builder()
//                .medicamento("Amoxicilina")
//                .animalId(animal1.getId())
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento1);
//
//        TratamentoTerapeutico tratamento2 = TratamentoTerapeutico.builder()
//                .medicamento("Dipirona")
//                .animalId(animal2.getId())
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento2);
//
//        TratamentoTerapeutico tratamento3 = TratamentoTerapeutico.builder()
//                .medicamento("Prediderm")
//                .animalId(animal3.getId())
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento3);
//
//        TratamentoTerapeutico tratamento4 = TratamentoTerapeutico.builder()
//                .medicamento("Vermífugo")
//                .animalId(animal4.getId())
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento4);
//
//        TratamentoTerapeutico tratamento5 = TratamentoTerapeutico.builder()
//                .medicamento("Omeprazol")
//                .animalId(animal5.getId())
//                .build();
//        tratamentoTerapeuticoRepository.save(tratamento5);
//
//        System.out.println("=== Dados carregados com sucesso! ===");
//        System.out.println("Animais: " + animalRepository.count());
//        System.out.println("Ações Preventivas: " + acaoPreventivaRepository.count());
//        System.out.println("Atividades Bem-Estar: " + atividadeBemEstarRepository.count());
//        System.out.println("Tratamentos Terapêuticos: " + tratamentoTerapeuticoRepository.count());
//    }
//}

//package br.com.fiap.javaadv.blog.backend.infrastructure;
//
//import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
//import br.com.fiap.javaadv.blog.backend.domainmodel.entities.*;
//import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import java.time.LocalDateTime;
//
//@Component
//@RequiredArgsConstructor
//public class DataLoader implements CommandLineRunner {
//
//    private final AnimalRepository animalRepository;
//    private final AcaoPreventivaRepository acaoPreventivaRepository;
//    private final AtividadeBemEstarRepository atividadeBemEstarRepository;
//    private final TratamentoTerapeuticoRepository tratamentoTerapeuticoRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        if (animalRepository.count() > 0) return;
//
//        // ================= ANIMAIS =================
//        Animal a1 = animalRepository.save(Animal.builder().nome("Rex").tipo(TipoAnimalEnum.CACHORRO.name()).ativo(true).build());
//        Animal a2 = animalRepository.save(Animal.builder().nome("Mimi").tipo(TipoAnimalEnum.GATO.name()).ativo(true).build());
//        Animal a3 = animalRepository.save(Animal.builder().nome("Thor").tipo(TipoAnimalEnum.CACHORRO.name()).ativo(true).build());
//        Animal a4 = animalRepository.save(Animal.builder().nome("Luna").tipo(TipoAnimalEnum.GATO.name()).ativo(false).build());
//        Animal a5 = animalRepository.save(Animal.builder().nome("Spike").tipo(TipoAnimalEnum.CACHORRO.name()).ativo(true).build());
//
//        // ================= AÇÕES PREVENTIVAS =================
//        saveAcao("Vacinação anual", "V10", "2025-05-20", "Aplicar no próximo semestre", a1);
//        saveAcao("Vermifugação", "Oral", "2025-06-15", "Repetir a cada 3 meses", a2);
//        saveAcao("Check-up", "Clínico", "2025-07-01", "Levar exames", a3);
//
//        // ================= ATIVIDADES BEM-ESTAR =================
//        // ================= ATIVIDADES BEM-ESTAR =================
//// Parâmetros: nome, observacaoAtividade, duracao, observacao, categoria, animalId
//        saveAtividade("Caminhada diária", "Caminhada leve", "30 min", "Manter ritmo", "Físico", a1.getId());
//        saveAtividade("Brincadeiras", "Busca de bolinha", "15 min", "Evitar calor", "Social", a2.getId());
//        saveAtividade("Natação", "Hidroterapia", "20 min", "Secar bem", "Saúde", a3.getId());
//        saveAtividade("Massagem", "Relaxamento", "10 min", "Música calma", "Bem-estar", a5.getId());
//
//        // ================= TRATAMENTOS TERAPÊUTICOS =================
//        saveTratamento("Amoxicilina", a1.getId());
//        saveTratamento("Dipirona", a2.getId());
//        saveTratamento("Prediderm", a3.getId());
//        saveTratamento("Vermífugo", a4.getId());
//        saveTratamento("Omeprazol", a5.getId());
//
//        System.out.println("=== Carga inicial concluída com sucesso! ===");
//    }
//
//    private void saveAcao(String nome, String desc, String prox, String obs, Animal animal) {
//        // Correção: Usando o nome customizado do builder definido na entidade AcaoPreventiva
//        acaoPreventivaRepository.save(AcaoPreventiva.preventivoBuilder()
//                .nomeServico(nome)
//                .descricao(desc)
//                .proximoPrevisto(prox)
//                .observacao(obs)
//                .animal(animal)
//                .dataHoraRegistro(LocalDateTime.now())
//                .build());
//    }
//
//    private void saveAtividade(String nome, String obsAtiv, String duracao, String obs, String cat, java.util.UUID animalId) {
//        AtividadeBemEstar atv = AtividadeBemEstar.builder()
//                .atividade(nome)
//                .observacaoAtividade(obsAtiv)
//                .duracao(duracao)
//                .observacao(obs)
//                .categoria(cat)
//                .dataHoraRegistro(java.time.LocalDateTime.now())
//                .animalId(animalId)
//                .build();
//        atividadeBemEstarRepository.save(atv);
//    }
//
//    private void saveTratamento(String med, java.util.UUID animalId) {
//        tratamentoTerapeuticoRepository.save(TratamentoTerapeutico.builder()
//                .medicamento(med)
//                .animalId(animalId)
//                .dataHoraRegistro(java.time.LocalDateTime.now())
//                .build());
//    }
//}

package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AnimalRepository animalRepository;
    private final AcaoPreventivaRepository acaoPreventivaRepository;
    private final AtividadeBemEstarRepository atividadeBemEstarRepository;
    private final TratamentoTerapeuticoRepository tratamentoTerapeuticoRepository;
    private final RegistroCuidadoRepository registroCuidadoRepository; // Injeção adicionada

    @Override
    public void run(String... args) throws Exception {

        if (animalRepository.count() > 0) return;

        // ================= ANIMAIS =================
        Animal a1 = animalRepository.save(Animal.builder().nome("Rex").tipo(TipoAnimalEnum.CACHORRO.name()).ativo(true).build());
        Animal a2 = animalRepository.save(Animal.builder().nome("Mimi").tipo(TipoAnimalEnum.GATO.name()).ativo(true).build());
        Animal a3 = animalRepository.save(Animal.builder().nome("Thor").tipo(TipoAnimalEnum.CACHORRO.name()).ativo(true).build());
        Animal a4 = animalRepository.save(Animal.builder().nome("Luna").tipo(TipoAnimalEnum.GATO.name()).ativo(false).build());
        Animal a5 = animalRepository.save(Animal.builder().nome("Spike").tipo(TipoAnimalEnum.CACHORRO.name()).ativo(true).build());

        // ================= AÇÕES PREVENTIVAS =================
        saveAcao("Vacinação anual", "V10", "2025-05-20", "Aplicar no próximo semestre", a1);
        saveAcao("Vermifugação", "Oral", "2025-06-15", "Repetir a cada 3 meses", a2);
        saveAcao("Check-up", "Clínico", "2025-07-01", "Levar exames", a3);

        // ================= ATIVIDADES BEM-ESTAR =================
        saveAtividade("Caminhada diária", "Caminhada leve", "30 min", "Manter ritmo", "Físico", a1.getId());
        saveAtividade("Brincadeiras", "Busca de bolinha", "15 min", "Evitar calor", "Social", a2.getId());
        saveAtividade("Natação", "Hidroterapia", "20 min", "Secar bem", "Saúde", a3.getId());
        saveAtividade("Massagem", "Relaxamento", "10 min", "Música calma", "Bem-estar", a5.getId());

        // ================= TRATAMENTOS TERAPÊUTICOS =================
        saveTratamento("Amoxicilina", a1.getId());
        saveTratamento("Dipirona", a2.getId());
        saveTratamento("Prediderm", a3.getId());
        saveTratamento("Vermífugo", a4.getId());
        saveTratamento("Omeprazol", a5.getId());

        // ================= REGISTROS DE CUIDADO =================
        // ================= REGISTROS DE CUIDADO =================
// Use apenas valores que existem no Enum: PREVENTIVO, TERAPEUTICO ou BEM_ESTAR
        saveRegistro("Banho completo", CategoriaCuidadoEnum.BEM_ESTAR, a1.getId());
        saveRegistro("Corte de unhas", CategoriaCuidadoEnum.BEM_ESTAR, a2.getId());
        saveRegistro("Consulta de rotina", CategoriaCuidadoEnum.PREVENTIVO, a3.getId());

        System.out.println("=== Carga inicial concluída com sucesso! ===");
    }

    private void saveAcao(String nome, String desc, String prox, String obs, Animal animal) {
        acaoPreventivaRepository.save(AcaoPreventiva.preventivoBuilder()
                .nomeServico(nome)
                .descricao(desc)
                .proximoPrevisto(prox)
                .observacao(obs)
                .animal(animal)
                .dataHoraRegistro(LocalDateTime.now())
                .build());
    }

    private void saveAtividade(String nome, String obsAtiv, String duracao, String obs, String cat, java.util.UUID animalId) {
        atividadeBemEstarRepository.save(AtividadeBemEstar.builder()
                .atividade(nome)
                .observacaoAtividade(obsAtiv)
                .duracao(duracao)
                .observacao(obs)
                .categoria(cat)
                .dataHoraRegistro(LocalDateTime.now())
                .animalId(animalId)
                .build());
    }

    private void saveTratamento(String med, java.util.UUID animalId) {
        tratamentoTerapeuticoRepository.save(TratamentoTerapeutico.builder()
                .medicamento(med)
                .animalId(animalId)
                .dataHoraRegistro(LocalDateTime.now())
                .build());
    }

    // Novo método para salvar RegistroCuidado
    private void saveRegistro(String desc, CategoriaCuidadoEnum categoria, java.util.UUID animalId) {
        RegistroCuidado registro = new RegistroCuidado();
        registro.setDescricao(desc);
        registro.setCategoria(categoria);
        registro.setAnimalId(animalId);
        registro.setDataHoraRegistro(LocalDateTime.now());
        registroCuidadoRepository.save(registro);
    }
}