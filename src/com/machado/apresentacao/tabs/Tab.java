package com.machado.apresentacao.tabs;

import com.machado.dados.Dado;
import com.machado.negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public abstract class Tab<T extends Dado> {

    private JPanel root;
    protected JTable table;
    private JButton btnAdd;
    private JButton btnUpd;
    private JButton btnRmv;

    protected final Sistema<T> sistema;

    public Tab(Sistema<T> sistema, String title) {
        this.sistema = sistema;
        root.setName(title);

        table.setModel(sistema);

        // Estilo da Tabela
        table.getTableHeader().setFont(new Font("Sagoe UI", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
        table.setRowHeight(25);
        table.setSelectionBackground(Color.decode("#e85a5a"));
        table.setSelectionForeground(Color.WHITE);

        btnAdd.addActionListener(actionEvent -> adicionaContato(novoContato()));
        btnUpd.addActionListener(actionEvent -> updateContato());
        btnRmv.addActionListener(actionEvent -> removeContato());

        // estilo dos botoes
        btnAdd.setFocusPainted(false);
        btnAdd.setBackground(Color.WHITE);
        btnUpd.setFocusPainted(false);
        btnUpd.setBackground(Color.WHITE);
        btnRmv.setFocusPainted(false);
        btnRmv.setBackground(Color.WHITE);
    }

    protected abstract T novoContato();

    protected void adicionaContato(T t) {
        try {
            if (t != null) sistema.cadastrar(t);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void updateContato() {
        if (table.getSelectedRow() != -1) {
            T t = novoContato();
            if (t != null) removeContato();
            adicionaContato(t);
        }
    }

    protected void removeContato() {
        if (table.getSelectedRow() != -1) {
            try {
                sistema.remover(table.getSelectedRow());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public JPanel getRoot() {
        return root;
    }
}
