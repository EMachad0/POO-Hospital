package com.machado.apresentacao.tabs;

import com.machado.apresentacao.dialogs.DialogCadastroPaciente;
import com.machado.dados.Paciente;
import com.machado.negocio.Sistema;

public class TabPaciente extends Tab<Paciente> {

    public TabPaciente(Sistema<Paciente> sistema, String title) {
        super(sistema, title);
    }

    @Override
    protected Paciente novoContato() {
        DialogCadastroPaciente dcp = new DialogCadastroPaciente();
        dcp.showDialog();
        return dcp.getDado();
    }
}
