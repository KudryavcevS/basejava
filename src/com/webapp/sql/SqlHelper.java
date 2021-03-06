package com.webapp.sql;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T sqlAction(String psText, SqlHelperExec<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(psText))
        {
            return executor.execute(preparedStatement);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) throw new ExistStorageException(null);
            throw new StorageException(e);
        }
    }

}
