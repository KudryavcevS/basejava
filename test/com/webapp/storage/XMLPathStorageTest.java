package com.webapp.storage;

import com.webapp.storage.serializable.XMLStreamSerializer;

public class XMLPathStorageTest extends AbstractStorageTest {
    static {
        storage = new PathStorage(STORAGEDIR.getAbsolutePath(), new XMLStreamSerializer());
    }
}
