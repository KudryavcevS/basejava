package storage;

import storage.serializable.XMLStreamSerializer;

public class XMLPathStorageTest extends AbstractStorageTest {
    static {
        storage = new PathStorage(STORAGEDIR, new XMLStreamSerializer());
    }
}
