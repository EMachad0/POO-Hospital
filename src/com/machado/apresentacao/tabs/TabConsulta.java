package com.machado.apresentacao.tabs;

import com.machado.apresentacao.dialogs.DialogCadastroConsulta;
import com.machado.apresentacao.tabelas.Tabela;
import com.machado.dados.Consulta;

public class TabConsulta extends Tab<Consulta> {

    public TabConsulta(Tabela<Consulta> tabela, String title) {
        super(tabela, title);
    }

    @Override
    protected Consulta novoDado() {
        DialogCadastroConsulta dcc = new DialogCadastroConsulta();
        dcc.showDialog();
        return dcc.getDado();
    }
}
