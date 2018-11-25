package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doSave(Resume r, Object indexKey) {
        storage.add(r);
    }

    @Override
    protected boolean isExist(Object indexKey) {
        int index = (int) indexKey;
        return index >= 0;
    }

    @Override
    protected Object getIndexKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    @Override
    public Resume doGet(Object indexKey) {
        return storage.get((int) indexKey);
    }

    @Override
    public void doUpdate(Resume r, Object indexKey) {
        storage.add((int) indexKey, r);
    }

    @Override
    public void doDelete(Object indexKey) {
        storage.remove((int)indexKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        storage.sort(Resume::compareTo);
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
