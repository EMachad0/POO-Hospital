package com.machado.apresentacao.tabs;

import com.machado.apresentacao.dialogs.DialogCadastroPaciente;
import com.machado.apresentacao.tabelas.Tabela;
import com.machado.dados.Paciente;

public class TabPaciente extends Tab<Paciente> {

    public TabPaciente(Tabela<Paciente> tabela, String title) {
        super(tabela, title);
    }

    @Override
    protected Paciente novoDado() {
        DialogCadastroPaciente dcp = new DialogCadastroPaciente();
        dcp.showDialog();
        return dcp.getDado();
    }
}
