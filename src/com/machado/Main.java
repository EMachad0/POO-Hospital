package com.machado;

import com.github.javafaker.Faker;
import com.machado.dados.Consulta;
import com.machado.dados.Medico;
import com.machado.dados.Paciente;
import com.machado.negocio.Sistema;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final Scanner in = new Scanner(System.in);

    public static final Faker faker = new Faker(new Locale("pt-BR"));
    public static final Random rand = new Random();

    public static final Sistema sistema = new Sistema();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Qual operacao?");

            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar Medico");
            System.out.println("2 - Cadastrar Paciente");
            System.out.println("3 - Cadastrar Consulta");
            System.out.println("4 - Alterar Medico");
            System.out.println("5 - Alterar Paciente");
            System.out.println("6 - Alterar Consulta");
            System.out.println("7 - Mostrar Medicos");
            System.out.println("8 - Mostrar Pacientes");
            System.out.println("9 - Mostrar Consultas");

            String op = in.nextLine();
            if (op.equals("1")) cadastraMedico();
            else if (op.equals("2")) cadastraPaciente();
            else if (op.equals("3")) cadastraConsulta();
            else if (op.equals("4")) alteraMedico();
            else if (op.equals("5")) alteraPaciente();
            else if (op.equals("6")) alteraConsulta();
            else if (op.equals("7")) mostraMedico();
            else if (op.equals("8")) mostraPaciente();
            else if (op.equals("9")) mostraConsulta();
            else break;
        }
    }

    public static void cadastraMedico() {
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

    public static void cadastraConsulta() {
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
        sistema.cadastra(new Consulta(0, valor, new Date(), diagnostico, p, m));
    }

    public static void cadastraPaciente() {
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

    public static void alteraMedico() {
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
    }

    public static void alteraConsulta() {
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
            else if (op.equals("2")) c.getDate().setTime(Long.parseLong(novo));
            else if (op.equals("3")) c.setDiagnostico(novo);
            else break;
        }
    }

    public static void alteraPaciente() {
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
    }

    public static void mostraMedico() {
        AtomicInteger i = new AtomicInteger();
        sistema.getMedicos().forEach(m -> System.out.println(i.getAndIncrement() + " - " + m));
    }

    public static void mostraConsulta() {
        AtomicInteger i = new AtomicInteger();
        sistema.getPacientes().forEach(c -> System.out.println(i.getAndIncrement() + " - " + c));
    }

    public static void mostraPaciente() {
        AtomicInteger i = new AtomicInteger();
        sistema.getPacientes().forEach(p -> System.out.println(i.getAndIncrement() + " - " + p));
    }
}
