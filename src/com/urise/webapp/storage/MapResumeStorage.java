package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();


    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {

    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {

    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected List<Resume> getListOfAllResumeInStorage() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}

