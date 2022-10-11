package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int key = getIndex(uuid);
        if (key == -1) {
            System.out.println("Такое резюме " + uuid + " НЕ существует");
            return null;
        }
        return storage[key];
    }

    protected abstract int getIndex(String uuid);
}
