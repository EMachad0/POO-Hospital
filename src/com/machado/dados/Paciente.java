package com.machado.dados;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Paciente extends Pessoa {

    private String descricao;

    public Paciente(long cpf, String nome, short idade, String cidade, String descricao) {
        super(cpf, nome, idade, cidade);
        this.descricao = descricao;
    }

    public Paciente() {
        super();
        Faker f = new Faker(new Locale("pt-BR"));
        descricao = f.medical().symptoms();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", cpf=" + cpf +
                ", idade=" + idade +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
