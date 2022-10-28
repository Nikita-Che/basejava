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
    protected Resume doGet(String uuid) {
        return storage.get((Integer) getSearchKey(uuid));
    }

    @Override
    protected void doUpdate(Resume resume) {
        storage.set((Integer) getSearchKey(resume.getUuid()), resume);
    }

    @Override
    protected void doSave(Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(new Resume(uuid));
    }

    @Override
    protected Object getSearchKey(String uuid) {
        int index = 0;
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    protected boolean isExit(Object searchKey) {
        if ((int) searchKey < 0) {
            return false;
        }
        return true;
    }
}

