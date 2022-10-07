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
        if(size==storage.length){
            System.out.println("Массив резюме переполнен");
            return;
        }

        for (int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(r.getUuid())){
                System.out.println("Такое резюме " + r.getUuid() + " уже существует");
                return;
            }
        }

        storage[size] = r;
        size++;
    }

    public void upDate(Resume r) {
        for (int i = 0; i < size; i++) {
            if(!storage[i].getUuid().equals(r.getUuid())){
                System.out.println("Нет такого резюме " + r.getUuid());
            }
        }

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                storage[i].setUuid(storage[i].getUuid() + " updated");
            }
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (!storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }else{
                System.out.println("Нет такого резюме " + uuid);
                return null;
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if(!storage[i].getUuid().equals(uuid)){
                System.out.println("Нет такого резюме " + uuid);
            }
            break;
        }

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
}
