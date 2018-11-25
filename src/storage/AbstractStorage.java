package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {


    public void save(Resume r) {
        Object indexKey = getIndexKey(r.getUuid());
        if (isExist(indexKey)) throw new ExistStorageException(r.getUuid());
        else {
            doSave(r, indexKey);
        }
    }

    protected abstract boolean isExist(Object indexKey);

    protected abstract Object getIndexKey(String uuid);

    protected abstract void doSave(Resume r, Object indexKey);


    public Resume get(String uuid) {
        Object indexKey = getIndexKey(uuid);
        if (!isExist(indexKey)) throw new NotExistStorageException(uuid);
        else {
            return doGet(indexKey);
        }
    }

    protected abstract Resume doGet(Object indexKey);

    public void update(Resume r) {
        Object indexKey = getIndexKey(r.getUuid());
        if (!isExist(indexKey)) throw new NotExistStorageException(r.getUuid());
        else {
            doUpdate(r, indexKey);
        }
    }

    protected abstract void doUpdate(Resume r, Object indexKey);

    public void delete(String uuid) {
        Object indexKey = getIndexKey(uuid);
        if (!isExist(indexKey)) throw new NotExistStorageException(uuid);
        else {
            doDelete(indexKey);
        }
    }

    protected abstract void doDelete(Object indexKey);

}
