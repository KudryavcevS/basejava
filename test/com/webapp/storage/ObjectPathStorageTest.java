package com.webapp.storage;

import com.webapp.storage.serializable.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {
    static {
        storage = new PathStorage(STORAGEDIR.getAbsolutePath(), new ObjectStreamSerializer());
    }
}

