package storage;

import exception.StorageException;
import model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File>  {

    private File storage;

    public AbstractFileStorage(File dir){
        Objects.requireNonNull(dir, "directory is null");
        if (!dir.isDirectory()) throw new IllegalArgumentException(dir.getPath() + "is not directory");
        if (!dir.canRead() || !dir.canWrite()) throw new IllegalArgumentException(dir.getPath() + "is not (read/write)able");
        storage = dir;
    }

    protected abstract void doWrite(Resume r, OutputStream outputStream) throws IOException;

    protected abstract Resume doRead(InputStream inputStream) throws IOException;

    @Override
    public void clear() {
        File[] files = storage.listFiles();
        if (files != null) {
            for (File file : files) doDelete(file);
        }
    }

    @Override
    public int size() {
        String[] fileNames = storage.list();
        if (fileNames != null) {
            return fileNames.length;
        }
        throw new StorageException("ERROR: cannot get file names from directory: ");
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> result = new ArrayList<>();
        File[] files = storage.listFiles();
        if (files == null) throw new StorageException("ERROR: cannot get files from directory: ");
        for (File file : files) result.add(doGet(file));
        return result;
    }

    @Override
    protected boolean isExist(File indexKey) {
        return indexKey.exists();
    }

    @Override
    protected File getIndexKey(String uuid) {
        return new File(storage, uuid);
    }

    @Override
    protected void doSave(Resume r, File indexKey) {
        try {
            if (!indexKey.createNewFile()) throw new StorageException("ERROR: cannot create file" + indexKey.getPath(), indexKey.getName());
            doWrite(r, new BufferedOutputStream(new FileOutputStream(indexKey)));
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot write file" + indexKey.getPath(), indexKey.getName(), e);
        }
    }
 
    @Override
    protected Resume doGet(File indexKey) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(indexKey)));
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot read file", indexKey.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, File indexKey) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(indexKey)));
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot write file" + indexKey.getPath(), indexKey.getName(), e);
        }
    }

    @Override
    protected void doDelete(File indexKey) {
        if (!indexKey.delete()) throw new StorageException("ERROR: cannot delete file", indexKey.getName());
    }
}
