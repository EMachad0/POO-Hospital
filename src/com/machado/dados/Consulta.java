package com.machado.dados;

import java.util.Date;

public class Consulta {

    private final int id;
    private float valor;
    private Date date;
    private String diagnostico;

    private final Paciente paciente;
    private final Medico medico;

    public Consulta(int id, float valor, Date date, String diagnostico, Paciente paciente, Medico medico) {
        this.id = id;
        this.valor = valor;
        this.date = date;
        this.diagnostico = diagnostico;
        this.paciente = paciente;
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "Consulta de id " + id + ":\n" +
                "O paciente " + paciente.getNome() + " foi atendido pelo médico " + medico.getNome() + " na data " + date + "\n" +
                diagnostico + "\n" +
                "Custando " + String.format("R$%.2f", valor);
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
