package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume r) {
        Object indexKey = getNotExistedIndexKey(r.getUuid());
        doSave(r, indexKey);
    }

    public Resume get(String uuid) {
        Object indexKey = getExistedIndexKey(uuid);
        return doGet(indexKey);
    }

    public void update(Resume r) {
        Object indexKey = getExistedIndexKey(r.getUuid());
        doUpdate(r, indexKey);
    }

    public void delete(String uuid) {
        Object indexKey = getExistedIndexKey(uuid);
        doDelete(indexKey);
    }

    protected abstract boolean isExist(Object indexKey);

    protected abstract Object getIndexKey(String uuid);

    protected abstract void doSave(Resume r, Object indexKey);

    protected abstract Resume doGet(Object indexKey);

    protected abstract void doUpdate(Resume r, Object indexKey);

    protected abstract void doDelete(Object indexKey);

    private Object getNotExistedIndexKey(String uuid) {
        Object indexKey = getIndexKey(uuid);
        if (isExist(indexKey)) throw new ExistStorageException(uuid);
        return indexKey;
    }

    private Object getExistedIndexKey(String uuid) {
        Object indexKey = getIndexKey(uuid);
        if (!isExist(indexKey)) throw new NotExistStorageException(uuid);
        return indexKey;
    }

}
