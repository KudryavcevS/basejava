package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void doSave(Resume r, Integer indexKey) {
        storage.add(r);
    }

    @Override
    public Resume doGet(Integer indexKey) {
        return storage.get(indexKey);
    }

    @Override
    public void doUpdate(Resume r, Integer indexKey) {
        storage.set(indexKey, r);
    }

    @Override
    public void doDelete(Integer indexKey) {
        storage.remove((int) indexKey);
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected boolean isExist(Integer indexKey) {
        int index = indexKey;
        return index >= 0;
    }

    @Override
    protected Integer getIndexKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) return i;
        }
        return -1;
    }

}
