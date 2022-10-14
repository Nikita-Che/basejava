package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final int STORAGE_LIMIT = 10000;

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

    final public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Такое резюме " + uuid + " НЕ существует");
            return null;
        }
        return storage[index];
    }

    final public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Такое резюме " + resume.getUuid() + " НЕ существует");
        }
    }

    final public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (size == STORAGE_LIMIT) {
            System.out.println("Массив резюме переполнен");
        } else if (index > 0) {
            System.out.println("Такое резюме " + resume.getUuid() + " Уже существует");
        } else {
            insertResume(resume, index);
            size++;
        }
    }

    final public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Такое резюме " + uuid + " НЕ существует");
        } else {
            deleteResume(index);
            size--;
        }
    }

    protected abstract void insertResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract int getIndex(String uuid);
}
