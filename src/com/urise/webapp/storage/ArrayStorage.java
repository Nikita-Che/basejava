package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        int key = getIndex(r.getUuid());
        if (key != -1) {
            System.out.println("Такое резюме " + r.getUuid() + " Уже существует");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Массив резюме переполнен");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int key = getIndex(r.getUuid());
        if (key == -1) {
            System.out.println("Такое резюме " + r.getUuid() + " НЕ существует");
        } else {
            storage[key] = r;
        }
    }

    public void delete(String uuid) {
        int key = getIndex(uuid);
        if (key == -1) {
            System.out.println("Такое резюме " + uuid + " НЕ существует");
        } else {
            storage[key] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}