package storage;

public class ObjectPathStorageTest extends AbstractStorageTest {
    static {
        storage = new PathStorage(STORAGEDIR, new ObjectStreamSerializer());
    }
}

