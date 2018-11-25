package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

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
    public void doSave(Resume r, Object indexKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public Resume doGet(Object indexKey) {
        return storage.get(indexKey);
    }

    @Override
    public void doUpdate(Resume r, Object indexkey) {
        storage.replace((String) indexkey, r);
    }

    @Override
    public void doDelete(Object indexKey) {
        storage.remove(indexKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>(storage.values());
        result.sort(Resume::compareTo);
        return result;
    }

    @Override
    protected boolean isExist(Object indexKey) {
        return indexKey != null;
    }

    @Override
    protected Object getIndexKey(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) return entry.getKey();
        }
        return null;
    }

}
