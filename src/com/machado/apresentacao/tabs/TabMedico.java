package com.machado.apresentacao.tabs;

import com.machado.apresentacao.dialogs.DialogCadastroMedico;
import com.machado.apresentacao.tabelas.Tabela;
import com.machado.dados.Medico;

public class TabMedico extends Tab<Medico> {

    public TabMedico(Tabela<Medico> tabela, String title) {
        super(tabela, title);
    }

    @Override
    protected Medico novoContato() {
        DialogCadastroMedico dcm = new DialogCadastroMedico();
        dcm.showDialog();
        return dcm.getDado();
    }
}
