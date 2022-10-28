package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[storage.size()];
        resumes = storage.values().toArray(resumes);
        return resumes;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(searchKey);
//        return storage.get(uuid);
    }

    @Override
    protected void doUpdate(Resume resume) {
        storage.computeIfPresent(resume.getUuid(), (key, value) -> value = resume);
    }

    @Override
    protected void doSave(Resume resume) {
        storage.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (String uuid1 : storage.keySet()) {
            if (uuid1.equals(uuid)) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExit(Object searchKey) {
        if ((int) searchKey <= 0) {
            return false;
        }
        return true;
    }
}

