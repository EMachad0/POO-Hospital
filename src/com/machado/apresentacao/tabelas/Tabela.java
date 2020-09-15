package com.machado.apresentacao.tabelas;

import com.machado.dados.Dado;
import com.machado.negocio.Sistema;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;

public abstract class Tabela<T extends Dado> extends AbstractTableModel {

    protected String[] columns;
    protected Sistema<T> sistema;

    public Tabela(Sistema<T> sistema) {
        this.sistema = sistema;
        columns = initColumns();
    }

    protected abstract String[] initColumns();

    public abstract void cadastrar(T t) throws SQLException;
    public abstract void remover(int i) throws SQLException;

    public void atualizar() {
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.getSize();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    @Override
    public boolean isCellEditable(int i, int j) {
        return j > 0;
    }
}
