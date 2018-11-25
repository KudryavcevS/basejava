package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int SIZE_LIMIT = 10000;

    protected Resume[] storage = new Resume[SIZE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
    @Override
    public void doSave(Resume r, Object indexKey) {
        if (size == SIZE_LIMIT) throw new StorageException("Storage is full", r.getUuid());
        else {
            insertElem(r, (int)indexKey);
            size++;
        }
    }

    protected abstract void insertElem(Resume r, int index);
    @Override
    public Resume doGet(Object indexKey) {
        return storage[(int)indexKey];
    }
    @Override
    public void doUpdate(Resume r, Object indexKey) {
        storage[(int)indexKey] = r;
    }
    @Override
    public void doDelete(Object indexKey) {
        deleteElem((int)indexKey);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void deleteElem(int index);

    public int size() {
        return size;
    }
    @Override
    protected boolean isExist(Object indexKey){
        int index = (int) indexKey;
        return index >= 0;
    }
}
