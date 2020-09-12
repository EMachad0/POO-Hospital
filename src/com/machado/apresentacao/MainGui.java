package com.machado.apresentacao;

import com.machado.apresentacao.tabs.Tab;
import com.machado.apresentacao.tabs.TabConsulta;
import com.machado.apresentacao.tabs.TabMedico;
import com.machado.apresentacao.tabs.TabPaciente;
import com.machado.dados.Consulta;
import com.machado.dados.Medico;
import com.machado.dados.Paciente;
import com.machado.negocio.SistemaConsulta;
import com.machado.negocio.SistemaMedico;
import com.machado.negocio.SistemaPaciente;
import com.machado.persistencia.Conexao;
import com.machado.persistencia.ConsultaDAO;
import com.machado.persistencia.MedicoDAO;
import com.machado.persistencia.PacienteDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainGui {

    private JPanel root;
    private JTabbedPane tabbedPane;

    public MainGui() {
        try {
            Tab<Medico> tm = new TabMedico(new SistemaMedico(MedicoDAO.getInstance()), "Medicos");
            tabbedPane.add(tm.getRoot());
            Tab<Paciente> tp = new TabPaciente(new SistemaPaciente(PacienteDAO.getInstance()), "Pacientes");
            tabbedPane.add(tp.getRoot());
            Tab<Consulta> tc = new TabConsulta(new SistemaConsulta(ConsultaDAO.getInstance()), "Consultas");
            tabbedPane.add(tc.getRoot());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getRoot() {
        return root;
    }

    public static void main(String[] args) {
        Conexao.setSenha("eliton");

        JFrame frame = new JFrame("Lista telefonica");
        frame.setContentPane(new MainGui().getRoot());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
}
