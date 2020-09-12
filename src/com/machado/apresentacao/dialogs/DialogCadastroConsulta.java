package com.machado.apresentacao.dialogs;

import com.machado.dados.Consulta;
import com.machado.dados.Medico;
import com.machado.dados.Paciente;
import com.machado.persistencia.MedicoDAO;
import com.machado.persistencia.PacienteDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class DialogCadastroConsulta extends DialogCadastro<Consulta> {

    private Input input1;
    private Input input2;
    private Input input3;
    private Input input4;
    private Input input5;

    @Override
    protected void initInputs() {
        input1 = novoInput("Cpf do Medico");
        input2 = novoInput("Cpf do Paciente");
        input3 = novoInput("Valor");
        input4 = novoInput("Diagnostico");
    }

    protected void onOK() {
        try {
            long cpfMedico = Long.parseLong(input1.getText());
            long cpfPaciente = Long.parseLong(input2.getText());
            float valor = Float.parseFloat(input3.getText());
            String diagnostico = input4.getText();
            Paciente p = PacienteDAO.getInstance().select(cpfPaciente);
            Medico m = MedicoDAO.getInstance().select(cpfMedico);
            dado = new Consulta(valor, LocalDate.now(), diagnostico, p, m);
            dispose();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        DialogCadastroMedico dialog = new DialogCadastroMedico();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
