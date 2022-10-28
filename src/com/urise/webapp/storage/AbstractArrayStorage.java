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
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage[(int) searchKey] = resume;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Массив резюме переполнен", resume.getUuid());
        } else {
            insertResume(resume, (int) searchKey);
            size++;
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteResume((int) searchKey);
        size--;
    }

    protected abstract void insertResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract Object getSearchKey(String uuid);

}
