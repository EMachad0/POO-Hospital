package com.machado.negocio;

import com.machado.dados.Consulta;
import com.machado.dados.Medico;
import com.machado.dados.Paciente;
import com.machado.persistencia.ConsultaDAO;
import com.machado.persistencia.MedicoDAO;
import com.machado.persistencia.PacienteDAO;

import java.sql.SQLException;
import java.util.List;

public class Sistema {

    private final MedicoDAO medicoDAO;
    private final PacienteDAO pacienteDAO;
    private final ConsultaDAO consultaDAO;

    public Sistema() throws SQLException {
        this.medicoDAO = MedicoDAO.getInstance();
        this.pacienteDAO = PacienteDAO.getInstance();
        this.consultaDAO = ConsultaDAO.getInstance();
    }

    public void cadastra(Medico medico) throws SQLException {
        medicoDAO.insert(medico);
    }

    public void cadastra(Consulta consulta) throws SQLException {
        consultaDAO.insert(consulta);
    }

    public void cadastra(Paciente paciente) throws SQLException {
        pacienteDAO.insert(paciente);
    }

    public void remove(Medico medico) throws SQLException {
        medicoDAO.delete(medico);
    }

    public void remove(Consulta consulta) throws SQLException {
        consultaDAO.delete(consulta);
    }

    public void remove(Paciente paciente) throws SQLException {
        pacienteDAO.delete(paciente);
    }

    public Medico getMedico(long cpf) throws SQLException {
        return medicoDAO.select(cpf);
    }

    public Consulta getConsulta(int id) throws SQLException {
        return consultaDAO.select(id);
    }

    public Paciente getPaciente(long cpf) throws SQLException {
        return pacienteDAO.select(cpf);
    }

    public void update(Medico m) throws SQLException {
        medicoDAO.update(m);
    }

    public void update(Consulta c) throws SQLException {
        consultaDAO.update(c);
    }

    public void update(Paciente p) throws SQLException {
        pacienteDAO.update(p);
    }

    public List<Medico> getMedicos() throws SQLException {
        return medicoDAO.selectAll();
    }

    public List<Consulta> getConsultas() throws SQLException {
        return consultaDAO.selectAll();
    }

    public List<Paciente> getPacientes() throws SQLException {
        return pacienteDAO.selectAll();
    }
}
