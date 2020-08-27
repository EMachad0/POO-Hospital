package com.machado.persistencia;

import com.machado.dados.Medico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO implements DAO<Medico> {

    private static MedicoDAO instance = null;

    private final PreparedStatement insert;
    private final PreparedStatement delete;
    private final PreparedStatement update;
    private final PreparedStatement select;
    private final PreparedStatement selectAll;

    private MedicoDAO() throws SQLException {
        insert = conexao.prepareStatement("insert into medico values (?, ?, ?, ?, ?)");
        delete = conexao.prepareStatement("delete from medico where cpf=?");
        update = conexao.prepareStatement("update medico set nome=?, idade=?, cidade=?, especialidade=? where cpf=?");
        select = conexao.prepareStatement("select * from medico where cpf=?");
        selectAll = conexao.prepareStatement("select * from medico");
    }

    public static MedicoDAO getInstance() throws SQLException {
        if (instance == null) instance = new MedicoDAO();
        return instance;
    }

    @Override
    public void insert(Medico m) throws SQLException {
        insert.setLong(1, m.getCpf());
        insert.setString(2, m.getNome());
        insert.setShort(3, m.getIdade());
        insert.setString(4, m.getCidade());
        insert.setString(5, m.getEspecialidade());
        insert.executeUpdate();
    }

    @Override
    public void delete(Medico m) throws SQLException {
        delete.setLong(1, m.getCpf());
        delete.executeUpdate();
    }

    @Override
    public void update(Medico m) throws SQLException {
        update.setLong(5, m.getCpf());
        update.setString(1, m.getNome());
        update.setLong(2, m.getIdade());
        update.setString(3, m.getCidade());
        update.setString(4, m.getEspecialidade());
        update.executeUpdate();
    }

    @Override
    public Medico select(long cpf) throws SQLException {
        select.setLong(1, cpf);
        ResultSet rs = select.executeQuery();
        if (rs.next()) {
            return new Medico(rs.getLong(1), rs.getString(2), rs.getShort(3), rs.getString(4), rs.getString(5));
        }
        return null;
    }

    @Override
    public List<Medico> selectAll() throws SQLException {
        List<Medico> list = new ArrayList<>();
        ResultSet rs = selectAll.executeQuery();
        while (rs.next()){
            Medico p = new Medico(rs.getLong(1), rs.getString(2), rs.getShort(3), rs.getString(4), rs.getString(5));
            list.add(p);
        }
        return list;
    }
}
