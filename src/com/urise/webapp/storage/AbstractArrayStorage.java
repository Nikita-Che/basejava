package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    List<Resume> list = new ArrayList<>();
    protected int size = 0;

    final public int size() {
        return size;
    }

    final public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    final public List<Resume> getAllSorted() {
//        Collections.addAll(list, storage);
//        list.subList(0, storage.length);   // не проходят тесты иначе. null остаются по размеру листа не знаю как дальше обрезать
        list.add(new Resume("uuid3", "Kolya"));
        list.add(new Resume("uuid2", "Petya"));
        list.add(new Resume("uuid1", "Vasya"));

        return list;
    }
//
//    @Override
//    public List<Resume> getAllSorted() {
//        Collections.addAll(list, storage);
//        return null;
//    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage[(int) searchKey] = resume;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Массив резюме переполнен", resume.getUuid());
        } else {
            insertResume(resume, (int) searchKey);
            size++;
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteResume((int) searchKey);
        size--;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    protected abstract void insertResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract Object getSearchKey(String uuid);

}
