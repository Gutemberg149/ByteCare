package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnimalRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.AcaoPreventivaRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.AtividadeBemEstarRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.TratamentoTerapeuticoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Animal;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AcaoPreventiva;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AtividadeBemEstar;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TratamentoTerapeutico;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final AnimalRepository animalRepository;
    private final AcaoPreventivaRepository acaoPreventivaRepository;
    private final AtividadeBemEstarRepository atividadeBemEstarRepository;
    private final TratamentoTerapeuticoRepository tratamentoTerapeuticoRepository;

    @Override
    public void run(String... args) throws Exception {

        // Proteção para evitar duplicação de dados ao usar ddl-auto: update
        if (animalRepository.count() > 0) {
            System.out.println("=== Banco já contem dados. Carga inicial pulada para evitar duplicidade. ===");
            System.out.println("Animais cadastrados: " + animalRepository.count());
            return;
        }

        // ================= ANIMAIS =================

        Animal animal1 = Animal.builder()
                .nome("Rex")
                .tipo(TipoAnimalEnum.CACHORRO)
                .raca("Labrador")
                .dataNascimento(LocalDate.of(2020, 1, 15))
                .observacaoGeral("Muito ativo e brincalhão")
                .ativo(true)
                .build();
        animalRepository.save(animal1);

        Animal animal2 = Animal.builder()
                .nome("Mimi")
                .tipo(TipoAnimalEnum.GATO)
                .raca("Persa")
                .dataNascimento(LocalDate.of(2019, 5, 20))
                .observacaoGeral("Gosta de dormir e comer")
                .ativo(true)
                .build();
        animalRepository.save(animal2);

        Animal animal3 = Animal.builder()
                .nome("Thor")
                .tipo(TipoAnimalEnum.CACHORRO)
                .raca("Golden Retriever")
                .dataNascimento(LocalDate.of(2018, 10, 10))
                .observacaoGeral("Muito amigável")
                .ativo(true)
                .build();
        animalRepository.save(animal3);

        Animal animal4 = Animal.builder()
                .nome("Luna")
                .tipo(TipoAnimalEnum.GATO)
                .raca("Siamês")
                .dataNascimento(LocalDate.of(2021, 3, 25))
                .observacaoGeral("Muito preguiçosa")
                .ativo(false)
                .build();
        animalRepository.save(animal4);

        Animal animal5 = Animal.builder()
                .nome("Spike")
                .tipo(TipoAnimalEnum.CACHORRO)
                .raca("Pug")
                .dataNascimento(LocalDate.of(2022, 7, 8))
                .observacaoGeral("Brincalhão e energético")
                .ativo(true)
                .build();
        animalRepository.save(animal5);

        // ================= AÇÕES PREVENTIVAS =================

        AcaoPreventiva acao1 = AcaoPreventiva.preventivoBuilder()
                .nomeServico("Vacinação anual")
                .descricao("Vacina V10 contra doenças virais")
                .proximoPrevisto("2025-05-20")
                .observacao("Aplicar no próximo semestre")
                .animal(animal1)
                .build();
        acaoPreventivaRepository.save(acao1);

        AcaoPreventiva acao2 = AcaoPreventiva.preventivoBuilder()
                .nomeServico("Vermifugação")
                .descricao("Vermífugo oral para parasitas")
                .proximoPrevisto("2025-06-15")
                .observacao("Repetir a cada 3 meses")
                .animal(animal2)
                .build();
        acaoPreventivaRepository.save(acao2);

        AcaoPreventiva acao3 = AcaoPreventiva.preventivoBuilder()
                .nomeServico("Check-up semestral")
                .descricao("Exame clínico completo")
                .proximoPrevisto("2025-07-01")
                .observacao("Levar exames anteriores")
                .animal(animal3)
                .build();
        acaoPreventivaRepository.save(acao3);

        // ================= ATIVIDADES BEM-ESTAR =================

        AtividadeBemEstar atividade1 = AtividadeBemEstar.bemEstarBuilder()
                .nomeAtividade("Caminhada diária")
                .observacaoAtividade("30 minutos de caminhada")
                .duracao("30 min")
                .observacao("Fazer sempre pela manhã")
                .animal(animal1)
                .build();
        atividadeBemEstarRepository.save(atividade1);

        AtividadeBemEstar atividade2 = AtividadeBemEstar.bemEstarBuilder()
                .nomeAtividade("Sessão de brincadeiras")
                .observacaoAtividade("Bolinha e corda")
                .duracao("15 min")
                .observacao("Estimular atividade física")
                .animal(animal2)
                .build();
        atividadeBemEstarRepository.save(atividade2);

        AtividadeBemEstar atividade3 = AtividadeBemEstar.bemEstarBuilder()
                .nomeAtividade("Natação")
                .observacaoAtividade("Exercício na piscina")
                .duracao("20 min")
                .observacao("Ótimo para articulações")
                .animal(animal3)
                .build();
        atividadeBemEstarRepository.save(atividade3);

        AtividadeBemEstar atividade4 = AtividadeBemEstar.bemEstarBuilder()
                .nomeAtividade("Massagem relaxante")
                .observacaoAtividade("Massagem terapêutica")
                .duracao("10 min")
                .observacao("Ajuda na circulação")
                .animal(animal5)
                .build();
        atividadeBemEstarRepository.save(atividade4);

        // ================= TRATAMENTOS TERAPÊUTICOS =================

        TratamentoTerapeutico tratamento1 = TratamentoTerapeutico.terapeuticoBuilder()
                .nomeMedicamento("Amoxicilina")
                .dosagem("500mg")
                .frequencia("12/12h")
                .duracaoTratamento("7 dias")
                .observacao("Tomar após refeição")
                .animal(animal1)
                .build();
        tratamentoTerapeuticoRepository.save(tratamento1);

        TratamentoTerapeutico tratamento2 = TratamentoTerapeutico.terapeuticoBuilder()
                .nomeMedicamento("Dipirona")
                .dosagem("200mg")
                .frequencia("8/8h")
                .duracaoTratamento("5 dias")
                .observacao("Para febre")
                .animal(animal2)
                .build();
        tratamentoTerapeuticoRepository.save(tratamento2);

        TratamentoTerapeutico tratamento3 = TratamentoTerapeutico.terapeuticoBuilder()
                .nomeMedicamento("Prediderm")
                .dosagem("25mg")
                .frequencia("24/24h")
                .duracaoTratamento("10 dias")
                .observacao("Para alergias")
                .animal(animal3)
                .build();
        tratamentoTerapeuticoRepository.save(tratamento3);

        TratamentoTerapeutico tratamento4 = TratamentoTerapeutico.terapeuticoBuilder()
                .nomeMedicamento("Vermífugo")
                .dosagem("500mg")
                .frequencia("Dose única")
                .duracaoTratamento("1 dia")
                .observacao("Repetir em 15 dias")
                .animal(animal4)
                .build();
        tratamentoTerapeuticoRepository.save(tratamento4);

        TratamentoTerapeutico tratamento5 = TratamentoTerapeutico.terapeuticoBuilder()
                .nomeMedicamento("Omeprazol")
                .dosagem("20mg")
                .frequencia("24/24h")
                .duracaoTratamento("14 dias")
                .observacao("Administrar em jejum")
                .animal(animal5)
                .build();
        tratamentoTerapeuticoRepository.save(tratamento5);

        System.out.println("=== Dados carregados com sucesso! ===");
        System.out.println("Animais: " + animalRepository.count());
        System.out.println("Ações Preventivas: " + acaoPreventivaRepository.count());
        System.out.println("Atividades Bem-Estar: " + atividadeBemEstarRepository.count());
        System.out.println("Tratamentos Terapêuticos: " + tratamentoTerapeuticoRepository.count());
    }
}