package storage;

import model.Resume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

public class ListStorageTest extends AbstractArrayStorageTest {
    static {
        storage = new ListStorage();
    }

    @Test
    @Ignore
    public void saveIsFull(){
    }
}
