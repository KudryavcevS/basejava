package storage;

import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class AbstractArrayStorageTest extends AbstractStorageTest {

    @Test(expected = StorageException.class)
    public void saveIsFull() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.SIZE_LIMIT; i++) {
                storage.save(new Resume("ghost" + i));
            }
        } catch (Exception e) {
            Assert.fail();
        }
        storage.save(new Resume("saveIsFull"));
    }
}
