package storage;

import model.Resume;
import java.util.Arrays;


public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElem(Resume r, int index) {
        storage[index] = r;
        size++;
    }

    @Override
    protected void deleteElem(int index) {
        storage[index] = storage[size - 1];
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}