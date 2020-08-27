package com.machado.persistencia;

import com.machado.dados.Consulta;
import com.machado.dados.Medico;
import com.machado.dados.Paciente;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO implements DAO<Consulta> {

    private static ConsultaDAO instance = null;
    private static MedicoDAO medicoDAO;
    private static PacienteDAO pacienteDAO;

    private final PreparedStatement newId;
    private final PreparedStatement insert;
    private final PreparedStatement delete;
    private final PreparedStatement update;
    private final PreparedStatement select;
    private final PreparedStatement selectAll;

    private ConsultaDAO() throws SQLException {
        newId = conexao.prepareStatement("select nextval('id_consulta')");
        insert = conexao.prepareStatement("insert into consulta values (?, ?, ?, ?, ?, ?)");
        delete = conexao.prepareStatement("delete from consulta where id=?");
        update = conexao.prepareStatement("update consulta set valor=?, data=?, diagnostico=?, cpf_medico=?, cpf_paciente=? where id=?");
        select = conexao.prepareStatement("select * from consulta where id=?");
        selectAll = conexao.prepareStatement("select * from consulta");

        medicoDAO = MedicoDAO.getInstance();
        pacienteDAO = PacienteDAO.getInstance();
    }

    public static ConsultaDAO getInstance() throws SQLException {
        if (instance == null) instance = new ConsultaDAO();
        return instance;
    }

    private int getNextId() throws SQLException {
        ResultSet rs = newId.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public void insert(Consulta c) throws SQLException {
        c.setId(getNextId());

        insert.setLong(1,c.getId());
        insert.setFloat(2, c.getValor());
        insert.setDate(3, Date.valueOf(c.getDate()));
        insert.setString(4, c.getDiagnostico());
        insert.setLong(5, c.getMedico().getCpf());
        insert.setLong(6, c.getPaciente().getCpf());
        insert.executeUpdate();
    }

    @Override
    public void delete(Consulta c) throws SQLException {
        delete.setLong(1, c.getId());
        delete.executeUpdate();
    }

    @Override
    public void update(Consulta c) throws SQLException {
        update.setLong(6, c.getId());
        update.setFloat(1, c.getValor());
        update.setDate(2, Date.valueOf(c.getDate()));
        update.setString(3, c.getDiagnostico());
        update.setLong(4, c.getMedico().getCpf());
        update.setLong(5, c.getPaciente().getCpf());
        update.executeUpdate();
    }

    @Override
    public Consulta select(long cpf) throws SQLException {
        select.setLong(1, cpf);
        ResultSet rs = select.executeQuery();
        if (rs.next()) {
            Medico m = medicoDAO.select(rs.getLong(5));
            Paciente p = pacienteDAO.select(rs.getLong(6));
            return new Consulta(rs.getInt(1), rs.getFloat(2), rs.getDate(3).toLocalDate(), rs.getString(4), p, m);
        }
        return null;
    }

    @Override
    public List<Consulta> selectAll() throws SQLException {
        List<Consulta> list = new ArrayList<>();
        ResultSet rs = selectAll.executeQuery();
        while (rs.next()){
            Medico m = medicoDAO.select(rs.getLong(5));
            Paciente p = pacienteDAO.select(rs.getLong(6));
            list.add(new Consulta(rs.getInt(1), rs.getFloat(2), rs.getDate(3).toLocalDate(), rs.getString(4), p, m));
        }
        return list;
    }
}
