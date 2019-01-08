package com.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
        SortedArrayStorageTest.class,
        ObjectFileStorageTest.class,
        ObjectPathStorageTest.class,
        XMLPathStorageTest.class,
        JSONPathStorageTest.class,
        DataPathStorageTest.class
})

public class AllStorageTest {
}
