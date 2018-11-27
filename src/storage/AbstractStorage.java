package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<IK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract List<Resume> getAll();

    protected abstract boolean isExist(IK indexKey);

    protected abstract IK getIndexKey(String uuid);

    protected abstract void doSave(Resume r, IK indexKey);

    protected abstract Resume doGet(IK indexKey);

    protected abstract void doUpdate(Resume r, IK indexKey);

    protected abstract void doDelete(IK indexKey);

    @Override
    public void save(Resume r) {
        IK indexKey = getNotExistedIndexKey(r.getUuid());
        doSave(r, indexKey);
    }

    @Override
    public Resume get(String uuid) {
        IK indexKey = getExistedIndexKey(uuid);
        return doGet(indexKey);
    }

    @Override
    public void update(Resume r) {
        IK indexKey = getExistedIndexKey(r.getUuid());
        doUpdate(r, indexKey);
    }

    @Override
    public void delete(String uuid) {
        IK indexKey = getExistedIndexKey(uuid);
        doDelete(indexKey);
    }

    @Override
    public List<Resume> getAllSorted(){
        List<Resume> result = getAll();
        Collections.sort(result);
        return result;
    }

    private IK getNotExistedIndexKey(String uuid) {
        IK indexKey = getIndexKey(uuid);
        if (isExist(indexKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return indexKey;
    }

    private IK getExistedIndexKey(String uuid) {
        IK indexKey = getIndexKey(uuid);
        if (!isExist(indexKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return indexKey;
    }

}
