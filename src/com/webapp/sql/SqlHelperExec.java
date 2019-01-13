package com.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface SqlHelperExec<T> {
    T execute(PreparedStatement preparedStatement) throws SQLException;
}
