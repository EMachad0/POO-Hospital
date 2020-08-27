package com.machado.dados;

public class Medico extends Pessoa {

    private String especialidade;

    public Medico(long cpf, String nome, short idade, String cidade, String especialidade) {
        super(cpf, nome, idade, cidade);
        this.especialidade = especialidade;
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
