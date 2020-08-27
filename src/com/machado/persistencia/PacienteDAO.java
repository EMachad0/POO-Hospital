package com.machado.persistencia;

import com.machado.dados.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements DAO<Paciente> {

    private static PacienteDAO instance = null;

    private final PreparedStatement insert;
    private final PreparedStatement delete;
    private final PreparedStatement update;
    private final PreparedStatement select;
    private final PreparedStatement selectAll;

    private PacienteDAO() throws SQLException {
        insert = conexao.prepareStatement("insert into paciente values (?, ?, ?, ?, ?)");
        delete = conexao.prepareStatement("delete from paciente where cpf=?");
        update = conexao.prepareStatement("update paciente set nome=?, idade=?, cidade=?, descricao=? where cpf=?");
        select = conexao.prepareStatement("select * from paciente where cpf=?");
        selectAll = conexao.prepareStatement("select * from paciente");
    }

    public static PacienteDAO getInstance() throws SQLException {
        if (instance == null) instance = new PacienteDAO();
        return instance;
    }

    @Override
    public void insert(Paciente p) throws SQLException {
        insert.setLong(1, p.getCpf());
        insert.setString(2, p.getNome());
        insert.setShort(3, p.getIdade());
        insert.setString(4, p.getCidade());
        insert.setString(5, p.getDescricao());
        insert.executeUpdate();
    }

    @Override
    public void delete(Paciente p) throws SQLException {
        delete.setLong(1, p.getCpf());
        delete.executeUpdate();
    }

    @Override
    public void update(Paciente p) throws SQLException {
        update.setLong(5, p.getCpf());
        update.setString(1, p.getNome());
        update.setLong(2, p.getIdade());
        update.setString(3, p.getCidade());
        update.setString(4, p.getDescricao());
        update.executeUpdate();
    }

    @Override
    public Paciente select(long cpf) throws SQLException {
        select.setLong(1, cpf);
        ResultSet rs = select.executeQuery();
        if (rs.next()) {
            return new Paciente(rs.getLong(1), rs.getString(2), rs.getShort(3), rs.getString(4), rs.getString(5));
        }
        return null;
    }

    @Override
    public List<Paciente> selectAll() throws SQLException {
        List<Paciente> list = new ArrayList<>();
        ResultSet rs = selectAll.executeQuery();
        while (rs.next()){
            Paciente p = new Paciente(rs.getLong(1), rs.getString(2), rs.getShort(3), rs.getString(4), rs.getString(5));
            list.add(p);
        }
        return list;
    }
}
