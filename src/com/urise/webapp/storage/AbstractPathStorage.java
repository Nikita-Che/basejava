package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
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
            return doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read Error", path.toString());
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File write Error ", resume.getUuid());
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Could`t create File ", resume.getUuid());
        }
        doUpdate(resume, path);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path deleted error ", null, e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected List<Resume> doCopyAll() {
        // TODO: 26.11.2022 не работает
        List<Path> list = null;
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
            throw new StorageException("pass delete error", null, e);
        }
    }

    @Override
    public int size() {
        List<Path> list = null;
        try {
            list = Files.list(directory).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Directory read Error", null, e);
        }
        return list.size();
    }

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;
}
