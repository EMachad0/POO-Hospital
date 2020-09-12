package com.machado.negocio;

import com.machado.apresentacao.MyFormatter;
import com.machado.dados.Medico;
import com.machado.persistencia.DAO;

import java.sql.SQLException;

public class SistemaMedico extends Sistema<Medico> {

    public SistemaMedico(DAO<Medico> dao) throws SQLException {
        super(dao);
    }

    @Override
    void initColumns() {
        columns = new String[]{"Cpf", "Nome", "Idade", "Cidade", "Especialidade"};
    }

    @Override
    public Object getValueAt(int i, int j) {
        Medico m = list.get(i);
        if (j == 0) return MyFormatter.formatCpf(m.getCpf());
        if (j == 1) return m.getNome();
        if (j == 2) return m.getIdade();
        if (j == 3) return m.getCidade();
        if (j == 4) return m.getEspecialidade();
        return null;
    }
}
