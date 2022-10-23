package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public abstract int size();

    @Override
    public abstract void clear();

    @Override
    public abstract Resume[] getAll();

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(uuid);
    }

    protected abstract Resume getResume(String uuid);

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            updateResume(resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract void updateResume(Resume resume);

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (index > 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveResume(r);
        }
    }

    protected abstract void saveResume(Resume resume);

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(uuid);
        }
    }

    protected abstract void deleteResume(String uuid);

    protected abstract int getIndex(String uuid);
}
