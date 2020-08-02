package com.machado.dados;

import com.machado.view.Main;

public class Medico extends Pessoa {

    private String especialidade;

    public Medico(long cpf, String nome, short idade, String cidade, String especialidade) {
        super(cpf, nome, idade, cidade);
        this.especialidade = especialidade;
    }

    public Consulta geraConsulta(Paciente p) {
        System.out.println("Qual o diagnostico");
        String diagnostico = "Devido aos sintomas " + p.getDescricao() + " o paciente tem " + Main.in.nextLine();
        return new Consulta(diagnostico, p, this);
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "nome='" + nome + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", cpf=" + cpf +
                ", idade=" + idade +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
