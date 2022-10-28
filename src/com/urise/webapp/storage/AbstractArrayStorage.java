package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    final public int size() {
        return size;
    }

    final public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    final public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected Resume doGet(String uuid) {
        return storage[(int) getSearchKey(uuid)];
    }

    @Override
    protected void doUpdate(Resume resume) {
        storage[(int) getSearchKey(resume.getUuid())] = resume;
    }

    @Override
    protected void doSave(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Массив резюме переполнен", resume.getUuid());
        } else {
            insertResume(resume, (int) getSearchKey(resume.getUuid()));
            size++;
        }
    }

    @Override
    protected void doDelete(String uuid) {
        deleteResume((int) getSearchKey(uuid));
        size--;
    }

    protected abstract void insertResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract Object getSearchKey(String uuid);

}
