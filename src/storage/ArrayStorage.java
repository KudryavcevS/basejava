package storage;

import model.Resume;

import java.util.Arrays;
import java.util.List;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElem(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void deleteElem(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected Integer getIndexKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}