package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        getSearchKeyParentNotExist(uuid);
        return doGet(uuid);
    }

    protected abstract Resume doGet(String uuid);

    @Override
    public void update(Resume resume) {
        getSearchKeyParentNotExist(resume.getUuid());
        doUpdate(resume);
    }

    protected abstract void doUpdate(Resume resume);

    @Override
    public void save(Resume r) {
        getSearchKeyParentExist(r.getUuid());
        doSave(r);
    }

    protected abstract void doSave(Resume resume);

    @Override
    public void delete(String uuid) {
        getSearchKeyParentNotExist(uuid);
        doDelete(uuid);
    }

    protected abstract void doDelete(String uuid);

    protected abstract boolean getSearchKey(String uuid);

    private void getSearchKeyParentNotExist(String uuid) {
        boolean searchKey = getSearchKey(uuid);
        if (!searchKey) {
            getNotExistingSearchKey(uuid);
        }
    }

    private void getSearchKeyParentExist(String uuid) {
        boolean searchKey = getSearchKey(uuid);
        if (searchKey) {
            getExistingSearchKey(uuid);
        }
    }

    private void getNotExistingSearchKey(String uuid) {
        throw new NotExistStorageException(uuid);
    }

    private void getExistingSearchKey(String uuid) {
        throw new ExistStorageException(uuid);
    }
}
