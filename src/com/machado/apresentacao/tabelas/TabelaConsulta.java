package com.machado.apresentacao.tabelas;

import com.machado.apresentacao.MyFormatter;
import com.machado.dados.Consulta;
import com.machado.dados.Medico;
import com.machado.negocio.Sistema;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class TabelaConsulta extends Tabela<Consulta> {

    public TabelaConsulta(Sistema<Consulta> sistema) {
        super(sistema);
    }

    @Override
    protected String[] initColumns() {
        return new String[]{"Medico", "Paciente", "Diagnostico", "Data", "Valor"};
    }

    @Override
    public void cadastrar(Consulta t) throws SQLException {
        sistema.cadastrar(t);
        atualizar();
    }

    @Override
    public void remover(int i) throws SQLException {
        sistema.remover(sistema.get(i).getId());
        atualizar();
    }

    @Override
    public Object getValueAt(int i, int j) {
        try {
            Consulta c = sistema.get(i);
            if (j == 0) return c.getMedico().getNome();
            if (j == 1) return c.getPaciente().getNome();
            if (j == 2) return MyFormatter.formatMoney(c.getValor());
            if (j == 3) return c.getDate();
            if (j == 4) return c.getDiagnostico();
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
            Consulta c = sistema.get(i);
            if (j == 1) c.setValor(Float.parseFloat(s));
            if (j == 2) c.setDate(LocalDate.parse(s));
            if (j == 3) c.setDiagnostico(s);
            sistema.atualizar(c);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean isCellEditable(int i, int j) {
        return j > 1;
    }
}
