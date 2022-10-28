package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

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
    public Resume[] getAll() {
        Resume[] resumes = new Resume[storage.size()];
        resumes = storage.values().toArray(resumes);
        return resumes;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        //я не могу получить значение по индексу. я могу получить значение по KEY а searchKey это индекс элемента

//        Set<String> strings = storage.keySet();
//        Iterator iterator = strings.iterator();
//        while (iterator.hasNext()){
//
//        }
//        Resume resume;
//        Set<String> strings = storage.keySet();

        //идем по мапе ДО searchkey. последний элемент в проходе возвращаем

        //либо просто ходим по мапе и сраваем

        //можно вызвать GetAll, присвоить его в массив, и вернуть массив[searchKey]
//        String[] strings = storage.keySet().toArray(new String[0]);
//        return new Resume(strings[(int) searchKey]);

        //перекинуть лист ключей в колеккцию, отсортировать, найти обратно по стрингу нужну

        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.computeIfPresent(resume.getUuid(), (key, value) -> value = resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.putIfAbsent(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        //я не могу получить значение по индексу. я могу получить значение по KEY а searchKey это индекс элемента
        storage.remove(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
//        int index = 0;
//        for (String uuid1 : storage.keySet()) {
//            if (uuid1.equals(uuid)) {
//                return index;
//            }
//            index++;
//        }
//        return -1;

        for (String uuid1 : storage.keySet()) {
            if (uuid1.equals(uuid)) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExit(Object searchKey) {
        if ((int) searchKey <= 0) {
            return false;
        }
        return true;
    }
}

