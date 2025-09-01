package edu.tudai.arq.dao;

import java.sql.Connection;

public abstract class AbstractDAO {

    protected final Connection cn;

    protected AbstractDAO(Connection cn) {
        this.cn = cn;
    }

    protected abstract void createTableIfNotExists();
}
