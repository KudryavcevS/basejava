package storage;

import model.Resume;

import java.util.Arrays;


public interface Storage {

    void clear();

    void save(Resume r);

    Resume get(String uuid);

    void update(Resume r);

    void delete(String uuid);

    Resume[] getAll();

    int size();

}