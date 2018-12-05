package storage;

import exception.StorageException;
import model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {

    public ObjectStreamStorage(File dir) {
        super(dir);
    }

    @Override
    protected void doWrite(Resume r, OutputStream outputStream) throws IOException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            objectOutputStream.writeObject(r);
        }

    }

    @Override
    protected Resume doRead(InputStream inputStream) throws IOException {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            return (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("ERROR: cannot read object", null, e);
        }
    }
}
