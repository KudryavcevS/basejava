package com.webapp.storage;

import com.webapp.storage.serializable.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    static {
        storage = new PathStorage(STORAGEDIR.getAbsolutePath(), new DataStreamSerializer());
    }
}
