package storage;

import storage.serializable.JSONStreamSerializer;

public class JSONPathStorageTest extends AbstractStorageTest {
    static {
        storage = new PathStorage(STORAGEDIR, new JSONStreamSerializer());
    }
}
