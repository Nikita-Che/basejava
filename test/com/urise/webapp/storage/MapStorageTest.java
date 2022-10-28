package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapStorageTest {
    private final Storage storage = new MapStorage();

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

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    private void assertSize(int expected) {
        assertEquals(expected, storage.size());
    }

    private void assertGet(Resume resume) {
        Resume r = storage.get(resume.getUuid()); //вот тут r = null
//        Resume r1 = new Resume(resume.getUuid());
        assertEquals(r.getUuid(), resume.getUuid());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void clear() {
        storage.clear();
        assertSize(0);
        assertGet(RESUME_2);
    }

    @Test
    public void getResume() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getResumeNotExist() {
        assertGet(RESUME_5);
    }

    @Test
    public void getAll() {
        Resume[] expected = new Resume[]{RESUME_2, RESUME_1, RESUME_3};
        assertSize(3);
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void updateResume() {
        Resume resume = RESUME_1;
        storage.update(resume);
        assertSame(resume, RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateResumeNoExist() {
        storage.update(RESUME_5);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteResume() {
        storage.delete(UUID_1);
        assertSize(2);
        assertGet(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteResumeNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }
}