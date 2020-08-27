package com.machado;

import com.github.javafaker.Faker;
import com.machado.dados.Consulta;
import com.machado.dados.Medico;
import com.machado.dados.Paciente;
import com.machado.negocio.Sistema;
import com.machado.persistencia.Conexao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final Scanner in = new Scanner(System.in);

    public static final Faker faker = new Faker(new Locale("pt-BR"));
    public static final Random rand = new Random();

    public static Sistema sistema;

    public static void main(String[] args) {
        try {
            Conexao.setSenha("eliton");
            sistema = new Sistema();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        while (true) {
            System.out.println("Qual operacao?");

            System.out.println("00 - Sair");
            System.out.println("01 - Cadastrar Medico");
            System.out.println("02 - Cadastrar Paciente");
            System.out.println("03 - Cadastrar Consulta");
            System.out.println("04 - Alterar Medico");
            System.out.println("05 - Alterar Paciente");
            System.out.println("06 - Alterar Consulta");
            System.out.println("07 - Remove Medico");
            System.out.println("08 - Remove Paciente");
            System.out.println("09 - Remove Consulta");
            System.out.println("10 - Mostrar Medicos");
            System.out.println("11 - Mostrar Pacientes");
            System.out.println("12 - Mostrar Consultas");

            String sop = in.nextLine();
            try {
                int op = Integer.parseInt(sop);

                if (op == 0) break;
                else if (op == 1) cadastraMedico();
                else if (op == 2) cadastraPaciente();
                else if (op == 3) cadastraConsulta();
                else if (op == 4) alteraMedico();
                else if (op == 5) alteraPaciente();
                else if (op == 6) alteraConsulta();
                else if (op == 7) removeMedico();
                else if (op == 8) removePaciente();
                else if (op == 9) removeConsulta();
                else if (op == 10) mostraMedico();
                else if (op == 11) mostraPaciente();
                else if (op == 12) mostraConsulta();
                else System.out.println("Numero Invalido");
            } catch (NumberFormatException e) {
                System.out.println("Não é uma opção valida");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void cadastraMedico() throws SQLException {
        System.out.println("Cpf?");
        long cpf = rand.nextInt((int) 1e12);
        System.out.println("Nome?");
        String nome = faker.name().fullName();
        System.out.println("Idade?");
        short idade = (short) rand.nextInt(151);
        System.out.println("Cidade?");
        String cidade = faker.address().city();
        System.out.println("Especialidade?");
        String especialidade = faker.job().title();
        sistema.cadastra(new Medico(cpf, nome, idade, cidade, especialidade));
    }

    public static void cadastraConsulta() throws SQLException {
        System.out.println("Qual Paciente?");
        mostraPaciente();
        Paciente p = sistema.getPacientes().get(in.nextInt());
        System.out.println("Qual Medico?");
        mostraMedico();
        Medico m = sistema.getMedicos().get(in.nextInt());
        in.nextLine();
        System.out.println("Qual Valor?");
        float valor = (float) (Math.random() * 1000);
        System.out.println("Qual o diagnostico?");
        String diagnostico = "Devido aos sintomas " + p.getDescricao() + " o paciente tem " + faker.medical().diseaseName();
        sistema.cadastra(new Consulta(0, valor, LocalDate.now(ZoneId.of("America/Sao_Paulo")), diagnostico, p, m));
    }

    public static void cadastraPaciente() throws SQLException {
        System.out.println("Cpf?");
        long cpf = rand.nextInt((int) 1e12);
        System.out.println("Nome?");
        String nome = faker.name().fullName();
        System.out.println("Idade?");
        short idade = (short) rand.nextInt(151);
        System.out.println("Cidade?");
        String cidade = faker.address().city();
        System.out.println("Sintomas?");
        String descricao = faker.medical().symptoms();
        sistema.cadastra(new Paciente(cpf, nome, idade, cidade, descricao));
    }

    public static void alteraMedico() throws SQLException {
        System.out.println("Qual Medico?");
        mostraMedico();
        Medico m = sistema.getMedicos().get(in.nextInt());
        in.nextLine();

        while (true) {
            System.out.println("Alterando o medico:");
            System.out.println(m);
            System.out.println("Qual parametro?");
            System.out.println("0 - Cancelar");
            System.out.println("1 - Nome");
            System.out.println("2 - Idade");
            System.out.println("3 - Cidade");
            System.out.println("4 - Especialidade");

            String op = in.nextLine();
            if (op.equals("0")) break;

            System.out.println("Qual o novo valor?");
            String novo = in.nextLine();

            if (op.equals("1")) m.setNome(novo);
            else if (op.equals("2")) m.setIdade(Short.parseShort(novo));
            else if (op.equals("3")) m.setCidade(novo);
            else if (op.equals("4")) m.setEspecialidade(novo);
            else break;
        }
        sistema.update(m);
    }

    public static void alteraConsulta() throws SQLException {
        System.out.println("Qual Consulta?");
        mostraConsulta();
        Consulta c = sistema.getConsultas().get(in.nextInt());
        in.nextLine();

        while (true) {
            System.out.println("Alterando a consulta:");
            System.out.println(c);
            System.out.println("Qual parametro?");
            System.out.println("0 - Cancelar");
            System.out.println("1 - Valor");
            System.out.println("2 - Data");
            System.out.println("3 - Diagnostico");

            String op = in.nextLine();
            if (op.equals("0")) break;

            System.out.println("Qual o novo valor?");
            String novo = in.nextLine();

            if (op.equals("1")) c.setValor(Float.parseFloat(novo));
            else if (op.equals("2")) c.setDate(c.getDate().plusDays(Long.parseLong(novo)));
            else if (op.equals("3")) c.setDiagnostico(novo);
            else break;
        }
        sistema.update(c);
    }

    public static void alteraPaciente() throws SQLException {
        System.out.println("Qual Paciente?");
        mostraPaciente();
        Paciente p = sistema.getPacientes().get(in.nextInt());
        in.nextLine();

        while (true) {
            System.out.println("Alterando o paciente:");
            System.out.println(p);
            System.out.println("Qual parametro?");
            System.out.println("0 - Cancelar");
            System.out.println("1 - Nome");
            System.out.println("2 - Idade");
            System.out.println("3 - Cidade");
            System.out.println("4 - Descrição");

            String op = in.nextLine();
            if (op.equals("0")) break;

            System.out.println("Qual o novo valor?");
            String novo = in.nextLine();

            if (op.equals("1")) p.setNome(novo);
            else if (op.equals("2")) p.setIdade(Short.parseShort(novo));
            else if (op.equals("3")) p.setCidade(novo);
            else if (op.equals("4")) p.setDescricao(novo);
            else break;
        }
        sistema.update(p);
    }

    public static void removeMedico() throws SQLException {
        System.out.println("Qual Medico?");
        mostraMedico();
        sistema.remove(sistema.getMedicos().get(in.nextInt()));
        in.nextLine();
    }

    public static void removeConsulta() throws SQLException {
        System.out.println("Qual Consulta?");
        mostraConsulta();
        sistema.remove(sistema.getConsultas().get(in.nextInt()));
        in.nextLine();
    }

    public static void removePaciente() throws SQLException {
        System.out.println("Qual Paciente?");
        mostraPaciente();
        sistema.remove(sistema.getPacientes().get(in.nextInt()));
        in.nextLine();
    }

    public static void mostraMedico() throws SQLException {
        AtomicInteger i = new AtomicInteger();
        sistema.getMedicos().forEach(m -> System.out.println(i.getAndIncrement() + " - " + m));
    }

    public static void mostraConsulta() throws SQLException {
        AtomicInteger i = new AtomicInteger();
        sistema.getConsultas().forEach(c -> System.out.println(i.getAndIncrement() + " - " + c));
    }

    public static void mostraPaciente() throws SQLException {
        AtomicInteger i = new AtomicInteger();
        sistema.getPacientes().forEach(p -> System.out.println(i.getAndIncrement() + " - " + p));
    }
}
