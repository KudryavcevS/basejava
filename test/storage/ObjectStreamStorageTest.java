package storage;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    static {
        storage = new ObjectStreamStorage(new File(STORAGEDIR));
    }

}
