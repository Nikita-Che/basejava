package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected Resume doGet(File file) {
        Resume resume = null;
        try {
            resume = doRead(file);
        } catch (IOException e) {
            throw new StorageException("OI error", file.getName(), e);
        }
        return resume;
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("OI error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("OI error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File not deleted", file.getName());
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("files = null", files.toString());
        }
        List<Resume> list = new ArrayList<>();
        for (File file : files) {
            try {
                list.add(doRead(file));
            } catch (IOException e) {
                throw new StorageException("OI error", file.getName(), e);
            }
        }
        return list;
    }

    @Override
    public void clear() {
//        directory.delete();
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("files = null", files.toString());
        }
        for (File file : files) {
            file.delete();
        }
    }

    @Override
    public int size() {
//        File[] files = directory.listFiles();
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("files = null", list.toString());
        }
        return list.length;
    }

    protected abstract Resume doRead(File file) throws IOException;

    protected abstract void doWrite(Resume resume, File file) throws IOException;
}
