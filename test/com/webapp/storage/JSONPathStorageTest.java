package com.webapp.storage;

import com.webapp.storage.serializable.JSONStreamSerializer;

public class JSONPathStorageTest extends AbstractStorageTest {
    static {
        storage = new PathStorage(STORAGEDIR.getAbsolutePath(), new JSONStreamSerializer());
    }
}
