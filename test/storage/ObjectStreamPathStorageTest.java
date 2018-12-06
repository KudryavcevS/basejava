package storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    static {
        storage = new ObjectStreamPathStorage(STORAGEDIR);
    }
}
