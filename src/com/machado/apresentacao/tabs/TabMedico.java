package com.machado.apresentacao.tabs;

import com.machado.apresentacao.dialogs.DialogCadastroMedico;
import com.machado.dados.Medico;
import com.machado.negocio.Sistema;

public class TabMedico extends Tab<Medico> {

    public TabMedico(Sistema<Medico> sistema, String title) {
        super(sistema, title);
    }

    @Override
    protected Medico novoContato() {
        DialogCadastroMedico dcm = new DialogCadastroMedico();
        dcm.showDialog();
        return dcm.getDado();
    }
}
