package com.machado.dados;

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
