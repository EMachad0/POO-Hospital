package com.machado.view;

import com.machado.negocio.Sistema;

import java.util.Scanner;

public class Main {

    public static final Scanner in = new Scanner(System.in);
    private static final Sistema s = new Sistema();

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
            if (op.equals("1")) s.cadastraMedico();
            else if (op.equals("2")) s.cadastraPaciente();
            else if (op.equals("3")) s.cadastraConsulta();
            else if (op.equals("4")) s.alteraMedico();
            else if (op.equals("5")) s.alteraPaciente();
            else if (op.equals("6")) s.alteraConsulta();
            else if (op.equals("7")) s.mostraMedico();
            else if (op.equals("8")) s.mostraPaciente();
            else if (op.equals("9")) s.mostraConsulta();
            else break;
        }
    }
}
