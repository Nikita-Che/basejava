package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        getNotExistingSearchKey(uuid);
        return doGet(uuid);
    }

    @Override
    public void update(Resume resume) {
        getNotExistingSearchKey(resume.getUuid());
        doUpdate(resume);
    }

    @Override
    public void save(Resume r) {
        getExistingSearchKey(r.getUuid());
        doSave(r);
    }

    @Override
    public void delete(String uuid) {
        getNotExistingSearchKey(uuid);
        doDelete(uuid);
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExit(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExit(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Resume doGet(String uuid);

    protected abstract void doUpdate(Resume resume);

    protected abstract void doSave(Resume resume);

    protected abstract void doDelete(String uuid);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExit(Object searchKey);
}
