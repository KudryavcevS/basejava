package com.webapp.storage;

import com.webapp.storage.serializable.ObjectStreamSerializer;

import java.io.File;

public class ObjectFileStorageTest extends AbstractStorageTest {
    static {
        storage = new FileStorage(STORAGEDIR, new ObjectStreamSerializer());
    }
}
