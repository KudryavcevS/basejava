package storage.serializable;

import exception.StorageException;
import model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements StreamSerializable {

    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            objectOutputStream.writeObject(r);
        }
    }

    public Resume doRead(InputStream inputStream) throws IOException {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            return (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("ERROR: cannot read object", null, e);
        }
    }
}
