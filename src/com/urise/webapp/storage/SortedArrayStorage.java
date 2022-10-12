package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int indexOfPosition = -getIndex(r.getUuid()) - 1;  //определяет индекс местоназначения

        if (storage[indexOfPosition] != null) {
            for (int i = size; i >= 0; i--) {
                storage[i + 1] = storage[i];
            }
            storage[indexOfPosition] = r;
            size++;
        } else {
            storage[indexOfPosition] = r;
            size++;
        }
    }

    @Override
    public void update(Resume r) {
        int indexOfPosition = getIndex(r.getUuid());
        storage[indexOfPosition] = r;
    }

    @Override
    public void delete(String uuid) {
        int indexOfPosition = getIndex(uuid);
        for (int i = indexOfPosition; i < size; i++) {
            storage[i] = storage[i + 1];
        }
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
