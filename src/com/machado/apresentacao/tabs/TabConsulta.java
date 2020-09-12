package com.machado.apresentacao.tabs;

import com.machado.apresentacao.dialogs.DialogCadastroConsulta;
import com.machado.dados.Consulta;
import com.machado.negocio.Sistema;

public class TabConsulta extends Tab<Consulta> {

    public TabConsulta(Sistema<Consulta> sistema, String title) {
        super(sistema, title);
    }

    @Override
    protected Consulta novoContato() {
        DialogCadastroConsulta dcc = new DialogCadastroConsulta();
        dcc.showDialog();
        return dcc.getDado();
    }
}
