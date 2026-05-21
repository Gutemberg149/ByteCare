package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.CategoriaCuidadoEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoAnimalEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public void run(String... args) {
        if (animalRepository.count() > 0) {
            return;
        }

        // 1. Persistência de Animais
        Animal a1 = criarAnimal("Rex", TipoAnimalEnum.CACHORRO, "Labrador", "2023-05-10", "Muito enérgico", true);
        Animal a2 = criarAnimal("Mimi", TipoAnimalEnum.GATO, "Siamês", "2024-02-15", "Gosta de dormir", true);
        Animal a3 = criarAnimal("Thor", TipoAnimalEnum.CACHORRO, "Golden", "2022-11-20", "Adora bolinhas", true);
        Animal a4 = criarAnimal("Luna", TipoAnimalEnum.GATO, "Persa", "2025-01-05", "Muito dócil", false);
        Animal a5 = criarAnimal("Spike", TipoAnimalEnum.CACHORRO, "Bulldog", "2023-08-30", "Cuidados com pele", true);

        // 2. Ações Preventivas
        saveAcao("Vacinação", "V10", "2026-05-20", "Manter repouso", CategoriaCuidadoEnum.BEM_ESTAR, a1);
        saveAcao("Vermifugação", "Oral", "2026-06-15", "Repetir em 3 meses", CategoriaCuidadoEnum.PREVENTIVO, a2);

        // 3. Atividades
        saveAtividade("Caminhada", "Leve", "30 min", "Ritmo constante", "Físico", a1);
        saveAtividade("Massagem", "Relaxante", "15 min", "Música calma", "Bem-estar", a5);

        // 4. Tratamentos
        saveTratamento("Amoxicilina", "500mg", "12/12h", "7 dias", "Após refeição", "Antibiótico", a1);
        saveTratamento("Dipirona", "200mg", "8/8h", "5 dias", "Para febre", "Analgésico", a2);

        // 5. Registros
        saveRegistro("Banho completo", CategoriaCuidadoEnum.BEM_ESTAR, a1);
        saveRegistro("Corte de unhas", CategoriaCuidadoEnum.BEM_ESTAR, a2);
    }

    private Animal criarAnimal(String nome, TipoAnimalEnum tipo, String raca, String nasc, String obs, boolean ativo) {
        return animalRepository.save(Animal.builder()
                .nome(nome)
                .tipo(tipo.name())
                .raca(raca)
                .dataNascimento(LocalDate.parse(nasc))
                .observacaoGeral(obs)
                .ativo(ativo)
                .build());
    }

    private void saveAcao(String n, String d, String p, String o, CategoriaCuidadoEnum cat, Animal a) {
        acaoPreventivaRepository.save(AcaoPreventiva.preventivoBuilder()
                .nomeServico(n).descricao(d).proximoPrevisto(p).observacao(o)
                .categoria(cat).dataHoraRegistro(LocalDateTime.now()).animal(a).build());
    }

    private void saveAtividade(String n, String obsAti, String dur, String obs, String cat, Animal a) {
        atividadeBemEstarRepository.save(AtividadeBemEstar.builder()
                .atividade(n).observacaoAtividade(obsAti).duracao(dur).observacao(obs)
                .categoria(cat).dataHoraRegistro(LocalDateTime.now()).animal(a).animalId(a.getId()).build());
    }

    private void saveTratamento(String m, String dos, String fre, String dur, String obs, String cat, Animal a) {
        tratamentoTerapeuticoRepository.save(TratamentoTerapeutico.builder()
                .medicamento(m).dosagem(dos).frequencia(fre).duracaoTratamento(dur)
                .observacao(obs).categoria(cat).dataHoraRegistro(LocalDateTime.now()).animal(a).build());
    }

    private void saveRegistro(String desc, CategoriaCuidadoEnum cat, Animal a) {
        registroCuidadoRepository.save(RegistroCuidado.builder()
                .descricao(desc).categoria(cat).animal(a).dataHoraRegistro(LocalDateTime.now()).build());
    }
}