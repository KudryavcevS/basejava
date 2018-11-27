package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {

    protected static Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1, "name1"));
        storage.save(new Resume(UUID_2, "name2"));
        storage.save(new Resume(UUID_3, "name3"));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        Resume resumeForSave = new Resume("uuid4","save check");
        storage.save(resumeForSave);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(resumeForSave, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_2, "name2"));
    }

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

    @Test
    public void get() {
        Assert.assertEquals(storage.get(UUID_2), new Resume(UUID_2, "name2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("ghost");
    }

    @Test
    public void update() {
        Resume updateResume = new Resume("uuid2", "update");
        storage.update(updateResume);
        Assert.assertEquals(updateResume, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("ghost"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("ghost");
    }

    @Test
    public void getAllSorted() {
        Resume[] expected = {new Resume(UUID_1, "name1"), new Resume(UUID_2, "name2"), new Resume(UUID_3,"name3")};
        Assert.assertArrayEquals(expected, storage.getAllSorted().toArray());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

}