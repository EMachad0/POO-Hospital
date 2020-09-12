package com.machado.negocio;

import com.machado.dados.Dado;
import com.machado.persistencia.DAO;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public abstract class Sistema<T extends Dado> extends AbstractTableModel {

    private final DAO<T> dao;
    protected final List<T> list;
    protected String[] columns;

    public Sistema(DAO<T> dao) throws SQLException {
        this.dao = dao;
        list = dao.selectAll();

        initColumns();
    }

    abstract void initColumns();

    public void cadastrar(T t) throws SQLException {
        list.add(t);
        atualizar();
        dao.insert(t);
    }

    public void cadastrar(List<T> l) throws SQLException {
        list.addAll(l);
        atualizar();
        for (T t : l) dao.insert(t);
    }

    public void remover(int i) throws SQLException {
        dao.delete(get(i));
        list.remove(i);
        atualizar();
    }

    public T get(int i) {
        return list.get(i);
    }

    public List<T> getAll() {
        return list;
    }

    public void atualizar() {
        super.fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }
}
