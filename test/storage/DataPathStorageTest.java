package storage;

import storage.serializable.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    static {
        storage = new PathStorage(STORAGEDIR, new DataStreamSerializer());
    }
}
