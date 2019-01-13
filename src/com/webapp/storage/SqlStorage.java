package com.webapp.storage;

import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;
import com.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {

    private SqlHelper sqlHelper;

    public SqlStorage(String url, String user, String password) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(url, user, password));
    }

    @Override
    public void clear() {
        sqlHelper.sqlAction("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume r) {
        sqlHelper.sqlAction("INSERT INTO resume (uuid, full_name) VALUES (?,?)", preparedStatement -> {
            preparedStatement.setString(1, r.getUuid());
            preparedStatement.setString(2, r.getFullName());
            return preparedStatement.execute();
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.sqlAction("SELECT * FROM resume r WHERE r.uuid =?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, resultSet.getString("full_name"));
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.sqlAction("UPDATE resume SET full_name =? WHERE uuid =?", preparedStatement -> {
            preparedStatement.setString(1, r.getFullName());
            preparedStatement.setString(2, r.getUuid());
            if (preparedStatement.executeUpdate() == 0) throw new NotExistStorageException(r.getUuid());
            return true;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.sqlAction("DELETE FROM resume WHERE uuid =?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) throw new NotExistStorageException(uuid);
            return true;
        });

    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.sqlAction("SELECT * FROM resume ORDER BY full_name, uuid", preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Resume> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Resume(resultSet.getString("uuid"), resultSet.getString("full_name")));
            }
            return result;
        });
    }

    @Override
    public int size() {
        return sqlHelper.sqlAction("SELECT COUNT(*) FROM resume", preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        });
    }
}
