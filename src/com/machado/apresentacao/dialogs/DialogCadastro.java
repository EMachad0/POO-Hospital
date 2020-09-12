package com.machado.apresentacao.dialogs;

import com.machado.dados.Dado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class DialogCadastro<T extends Dado> extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    protected JPanel inputPane;

    protected T dado;

    public DialogCadastro() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonOK.setFocusPainted(false);
        buttonOK.setBackground(Color.WHITE);

        buttonCancel.setFocusPainted(false);
        buttonCancel.setBackground(Color.WHITE);

        initInputs();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public void showDialog() {
        pack();
        setVisible(true);
    }

    protected abstract void initInputs();

    protected Input novoInput(String label) {
        Input in = new Input();
        in.setText(label);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;

        inputPane.add(in.getRoot(), c);
        return in;
    }

    protected abstract void onOK();

    protected void onCancel() {
        dispose();
    }

    public T getDado() {
        return dado;
    }
}
