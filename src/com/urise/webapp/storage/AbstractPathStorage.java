package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writeble");
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(path);
        } catch (IOException e) {
            throw new StorageException("Path read Error", path.toString());
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            doWrite(resume, path);
        } catch (IOException e) {
            throw new StorageException("Could`t create File ", resume.getUuid());
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            doWrite(resume, path);
        } catch (IOException e) {
            throw new StorageException("Could`t create File ", resume.getUuid());
        }
    }

    @Override
    protected void doDelete(Path path) {
        if (!path.toFile().delete()) {
            throw new StorageException("Path deleted error ", null);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return new File(String.valueOf(directory)).toPath();
    }

    @Override
    protected boolean isExist(Path Path) {
        return directory.isAbsolute();
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Path> list = null;
        if (list == null) {
            throw new StorageException("Directory read Error", null);
        }
        try {
            list = Files.list(directory).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Resume> list1 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            list1.add((Resume) list.get(i));
        }

        return list1;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("pass delete error", null);
        }
    }

    @Override
    public int size() {
        List<Path> list = null;
        try {
            list = Files.list(directory).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.size();
    }

    protected abstract Resume doRead(Path path) throws IOException;

    protected abstract void doWrite(Resume resume, Path path) throws IOException;
}
