package storage;

import storage.serializable.ObjectStreamSerializer;

import java.io.File;

public class ObjectFileStorageTest extends AbstractStorageTest {
    static {
        storage = new FileStorage(new File(STORAGEDIR), new ObjectStreamSerializer());
    }
}
