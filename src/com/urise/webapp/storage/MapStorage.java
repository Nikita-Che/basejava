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
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void updateResume(Resume resume) {
        storage.computeIfPresent(resume.getUuid(), (key, value) -> value = resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }
}

