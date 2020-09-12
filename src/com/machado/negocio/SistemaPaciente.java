package com.machado.negocio;

import com.machado.apresentacao.MyFormatter;
import com.machado.dados.Paciente;
import com.machado.persistencia.DAO;

import java.sql.SQLException;

public class SistemaPaciente extends Sistema<Paciente> {

    public SistemaPaciente(DAO<Paciente> dao) throws SQLException {
        super(dao);
    }

    @Override
    void initColumns() {
        columns = new String[]{"Cpf", "Nome", "Idade", "Cidade", "Descrição"};
    }

    @Override
    public Object getValueAt(int i, int j) {
        Paciente p = list.get(i);
        if (j == 0) return MyFormatter.formatCpf(p.getCpf());
        if (j == 1) return p.getNome();
        if (j == 2) return p.getIdade();
        if (j == 3) return p.getCidade();
        if (j == 4) return p.getDescricao();
        return null;
    }
}
