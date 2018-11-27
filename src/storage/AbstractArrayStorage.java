package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int SIZE_LIMIT = 10000;

    protected Resume[] storage = new Resume[SIZE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public void doSave(Resume r, Integer indexKey) {
        if (size == SIZE_LIMIT) throw new StorageException("Storage is full", r.getUuid());
        else {
            insertElem(r, indexKey);
            size++;
        }
    }

    @Override
    public Resume doGet(Integer indexKey) {
        return storage[indexKey];
    }

    @Override
    public void doUpdate(Resume r, Integer indexKey) {
        storage[indexKey] = r;
    }

    @Override
    public void doDelete(Integer indexKey) {
        deleteElem(indexKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Integer indexKey) {
        int index = indexKey;
        return index >= 0;
    }

    protected abstract void insertElem(Resume r, int index);

    protected abstract void deleteElem(int index);

    @Override
    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }
}
