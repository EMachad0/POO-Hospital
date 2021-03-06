package com.machado.apresentacao.dialogs;

import com.machado.dados.Paciente;

import javax.swing.*;

public class DialogCadastroPaciente extends DialogCadastro<Paciente> {

    private Input input1;
    private Input input2;
    private Input input3;
    private Input input4;
    private Input input5;

    @Override
    protected void initInputs() {
        input1 = novoInput("Cpf");
        input2 = novoInput("Nome");
        input3 = novoInput("Idade");
        input4 = novoInput("Cidade");
        input5 = novoInput("Descrição");
    }

    protected void onOK() {
        try {
            long cpf = Long.parseLong(input1.getText());
            String nome = input2.getText();
            short idade = Short.parseShort(input3.getText());
            String cidade = input4.getText();
            String descricao = input5.getText();
            dado = new Paciente(cpf, nome, idade, cidade, descricao);
            dispose();
        } catch (NumberFormatException e) {
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
