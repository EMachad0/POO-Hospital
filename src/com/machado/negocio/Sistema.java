package com.machado.negocio;

import com.machado.dados.Dado;
import com.machado.persistencia.DAO;

import java.sql.SQLException;
import java.util.List;

public class Sistema<T extends Dado> {

    protected final DAO<T> dao;

    public Sistema(DAO<T> dao) {
        this.dao = dao;
    }

    public void cadastrar(T t) throws SQLException {
        dao.insert(t);
    }

    public void remover(long i) throws SQLException {
        dao.delete(select(i));
    }

    public T select(long i) throws SQLException {
        return dao.select(i);
    }

    public T get(int i) throws SQLException {
        return getAll().get(i);
    }

    public List<T> getAll() throws SQLException {
        return dao.selectAll();
    }

    public int getSize() throws SQLException {
        return getAll().size();
    }
}
