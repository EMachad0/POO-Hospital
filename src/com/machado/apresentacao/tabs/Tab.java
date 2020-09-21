package com.machado.apresentacao.tabs;

import com.machado.apresentacao.tabelas.Tabela;
import com.machado.dados.Dado;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public abstract class Tab<T extends Dado> {

    private JPanel root;
    protected JTable table;
    private JButton btnAdd;
    private JButton btnRmv;

    protected final Tabela<T> tabela;

    public Tab(Tabela<T> tabela, String title) {
        this.tabela = tabela;
        root.setName(title);

        table.setModel(tabela);

        // Estilo da Tabela
        table.getTableHeader().setFont(new Font("Sagoe UI", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
        table.setRowHeight(25);
        table.setSelectionBackground(Color.decode("#e85a5a"));
        table.setSelectionForeground(Color.WHITE);

        btnAdd.addActionListener(actionEvent -> adicionaContato(novoDado()));
        btnRmv.addActionListener(actionEvent -> removeContato());

        // estilo dos botoes
        btnAdd.setFocusPainted(false);
        btnAdd.setBackground(Color.WHITE);
        btnRmv.setFocusPainted(false);
        btnRmv.setBackground(Color.WHITE);
    }

    protected abstract T novoDado();

    protected void adicionaContato(T t) {
        try {
            if (t != null) tabela.cadastrar(t);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void removeContato() {
        if (table.getSelectedRow() != -1) {
            try {
                tabela.remover(table.getSelectedRow());
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
