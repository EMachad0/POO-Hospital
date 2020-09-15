package com.machado.apresentacao.tabelas;

import com.machado.apresentacao.MyFormatter;
import com.machado.dados.Medico;
import com.machado.negocio.Sistema;

import javax.swing.*;
import java.sql.SQLException;

public class TabelaMedico extends Tabela<Medico> {

    public TabelaMedico(Sistema<Medico> sistema) {
        super(sistema);
    }

    @Override
    protected String[] initColumns() {
        return new String[]{"Cpf", "Nome", "Idade", "Cidade", "Especialidade"};
    }

    @Override
    public void cadastrar(Medico t) throws SQLException {
        sistema.cadastrar(t);
        atualizar();
    }

    @Override
    public void remover(int i) throws SQLException {
        sistema.remover(sistema.get(i).getCpf());
        atualizar();
    }

    @Override
    public Object getValueAt(int i, int j) {
        try {
            Medico m = sistema.get(i);
            if (j == 0) return MyFormatter.formatCpf(m.getCpf());
            if (j == 1) return m.getNome();
            if (j == 2) return m.getIdade();
            if (j == 3) return m.getCidade();
            if (j == 4) return m.getEspecialidade();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int j) {
        String s = (String) o;
        try {
            Medico m = sistema.get(i);
            if (j == 1) m.setNome(s);
            if (j == 2) m.setIdade(Short.parseShort(s));
            if (j == 3) m.setCidade(s);
            if (j == 4) m.setEspecialidade(s);
            sistema.atualizar(m);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
