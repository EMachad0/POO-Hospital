package com.machado.negocio;

import com.machado.apresentacao.MyFormatter;
import com.machado.dados.Consulta;
import com.machado.persistencia.DAO;

import java.sql.SQLException;

public class SistemaConsulta extends Sistema<Consulta> {

    public SistemaConsulta(DAO<Consulta> dao) throws SQLException {
        super(dao);
    }

    @Override
    void initColumns() {
        columns = new String[]{"Medico", "Paciente", "Diagnostico", "Data", "Valor"};
    }

    @Override
    public Object getValueAt(int i, int j) {
        Consulta c = list.get(i);
        if (j == 0) return c.getMedico().getNome();
        if (j == 1) return c.getPaciente().getNome();
        if (j == 2) return MyFormatter.formatMoney(c.getValor());
        if (j == 3) return c.getDate();
        if (j == 4) return c.getDiagnostico();
        return null;
    }
}
