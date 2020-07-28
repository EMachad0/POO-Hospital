package com.machado.dados;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public abstract class Pessoa {

    protected final long cpf;
    protected String nome;
    protected short idade;
    protected String cidade;

    public Pessoa(long cpf, String nome, short idade, String cidade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
    }

    public Pessoa() {
        Random r = new Random();
        Faker f = new Faker(new Locale("pt-BR"));
        this.cpf = r.nextInt((int) 1e12);
        this.nome = f.name().fullName();
        this.idade = (short) r.nextInt(151);
        this.cidade = f.address().city();
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public short getIdade() {
        return idade;
    }

    public void setIdade(short idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


}
