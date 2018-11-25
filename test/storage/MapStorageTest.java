package storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapStorageTest extends AbstractArrayStorageTest {
    static {
        storage = new MapStorage();
    }

    @Ignore
    @Test
    public void saveIsFull(){
    }
}
