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
    protected Object getIndexKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Resume> getAllSorted() {
        Arrays.sort(storage);
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }
}