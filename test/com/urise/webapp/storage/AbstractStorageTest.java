package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

//@RunWith(Suite.class)
//@Suite.SuiteClasses({ArrayStorageTest.class,
//        SortedArrayStorageTest.class,
//        ListStorageTest.class,
//        MapStorageTest.class,
//})
//abstract class allTests{
//}

public abstract class AbstractStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);
    private static final Resume RESUME_5 = new Resume(UUID_NOT_EXIST);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    private void assertSize(int expected) {
        Assertions.assertEquals(expected, storage.size());
    }

    private void assertGet(Resume resume) {
        Resume r = storage.get(resume.getUuid());
        Assertions.assertEquals(r.getUuid(), resume.getUuid());
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            List<Resume> list = null;
            storage.clear();
            assertSize(0);
            Assertions.assertEquals(list, storage.getAllSorted());
            assertGet(RESUME_1);
        });
    }

    @Test
    public void getResume() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getResumeNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            assertGet(RESUME_5);
        });
    }

    @Test
    public void getAll() throws Exception {
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertSize(3);
        Assertions.assertEquals(expected, storage.getAllSorted());
    }

    @Test()
    public void updateResume() throws Exception {
        Resume resume = RESUME_1;
        storage.update(resume);
        Assertions.assertSame(resume, RESUME_1);
    }

    @Test
    public void updateNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(RESUME_5);
        });
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void saveExist() throws Exception {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_2);
        });
    }

    @Test
    public void deleteResume() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_1);
            assertSize(2);
            assertGet(RESUME_1);
        });
    }

    @Test
    public void deleteNotExist() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_NOT_EXIST);
        });
    }
}
