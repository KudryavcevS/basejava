package storage;

import exception.StorageException;
import model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private Path storage;

    public AbstractPathStorage(String dir) {
        storage = Paths.get(dir);
        Objects.requireNonNull(dir, "directory is null");
        if (!Files.isDirectory(storage))
            throw new IllegalArgumentException(storage.toAbsolutePath() + "is not directory");
        if (!Files.isReadable(storage) || !Files.isWritable(storage))
            throw new IllegalArgumentException(storage.toAbsolutePath() + "is not (read/write)able");

    }

    protected abstract void doWrite(Resume r, OutputStream outputStream) throws IOException;

    protected abstract Resume doRead(InputStream inputStream) throws IOException;

    @Override
    public void clear() {
        try {
            Files.list(storage).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot delete path", e);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(storage).count();
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot read directory: ", e);
        }
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> result = new ArrayList<>();
        try {
            Files.list(storage).forEach(path -> result.add(doGet(path)));
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot read directory: " + storage.toAbsolutePath(), e);
        }
        return result;
    }

    @Override
    protected boolean isExist(Path indexKey) {
        return Files.isRegularFile(indexKey);
    }

    @Override
    protected Path getIndexKey(String uuid) {
        return storage.resolve(uuid);
    }

    @Override
    protected void doSave(Resume r, Path indexKey) {
        try {
            Files.createFile(indexKey);
            doWrite(r, new BufferedOutputStream(new FileOutputStream(indexKey.toFile())));
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot write file" + indexKey, indexKey.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path indexKey) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(indexKey.toFile())));
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot read file", indexKey.getFileName().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, Path indexKey) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(indexKey.toFile())));
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot write file" , r.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path indexKey) {
        try {
            Files.delete(indexKey);
        } catch (IOException e) {
            throw new StorageException("ERROR: cannot delete file", indexKey.getFileName().toString(), e);
        }
    }
}
