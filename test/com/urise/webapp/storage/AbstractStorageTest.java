package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.urise.webapp.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public abstract class AbstractStorageTest {
    private final Storage storage;
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
        assertEquals(expected, storage.size());
    }

    private void assertGet(Resume resume) {
        Resume r = storage.get(resume.getUuid());
        assertEquals(r.getUuid(), resume.getUuid());
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            List<Resume> list = new ArrayList<>();
            storage.clear();
            assertSize(0);
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
        expected.sort(AbstractStorage.comparator);
        assertSize(3);
        assertEquals(expected, storage.getAllSorted());
    }

    @Test()
    public void updateResume() throws Exception {
        Resume resume = RESUME_1;
        Map<ContactType, String> contacts = new HashMap<>();
        contacts.put(ContactType.PHONE, "TEST");
        contacts.put(ContactType.MOBILE, "TEST");
        contacts.put(ContactType.HOME_PHONE, "TEST");
        contacts.put(ContactType.EMAIL, "TEST@rambler.ru");
        contacts.put(ContactType.HOME_PAGE, "http://TEST!!!.ru");
        contacts.put(ContactType.SKYPE, "TEST");
        contacts.put(ContactType.GITHUB, "TEST");
        contacts.put(ContactType.LINKEDIN, "TEST");
        contacts.put(ContactType.STACKOVERFLOW, "TEST");
        resume.addContacts(contacts);
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
