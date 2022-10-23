package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

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
        resumes = storage.toArray(resumes);
        return resumes;
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    protected void updateResume(Resume resume) {
        storage.set(getIndex(resume.getUuid()), resume);
    }

    @Override
    protected void saveResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteResume(String uuid) {
        storage.remove(getIndex(uuid));
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}
