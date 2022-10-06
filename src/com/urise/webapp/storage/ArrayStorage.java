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
        //TODO check if resume NOT Present sout(uuid)
        //TODO check oversize
        storage[size] = r;
        size++;
    }

    public void upDate(Resume r, String newUuid) {
        //TODO check if resume  Present  sout(uuid)
    }

    public Resume get(String uuid) {
        //TODO check if resume Present  sout(uuid)
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        //TODO check if resume  Present  sout(uuid)
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }
    }

//        //deleting element
//        int deletedElementIndex = 0;
//        boolean isDeleted = false;
//        for (int i = 0; i < size; i++) {
//            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
//                storage[i] = null;
//                deletedElementIndex = i;
//                isDeleted = true;
//                size--;
//                break;
//            }
//        }
//        //right shift elements
//        for (int i = deletedElementIndex; i < size; i++) {
//            if (isDeleted) {
//                storage[i] = storage[i + 1];
//            }
//        }

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
}
