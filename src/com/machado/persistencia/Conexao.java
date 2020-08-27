package com.machado.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static String senha = "";
    private static Connection conexao;

    private Conexao() {}

    public static Connection getConexao() {
        if (conexao == null) {
            String url = "jdbc:postgresql://localhost:5432/hospital";
            String user = "postgres";
            try {
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(url, user, senha);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conexao;
    }

    public static void setSenha(String senha) {
        Conexao.senha = senha;
    }
}
