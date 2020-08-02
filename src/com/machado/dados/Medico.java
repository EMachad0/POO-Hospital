package com.machado.dados;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Medico extends Pessoa {

    private String especialidade;

    public Medico(long cpf, String nome, short idade, String cidade, String especialidade) {
        super(cpf, nome, idade, cidade);
        this.especialidade = especialidade;
    }

    public Medico() {
        super();
        Faker f = new Faker(new Locale("pt-BR"));
        especialidade = f.job().title();
    }

    public Consulta geraConsulta(Paciente p) {
        Faker f = new Faker(new Locale("pt-BR"));
        String diagnostico = "Devido aos sintomas " + p.getDescricao() + " o paciente tem " + f.medical().diseaseName();
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
                ", cpf=" + String.format("%011d", cpf) +
                ", idade=" + idade +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
