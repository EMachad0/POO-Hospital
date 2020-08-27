package com.machado.dados;

import java.time.LocalDate;
import java.util.Date;

public class Consulta {

    private int id;
    private float valor;
    private LocalDate date;
    private String diagnostico;

    private final Paciente paciente;
    private final Medico medico;

    public Consulta(int id, float valor, LocalDate date, String diagnostico, Paciente paciente, Medico medico) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }
}
