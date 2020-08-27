package com.machado.persistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    Connection conexao = Conexao.getConexao();

    void insert(T t) throws SQLException;
    void delete(T t) throws SQLException;
    void update(T t) throws SQLException;
    T select(long i) throws SQLException;
    List<T> selectAll() throws SQLException;
}
