package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void insertResume(Resume r, int indexOfPosition) {
        int index = -indexOfPosition - 1;

        if (storage[index] != null) {
            if (size + 1 >= 0) System.arraycopy(storage, 0, storage, 1, size + 1);
        }
        storage[index] = r;
    }

    @Override
    public void deleteResume(int indexOfPosition) {
        if (size - indexOfPosition >= 0)
            System.arraycopy(storage, indexOfPosition + 1, storage, indexOfPosition, size - indexOfPosition);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
