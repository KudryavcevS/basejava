package storage;

import storage.serializable.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {
    static {
        storage = new PathStorage(STORAGEDIR, new ObjectStreamSerializer());
    }
}

