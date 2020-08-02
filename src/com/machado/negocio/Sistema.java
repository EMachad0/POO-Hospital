package com.machado.negocio;

import com.machado.dados.Consulta;
import com.machado.dados.Medico;
import com.machado.dados.Paciente;
import com.machado.view.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Sistema {

    private final Scanner in = Main.in;

    private final List<Medico> medicos = new ArrayList<>();
    private final List<Consulta> consultas = new ArrayList<>();
    private final List<Paciente> pacientes = new ArrayList<>();


    public void cadastraMedico() {
        medicos.add(new Medico());
    }

    public void cadastraConsulta() {
        System.out.println("Qual Paciente?");
        mostraPaciente();
        Paciente p = pacientes.get(in.nextInt());
        System.out.println("Qual Medico?");
        mostraMedico();
        Medico m = medicos.get(in.nextInt());
        consultas.add(m.geraConsulta(p));
        in.nextLine();
    }

    public void cadastraPaciente() {
        pacientes.add(new Paciente());
    }

    public void alteraMedico() {
        System.out.println("Qual Medico?");
        mostraMedico();
        Medico m = medicos.get(in.nextInt());
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

    public void alteraConsulta() {
        System.out.println("Qual Consulta?");
        mostraConsulta();
        Consulta c = consultas.get(in.nextInt());
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

    public void alteraPaciente() {
        System.out.println("Qual Paciente?");
        mostraPaciente();
        Paciente p = pacientes.get(in.nextInt());
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

    public void mostraMedico() {
        AtomicInteger i = new AtomicInteger();
        medicos.forEach(m -> System.out.println(i.getAndIncrement() + " - " + m));
    }

    public void mostraConsulta() {
        AtomicInteger i = new AtomicInteger();
        consultas.forEach(c -> System.out.println(i.getAndIncrement() + " - " + c));
    }

    public void mostraPaciente() {
        AtomicInteger i = new AtomicInteger();
        pacientes.forEach(p -> System.out.println(i.getAndIncrement() + " - " + p));
    }
}
