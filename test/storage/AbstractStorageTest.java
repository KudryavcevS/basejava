package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

public abstract class AbstractStorageTest {

    protected static Storage storage;
    protected static final String STORAGEDIR = "C:\\Users\\matt\\basejava\\src\\storage\\storageDir";

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume RESUME_1 = new Resume(UUID_1, "name1");
    private static final Resume RESUME_2 = new Resume(UUID_2, "name2");
    private static final Resume RESUME_3 = new Resume(UUID_3, "name3");

    static {
        RESUME_1.addContact(ContactType.PHONE, "phone from resume1");
        RESUME_1.addContact(ContactType.MAIL, "mail from resume1");
        RESUME_1.addSections(SectionType.PERSONAL, new SectionText("personal from resume1"));
        RESUME_1.addSections(SectionType.ACHIEVEMENT, new SectionList("achievement1 from resume1","achievement2 from resume1"));
        RESUME_1.addSections(SectionType.QUALIFICATIONS, new SectionList("qualification1 from resume1","qualification2 from resume1" ));
        RESUME_1.addSections(SectionType.EDUCATION, new SectionOrg(
                new Organization("org1 from resume1", "url1 from resume1",
                new Organization.Period(LocalDate.of(2007, Month.APRIL, 1), LocalDate.of(2008, Month.JANUARY, 1),"periodName1 from resume1", "desc1 from resume1" ),
                new Organization.Period(LocalDate.of(2008, Month.SEPTEMBER,1), "periodName2 from resume1", "desc2 from resume1" ))));
        RESUME_1.addSections(SectionType.EXPERIENCE, new SectionOrg(
                new Organization("org2 from resume1", "url2 from resume1",
                new Organization.Period(LocalDate.of(2009, Month.APRIL, 1), LocalDate.of(2015, Month.JANUARY, 1),"periodName3 from resume1", null)),
                new Organization("org3 from resume1", "url3 from resume1",
                new Organization.Period(LocalDate.of(2009, Month.APRIL, 1), LocalDate.of(2015, Month.JANUARY, 1),"periodName4 from resume1", null))));

        RESUME_2.addContact(ContactType.PHONE, "phone from resume2");
        RESUME_2.addContact(ContactType.MAIL, "mail from resume2");
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
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