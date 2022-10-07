package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (isOverSizeStorage()) return;

        if (isResumePresented(r)) return;

        storage[size] = r;
        size++;
    }

    public void upDate(Resume r) {
        if (isResumeNotPresented(r)) return;

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                storage[i].setUuid(storage[i].getUuid() + " updated");
            }
        }
    }

    public Resume get(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);

        if(isResumeNotPresented(resume)) return null;

        return resume;
    }

    public void delete(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        if(isResumeNotPresented(resume)) return;

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
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

    private boolean isResumePresented(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("Такое резюме " + r.getUuid() + " уже существует");
                return true;
            }
        }
        return false;
    }

    private boolean isResumeNotPresented(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                return false;
            }
        }
        System.out.println("Такое резюме " + r.getUuid() + " НЕ существует");
        return true;
    }

    private boolean isOverSizeStorage() {
        if (size == storage.length) {
            System.out.println("Массив резюме переполнен");
            return true;
        }
        return false;
    }
}
