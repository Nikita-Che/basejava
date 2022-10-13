package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void insertResume(Resume r, int indexOfPosition) {
        int index = -indexOfPosition - 1;

        if (storage[index] != null) {
            for (int i = size; i >= 0; i--) {
                storage[i + 1] = storage[i];
            }
            storage[index] = r;
            size++;
        } else {
            storage[index] = r;
            size++;
        }
    }

    @Override
    public void deleteResume(int indexOfPosition) {
        for (int i = indexOfPosition; i < size; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
