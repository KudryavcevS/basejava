package com.webapp.storage;

import com.webapp.MainConfig;

public class SQLStorageTest extends AbstractStorageTest {
    static {
        storage = MainConfig.get().getStorage();
    }
}
