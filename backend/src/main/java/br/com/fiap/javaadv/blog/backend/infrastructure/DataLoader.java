package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataTutPet(
            TutorRepository tutorRep,
            RelatorioRepository relatorioRep,
            ReceitaRepository receitaRep,
            ProntuarioRepository prontuarioRep,
            PetRepository petRep,
            MedicoRepository medicoRep,
            MedicamentoRepository medicamentoRep,
            HistoricoRepository historicoRep,
            ExameRepository exameRep,
            EnderecoRepository enderecoRep,
            ClinicaRepository clinicaRep) {

        return args -> {

            // ================= TUTORES =================
            Tutor tutor1 = tutorRep.save(Tutor.builder()
                    .id(UUID.fromString("90c87b51-422c-4a4e-83ca-eb73fdc1932a"))
                    .nome("Sandra Nascimento")
                    .dataNascimento(Date.valueOf("1968-11-14"))
                    .telefone("11992746375")
                    .email("sandraReg@gmail.com")
                    .sexo(SexoEnum.F)
                    .senha("523453466256")
                    .build());

            Tutor tutor2 = tutorRep.save(Tutor.builder()
                    .id(UUID.fromString("978c415d-7c8b-4b37-af9a-d54fcb1bda46"))
                    .nome("Noelle Almeida")
                    .dataNascimento(Date.valueOf("1988-05-18"))
                    .telefone("11992933212")
                    .email("Nolal@gmail.com")
                    .sexo(SexoEnum.F)
                    .senha("2445356ghd5")
                    .build());

            Tutor tutor3 = tutorRep.save(Tutor.builder()
                    .id(UUID.fromString("6cc9935e-874f-4569-82b2-1c58baf91e79"))
                    .nome("Lucia Lurdes")
                    .dataNascimento(Date.valueOf("1993-04-23"))
                    .telefone("11993857635")
                    .email("lulu.lurdes@gmail.com")
                    .sexo(SexoEnum.F)
                    .senha("233453564fghj")
                    .build());

            // ================= HISTÓRICO =================
            Historico hist1 = historicoRep.save(Historico.builder()
                    .id(UUID.fromString("0b5920dc-c674-4df9-a1b2-d94acb659443"))
                    .data(Date.valueOf("2026-05-03"))
                    .status(StatusEnum.INATIVO)
                    .build());

            Historico hist2 = historicoRep.save(Historico.builder()
                    .id(UUID.fromString("bac1afb7-5d9d-4f83-8017-b4e29c8d66f9"))
                    .data(Date.valueOf("2025-07-25"))
                    .status(StatusEnum.ATIVO)
                    .build());

            Historico hist3 = historicoRep.save(Historico.builder()
                    .id(UUID.fromString("296607a4-f07a-4018-998b-69367cb01fa5"))
                    .data(Date.valueOf("2026-02-09"))
                    .status(StatusEnum.ATIVO)
                    .build());

            Historico hist4 = historicoRep.save(Historico.builder()
                    .id(UUID.fromString("e8d25a08-94ff-43d0-9a54-da279a868e8f"))
                    .data(Date.valueOf("2026-05-19"))
                    .status(StatusEnum.ATIVO)
                    .build());

            Historico hist5 = historicoRep.save(Historico.builder()
                    .id(UUID.fromString("64c6b86a-17d6-4693-912c-331d83ae1c06"))
                    .data(Date.valueOf("2026-03-01"))
                    .status(StatusEnum.ATIVO)
                    .build());

            // ================= MÉDICOS =================
            Medico med1 = medicoRep.save(Medico.builder()
                    .id(UUID.fromString("47d44b69-bfa6-4c9e-a01a-bb5698596797"))
                    .nome("Renato Gonçalves")
                    .dataNascimento(Date.valueOf("1990-09-04"))
                    .telefone("11857284924")
                    .email("renatoGon@gmail.com")
                    .sexo(SexoEnum.M)
                    .senha("08945706")
                    .especialidade("Endocrinologia")
                    .build());

            Medico med2 = medicoRep.save(Medico.builder()
                    .id(UUID.fromString("765ac59c-5f81-4943-8986-7e048b15e474"))
                    .nome("Julio Juniro")
                    .dataNascimento(Date.valueOf("1993-04-23"))
                    .telefone("11883746219")
                    .email("JU9836247@gmail.com")
                    .sexo(SexoEnum.M)
                    .senha("439857$%")
                    .especialidade("Cardiologia")
                    .build());

            Medico med3 = medicoRep.save(Medico.builder()
                    .id(UUID.fromString("9c02a90a-69d6-4fc1-9f01-1588ae05e2ca"))
                    .nome("Amanhda Rocha")
                    .dataNascimento(Date.valueOf("1993-04-23"))
                    .telefone("11983647515")
                    .email("RAFAFAF9483794@gmail.com")
                    .sexo(SexoEnum.M)
                    .senha("34957DF%")
                    .especialidade("Nutrição")
                    .build());

            // ================= PETS =================
            Pet pet1 = petRep.save(Pet.builder()
                    .id(UUID.fromString("1405e532-7085-4a07-80e8-5d36e4d35c44"))
                    .nome("Olimpio")
                    .especie("Cachorro")
                    .raca("Yorkshire")
                    .dataNascimento(Date.valueOf("2006-11-12"))
                    .pelagem("Média amarronzada")
                    .porte("Pequeno")
                    .sexo(SexoEnum.M)
                    .status(StatusEnum.INATIVO)
                    .urlImg("")
                    .tutores(Set.of(tutor1))
                    .build());

            Pet pet2 = petRep.save(Pet.builder()
                    .id(UUID.fromString("c76e70e4-e5e4-457d-9a10-7d6d2f8e0488"))
                    .nome("Dori")
                    .especie("Peixe")
                    .raca("Beta")
                    .dataNascimento(Date.valueOf("2020-06-10"))
                    .pelagem("Vermelha")
                    .porte("Pequeno")
                    .sexo(SexoEnum.F)
                    .status(StatusEnum.ATIVO)
                    .urlImg("")
                    .tutores(Set.of(tutor2))
                    .build());

            Pet pet3 = petRep.save(Pet.builder()
                    .id(UUID.fromString("2d4a93eb-0434-4671-b4ce-9f3fc24316d3"))
                    .nome("Thor")
                    .especie("Cachorro")
                    .raca("Golden retriever")
                    .dataNascimento(Date.valueOf("2010-07-02"))
                    .pelagem("Amarronzada")
                    .porte("Medio")
                    .sexo(SexoEnum.M)
                    .status(StatusEnum.ATIVO)
                    .urlImg("")
                    .tutores(Set.of(tutor2))
                    .build());

            Pet pet4 = petRep.save(Pet.builder()
                    .id(UUID.fromString("c057e4be-03d1-4c84-982f-e43aa4c3cace"))
                    .nome("Lidia")
                    .especie("Gato")
                    .raca("Malhado")
                    .dataNascimento(Date.valueOf("2017-02-06"))
                    .pelagem("Malhado")
                    .porte("Pequeno")
                    .sexo(SexoEnum.F)
                    .status(StatusEnum.ATIVO)
                    .urlImg("")
                    .tutores(Set.of(tutor3))
                    .build());

            Pet pet5 = petRep.save(Pet.builder()
                    .id(UUID.fromString("ce5a6129-1eed-45fa-b237-4760f623dadf"))
                    .nome("Maria")
                    .especie("Cachorro")
                    .raca("Pug")
                    .dataNascimento(Date.valueOf("2023-09-19"))
                    .pelagem("Pelagem Preta")
                    .porte("Pequeno")
                    .sexo(SexoEnum.F)
                    .status(StatusEnum.ATIVO)
                    .urlImg("")
                    .tutores(Set.of(tutor3))
                    .build());

            // ================= EXAMES =================
            Exame ex1 = exameRep.save(Exame.builder()
                    .id(UUID.fromString("ad257493-f6d3-4dc4-be4a-cc3c25fba540"))
                    .nome("Teste de Glicemia")
                    .data(Date.valueOf("2025-01-07"))
                    .tipo("Exame endócrino")
                    .medico(med1)
                    .build());

            Exame ex2 = exameRep.save(Exame.builder()
                    .id(UUID.fromString("6f36c712-d4cb-4648-97f3-eac7866a41e4"))
                    .nome("Hemograma completo")
                    .data(Date.valueOf("2025-05-04"))
                    .tipo("Exame de sangue")
                    .medico(med1)
                    .build());

            Exame ex3 = exameRep.save(Exame.builder()
                    .id(UUID.fromString("6135688d-b25e-40b7-90f6-44dc15023a77"))
                    .nome("Exame Raio-x")
                    .data(Date.valueOf("2025-06-02"))
                    .tipo("Exame de imagem")
                    .medico(med3)
                    .build());

            Exame ex4 = exameRep.save(Exame.builder()
                    .id(UUID.fromString("2ac99355-33fd-4080-bb5d-5220cdad1f8e"))
                    .nome("Teste de Glicemia")
                    .data(Date.valueOf("2025-03-17"))
                    .tipo("Exame endócrino")
                    .medico(med3)
                    .build());

            Exame ex5 = exameRep.save(Exame.builder()
                    .id(UUID.fromString("abd73853-85dd-43b8-934d-febe4cf1f661"))
                    .nome("Exame de urina")
                    .data(Date.valueOf("2025-04-27"))
                    .tipo("Exame parasitológico")
                    .medico(med2)
                    .build());

            Exame ex6 = exameRep.save(Exame.builder()
                    .id(UUID.fromString("b9080566-5530-4eb2-a0d9-d9650fbdfa50"))
                    .nome("Hemograma completo")
                    .data(Date.valueOf("2025-09-18"))
                    .tipo("Exame de sangue")
                    .medico(med2)
                    .build());

            // ================= RECEITAS =================
            Receita rec1 = receitaRep.save(Receita.builder()
                    .id(UUID.fromString("0efc7b5b-1f83-476e-9fc1-7be4360ab9c0"))
                    .nome("Receita hormonal")
                    .validade(Date.valueOf("2026-02-04"))
                    .medico(med2)
                    .build());

            Receita rec2 = receitaRep.save(Receita.builder()
                    .id(UUID.fromString("dc656926-011d-435c-a33c-f71df7b56ecc"))
                    .nome("Receita vermes")
                    .validade(Date.valueOf("2026-01-08"))
                    .medico(med1)
                    .build());

            Receita rec3 = receitaRep.save(Receita.builder()
                    .id(UUID.fromString("4a277997-e62f-4683-adc2-cde55f27dc64"))
                    .nome("Receita gripe")
                    .validade(Date.valueOf("2026-01-02"))
                    .medico(med2)
                    .build());

            Receita rec4 = receitaRep.save(Receita.builder()
                    .id(UUID.fromString("918d90f2-91e3-4124-8986-5fbbb143574a"))
                    .nome("Receita diabetes")
                    .validade(Date.valueOf("2026-04-14"))
                    .medico(med3)
                    .build());

            Receita rec5 = receitaRep.save(Receita.builder()
                    .id(UUID.fromString("fe491251-62e7-47a4-a576-f98412fd7a53"))
                    .nome("Receita hormonal 2")
                    .validade(Date.valueOf("2026-03-24"))
                    .medico(med1)
                    .build());

            Receita rec6 = receitaRep.save(Receita.builder()
                    .id(UUID.fromString("2162c111-528a-4700-a0e5-e7a3a674c094"))
                    .nome("Receita controle")
                    .validade(Date.valueOf("2026-01-25"))
                    .medico(med3)
                    .build());

            // ================= MEDICAMENTOS =================
            Medicamento m1 = medicamentoRep.save(Medicamento.builder()
                    .id(UUID.fromString("9be6cf8f-9e06-4dea-b767-6de2c552536d"))
                    .nome("Beneflora")
                    .dosagem("14mg")
                    .instrucao("1 manhã")
                    .receitas(Set.of(rec1, rec2))
                    .build());

            Medicamento m2 = medicamentoRep.save(Medicamento.builder()
                    .id(UUID.fromString("a923cf7e-a739-4a81-945b-2806e18c1cac"))
                    .nome("Levotiroxina")
                    .dosagem("25mg")
                    .instrucao("1 manhã")
                    .receitas(Set.of(rec2))
                    .build());

            Medicamento m3 = medicamentoRep.save(Medicamento.builder()
                    .id(UUID.fromString("27a68679-4675-4808-9c29-c2222579a64a"))
                    .nome("Prediderm")
                    .dosagem("25mg")
                    .instrucao("manhã/noite")
                    .receitas(Set.of(rec3, rec4, rec1))
                    .build());

            Medicamento m4 = medicamentoRep.save(Medicamento.builder()
                    .id(UUID.fromString("2001e84d-7aab-4718-8c46-38101d4f1823"))
                    .nome("Vermífugo")
                    .dosagem("500mg")
                    .instrucao("1 manhã")
                    .receitas(Set.of(rec5, rec6))
                    .build());

            Medicamento m5 = medicamentoRep.save(Medicamento.builder()
                    .id(UUID.fromString("4e19ed2a-bc24-4436-bda4-19a105e23fc4"))
                    .nome("Meloxicam")
                    .dosagem("2mg")
                    .instrucao("após almoço")
                    .receitas(Set.of(rec3, rec6))
                    .build());

            Medicamento m6 = medicamentoRep.save(Medicamento.builder()
                    .id(UUID.fromString("c4558479-8f35-4fa2-b479-fe3a12e90668"))
                    .nome("Omeprazol")
                    .dosagem("20mg")
                    .instrucao("jejum")
                    .receitas(Set.of(rec4, rec6, rec2))
                    .build());

            // ================= RELATÓRIOS =================
            Relatorio r1 = relatorioRep.save(Relatorio.builder()
                    .id(UUID.fromString("3fa6e8fb-9b5e-48d8-b079-9eb40139766a"))
                    .observacao("Paciente apresentou alteração hormonal e iniciou acompanhamento clínico.")
                    .historico(hist1)
                    .medico(med1)
                    .build());

            Relatorio r2 = relatorioRep.save(Relatorio.builder()
                    .id(UUID.fromString("f2e4444e-3735-4d1a-b4a2-91622a839c29"))
                    .observacao("Paciente apresentou melhora significativa após tratamento de hipotermia.")
                    .historico(hist2)
                    .medico(med3)
                    .build());

            Relatorio r3 = relatorioRep.save(Relatorio.builder()
                    .id(UUID.fromString("5b28f091-6606-4538-998b-046c48270bcf"))
                    .observacao("Paciente apresentou episódios de febre e fadiga durante a consulta.")
                    .historico(hist3)
                    .medico(med3)
                    .build());

            Relatorio r4 = relatorioRep.save(Relatorio.builder()
                    .id(UUID.fromString("eb023a8b-3058-4fe0-abb3-f544a563d8a3"))
                    .observacao("Suspeita clínica de diabetes. Solicitados exames complementares.")
                    .historico(hist4)
                    .medico(med1)
                    .build());

            Relatorio r5 = relatorioRep.save(Relatorio.builder()
                    .id(UUID.fromString("d5752b1d-105c-4829-a07b-0d27c4c421ae"))
                    .observacao("Paciente segue em acompanhamento hormonal com resposta positiva ao tratamento.")
                    .historico(hist5)
                    .medico(med1)
                    .build());

// ================= ENDEREÇO =================
            Endereco end1 = enderecoRep.save(Endereco.builder()
                    .id(UUID.fromString("174bcf8c-b2ce-496c-bc35-710813a65836"))
                    .cep("01001000")
                    .complemento("Apto 14")
                    .build());

            Endereco end2 = enderecoRep.save(Endereco.builder()
                    .id(UUID.fromString("e6052b8b-db26-41ea-a449-59b1e39984b2"))
                    .cep("05999999")
                    .complemento("Casa 2")
                    .build());

            Endereco end3 = enderecoRep.save(Endereco.builder()
                    .id(UUID.fromString("6c1d01c1-47b5-46ab-a8bb-0748932fbbd5"))
                    .cep("08499999")
                    .complemento("Centro")
                    .build());

// ================= CLÍNICAS =================
            Diario cli1 = clinicaRep.save(Diario.builder()
                    .id(UUID.fromString("7d4cc917-cc9f-45b9-95ab-fe195014e898"))
                    .cnpj("49582746381934")
                    .nome("Clinica Patas")
                    .endereco(end1)
                    .relatorios(Set.of(r1, r2))
                    .build());

            Diario cli2 = clinicaRep.save(Diario.builder()
                    .id(UUID.fromString("f0bc3b21-0f17-4b02-9199-e7c02068e8fa"))
                    .cnpj("34897297593745")
                    .nome("Clinica ABV")
                    .endereco(end2)
                    .relatorios(Set.of(r3))
                    .build());

            Diario cli3 = clinicaRep.save(Diario.builder()
                    .id(UUID.fromString("dd68fcd3-61f0-423a-932b-360574deb813"))
                    .cnpj("91840725034884")
                    .nome("Clinica PetPte")
                    .endereco(end3)
                    .relatorios(Set.of(r4, r5))
                    .build());

// ================= PRONTUARIOS =================
            Prontuario pront1 = prontuarioRep.save(Prontuario.builder()
                    .id(UUID.fromString("20407246-c6c1-476c-b567-0f65cb685172"))
                    .data(Date.valueOf("2026-05-08"))
                    .descricao("Paciente apresentou alteração hormonal. Tratamento iniciado.")
                    .medico(med1)
                    .exames(Set.of(ex1, ex2))
                    .receitas(Set.of())
                    .historico(hist1)
                    .build());

            Prontuario pront2 = prontuarioRep.save(Prontuario.builder()
                    .id(UUID.fromString("5d21e3e7-c4aa-486f-b271-0f1a1c98e5ac"))
                    .data(Date.valueOf("2025-01-07"))
                    .descricao("Paciente apresentou melhora do tratamento de hipotermia.")
                    .medico(med2)
                    .exames(Set.of())
                    .receitas(Set.of(rec6))
                    .historico(hist2)
                    .build());

            Prontuario pront3 = prontuarioRep.save(Prontuario.builder()
                    .id(UUID.fromString("53fe112e-9843-46c1-8eac-32c946901873"))
                    .data(Date.valueOf("2025-11-20"))
                    .descricao("Paciente apresentou febre alta e fadiga. Tratamento iniciado.")
                    .medico(med3)
                    .exames(Set.of(ex5, ex6))
                    .receitas(Set.of(rec3))
                    .historico(hist3)
                    .build());

            Prontuario pront4 = prontuarioRep.save(Prontuario.builder()
                    .id(UUID.fromString("1d3846d0-ef4e-42ae-bcd9-a10e54a47b3a"))
                    .data(Date.valueOf("2026-02-13"))
                    .descricao("Paciente apresentou sintomas de diabetes. Tratamento iniciado.")
                    .medico(med2)
                    .exames(Set.of(ex4))
                    .receitas(Set.of(rec4))
                    .historico(hist4)
                    .build());

            Prontuario pront5 = prontuarioRep.save(Prontuario.builder()
                    .id(UUID.fromString("f56aad37-ebfb-47ff-8b90-07249d995ebb"))
                    .data(Date.valueOf("2026-03-09"))
                    .descricao("Paciente apresentou alteração hormonal. Necessário controle contínuo.")
                    .medico(med2)
                    .exames(Set.of())
                    .receitas(Set.of(rec1, rec5))
                    .historico(hist5)
                    .build());

            Prontuario pront6 = prontuarioRep.save(Prontuario.builder()
                    .id(UUID.fromString("5104d45e-052b-411d-a340-ac8968d63a8f"))
                    .data(Date.valueOf("2026-05-02"))
                    .descricao("Paciente apresentou sintomas da doença do carrapato. Tratamento iniciado.")
                    .medico(med1)
                    .exames(Set.of(ex3))
                    .receitas(Set.of(rec2))
                    .historico(hist3)
                    .build());

            Prontuario pront7 = prontuarioRep.save(Prontuario.builder()
                    .id(UUID.fromString("d2a6b3a3-6927-480a-a39f-035adf6ab02d"))
                    .data(Date.valueOf("2026-04-22"))
                    .descricao("Paciente apresentou melhora no tratamento da diarreia.")
                    .medico(med3)
                    .exames(Set.of())
                    .receitas(Set.of())
                    .historico(hist5)
                    .build());
            // ================= RELACIONAMENTOS (DEPOIS DO SAVE) =================

            tutor1.setPets(Set.of(pet1));
            tutor2.setPets(Set.of(pet2, pet3));
            tutor3.setPets(Set.of(pet4, pet5));

            //========================================
            pet1.setHistorico(hist1);
            pet2.setHistorico(hist2);
            pet3.setHistorico(hist3);
            pet4.setHistorico(hist4);
            pet5.setHistorico(hist5);

            //========================================
            med1.setRelatorios(Set.of(r1,r4));
            med2.setRelatorios(Set.of(r5));
            med3.setRelatorios(Set.of(r2,r3));
            //----------------------------
            med1.setProntuarios(Set.of(pront1,pront6));
            med2.setProntuarios(Set.of(pront2,pront4,pront5));
            med3.setProntuarios(Set.of(pront3,pront7));
            //----------------------------
            med1.setExames(Set.of(ex1,ex2));
            med2.setExames(Set.of(ex5,ex6));
            med3.setExames(Set.of(ex3,ex4));
            //----------------------------
            med1.setReceitas(Set.of(rec2,rec5));
            med2.setReceitas(Set.of(rec1,rec3));
            med3.setReceitas(Set.of(rec4,rec6));

            //========================================
            hist1.setRelatorios(Set.of(r1));
            hist2.setRelatorios(Set.of(r2));
            hist3.setRelatorios(Set.of(r3));
            hist4.setRelatorios(Set.of(r4));
            hist5.setRelatorios(Set.of(r5));
            //----------------------------
            hist1.setProntuarios(Set.of(pront1));
            hist2.setProntuarios(Set.of(pront2));
            hist3.setProntuarios(Set.of(pront3, pront6));
            hist4.setProntuarios(Set.of(pront4));
            hist5.setProntuarios(Set.of(pront5, pront7));
            //----------------------------
            hist1.setPet(pet1);
            hist2.setPet(pet2);
            hist3.setPet(pet3);
            hist4.setPet(pet4);
            hist5.setPet(pet5);

            //========================================
            rec1.setMedicamentos(Set.of(m1, m3));
            rec2.setMedicamentos(Set.of(m1, m2, m6));
            rec3.setMedicamentos(Set.of(m3, m5));
            rec4.setMedicamentos(Set.of(m3, m6));
            rec5.setMedicamentos(Set.of(m4));
            rec6.setMedicamentos(Set.of(m4, m5, m6));
            //========================================
            ex1.setProntuario(pront1);
            ex2.setProntuario(pront1);
            ex3.setProntuario(pront6);
            ex4.setProntuario(pront4);
            ex5.setProntuario(pront3);
            ex6.setProntuario(pront3);

            //========================================
            rec1.setProntuario(pront5);
            rec2.setProntuario(pront6);
            rec3.setProntuario(pront3);
            rec4.setProntuario(pront4);
            rec5.setProntuario(pront5);
            rec6.setProntuario(pront2);
            //----------------------------
            rec1.setMedicamentos(Set.of(m1, m3));
            rec2.setMedicamentos(Set.of(m1, m2, m6));
            rec3.setMedicamentos(Set.of(m3, m5));
            rec4.setMedicamentos(Set.of(m3, m6));
            rec5.setMedicamentos(Set.of(m4));
            rec6.setMedicamentos(Set.of(m4, m5, m6));

            //========================================
            r1.setClinicas(Set.of(cli1));
            r2.setClinicas(Set.of(cli1));
            r3.setClinicas(Set.of(cli2));
            r4.setClinicas(Set.of(cli3));
            r5.setClinicas(Set.of(cli3));
            //========================================
            end1.setClinica(cli1);
            end2.setClinica(cli2);
            end3.setClinica(cli3);

            // ================= SAVE RELACIONAMENTOS =================
            petRep.saveAll(Set.of(pet1, pet2, pet3, pet4, pet5));
            prontuarioRep.saveAll(Set.of(pront1, pront2, pront3, pront4, pront5, pront6, pront7));
            exameRep.saveAll(Set.of(ex1, ex2, ex3, ex4, ex5, ex6));
            receitaRep.saveAll(Set.of(rec1, rec2, rec3, rec4, rec5, rec6));
            relatorioRep.saveAll(Set.of(r1, r2, r3, r4, r5));
            enderecoRep.saveAll(Set.of(end1, end2, end3));

        };
    }
}