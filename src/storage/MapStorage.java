package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void doSave(Resume r, String indexKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public Resume doGet(String indexKey) {
        return storage.get(indexKey);
    }

    @Override
    public void doUpdate(Resume r, String indexKey) {
        storage.replace(indexKey, r);
    }

    @Override
    public void doDelete(String indexKey) {
        storage.remove(indexKey);
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected boolean isExist(String indexKey) {
        return storage.containsKey(indexKey);
    }

    @Override
    protected String getIndexKey(String uuid) {
        return uuid;
    }

}
