package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    //public class AbstractArrayStorageTest {
    private final Storage storage;
//  private final Storage storage = new SortedArrayStorage();

    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        String test = String.valueOf(storage.get("uuid1")) +
                storage.get("uuid2") +
                storage.get("uuid3");

        storage.getAll();
        Assert.assertEquals("uuid1" + "uuid2" + "uuid3", test);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateWithNotExist() throws Exception {
        String uuid = UUID_1 + "updated";
        Resume resume = new Resume(uuid);
        storage.update(resume);
    }

    @Test()
    public void update() throws Exception {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume resume = new Resume("asdas");
        storage.update(resume);
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume());
        Assert.assertEquals(4, storage.size());
    }

    @Ignore
    @Test(expected = ExistStorageException.class) //не понимаю
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void delete() throws Exception {
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("uuid123");
    }

    @Ignore
    @Test()
    public void overFlow() throws Exception {
        try {
            storage.save(new Resume(UUID_1));
            Assert.fail("переполнение произошло раньше времени");
        } catch (StorageException e) {
//            for (int i = 0; i < storage.leght; i++) { // где мне взять?
//
//            }
        }
    }

    @Test
    public void get() throws Exception {
        String test = String.valueOf(storage.get("uuid1"));
        Assert.assertEquals("uuid1", test);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}