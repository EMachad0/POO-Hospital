package com.machado.negocio;

import com.machado.dados.Consulta;
import com.machado.dados.Medico;
import com.machado.dados.Paciente;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

    private final List<Medico> medicos = new ArrayList<>();
    private final List<Consulta> consultas = new ArrayList<>();
    private final List<Paciente> pacientes = new ArrayList<>();

    public void cadastra(Medico medico) {
        medicos.add(medico);
    }

    public void cadastra(Consulta consulta) {
        consultas.add(consulta);
    }

    public void cadastra(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void remove(Medico medico) {
        medicos.remove(medico);
    }

    public void remove(Consulta consulta) {
        consultas.remove(consulta);
    }

    public void remove(Paciente paciente) {
        pacientes.remove(paciente);
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
}
