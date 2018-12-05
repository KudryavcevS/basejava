package storage;

import org.junit.Ignore;
import org.junit.Test;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    static {
        storage = new ObjectStreamStorage(STORAGEDIR);
    }

}
