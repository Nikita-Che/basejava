package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Массив резюме переполнен");
            return;
        } else if (getSearchKey(r.getUuid()) >= 0) {
            return;
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        storage[getSearchKey(r.getUuid())] = r;
    }

    public Resume get(String uuid) {
        if (getSearchKey(uuid) == -1) {
            System.out.println("Такое резюме " + uuid + " НЕ существует");
        } else {
            return storage[getSearchKey(uuid)];
        }

        return null;
    }

    public void delete(String uuid) {
        if (getSearchKey(uuid) == -1) {
            System.out.println("Такое резюме " + uuid + " НЕ существует");
        } else {
            storage[getSearchKey(uuid)]=storage[size-1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = Arrays.copyOf(storage, size);
        return resumes;
    }

    public int size() {
        return size;
    }

    private int getSearchKey(String uuid) {
        int searchKey = -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                searchKey = i;
                break;
            }
        }

        return searchKey;
    }
}