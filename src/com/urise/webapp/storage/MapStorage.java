package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

// TODO: 03.11.2022 implement 
// TODO: 03.11.2022 create new MapStorage with searchkey not uuid 
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
    public List<Resume> getAllSorted() {
        List<String>  list = new ArrayList<>(storage.keySet());
        List<Resume> list1 = new ArrayList<>();
        for (String s : list) {
            list1.add(new Resume(s));
        }
        list1.sort(Resume.comparator);
        return list1;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }
}

