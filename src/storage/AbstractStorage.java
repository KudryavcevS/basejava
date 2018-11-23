package storage;

import exception.ExistStorageException;
import exception.StorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {



    public abstract void clear();

    public abstract void save(Resume r){
        int index = getIndex(r.getUuid());
        if (index >= 0) throw new ExistStorageException(r.getUuid());
        else if (size == SIZE_LIMIT) throw new StorageException("Storage is full", r.getUuid());
        else {
            insertElem(r, index);
            size++;
        }
    }

    public abstract Resume get(String uuid);

    public abstract void update(Resume r);

    public abstract void delete(String uuid);

    public abstract Resume[] getAll();

    public abstract int size();

}
