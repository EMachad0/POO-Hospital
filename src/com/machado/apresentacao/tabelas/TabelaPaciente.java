package com.machado.apresentacao.tabelas;

import com.machado.apresentacao.MyFormatter;
import com.machado.dados.Paciente;
import com.machado.negocio.Sistema;

import javax.swing.*;
import java.sql.SQLException;

public class TabelaPaciente extends Tabela<Paciente> {

    public TabelaPaciente(Sistema<Paciente> sistema) {
        super(sistema);
    }

    @Override
    protected String[] initColumns() {
        return new String[]{"Cpf", "Nome", "Idade", "Cidade", "Descrição"};
    }

    @Override
    public void cadastrar(Paciente t) throws SQLException {
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
            Paciente p = sistema.get(i);
            if (j == 0) return MyFormatter.formatCpf(p.getCpf());
            if (j == 1) return p.getNome();
            if (j == 2) return p.getIdade();
            if (j == 3) return p.getCidade();
            if (j == 4) return p.getDescricao();
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
            Paciente p = sistema.get(i);
            if (j == 1) p.setNome(s);
            if (j == 2) p.setIdade(Short.parseShort(s));
            if (j == 3) p.setCidade(s);
            if (j == 4) p.setDescricao(s);
            sistema.atualizar(p);
            atualizar();
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
