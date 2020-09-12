package com.machado.apresentacao.dialogs;

import javax.swing.*;

public class Input {

    private JPanel root;
    private JLabel label;
    private JTextField textField;

    public void setText(String s) {
        label.setText(s);
    }

    public String getText() {
        return textField.getText();
    }

    public JPanel getRoot() {
        return root;
    }
}
