package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void insertResume(Resume r, int indexOfPosition) {
        int index = -indexOfPosition - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    public void deleteResume(int indexOfPosition) {
        System.arraycopy(storage, indexOfPosition + 1, storage, indexOfPosition, size - indexOfPosition);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        if ((Integer) searchKey < 0) {
            return false;
        }
        return true;
    }
}
