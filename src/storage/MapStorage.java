package storage;

import model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public Resume get(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void update(Resume r) {
        storage.replace(r.getUuid(),r);
    }

    @Override
    public void delete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] result = new Resume[storage.size()];
        int i = 0;
        for (Map.Entry<String,Resume> entry : storage.entrySet() ){
            result[i++] = entry.getValue();
        }
        return result;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
