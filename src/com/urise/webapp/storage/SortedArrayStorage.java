package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int indexOfPosition = -getIndex(r.getUuid()) -1 ;  //определяет индекс местоназначения

        //в зависимости от того занято или нет сдвигать массив копированием справо с момента индекс+1
        if (storage[indexOfPosition] != null) {

            for (int i = indexOfPosition; i < size; i++) {
                //сдвинуть весь массив вправо на 1 индекс

            }

            storage[indexOfPosition] = r;
            size++;
        } else {
            storage[indexOfPosition] = r;
            size++;
        }
    }

    @Override
    public void update(Resume r) {
        //определи хеш код и упдейтни
    }

    @Override
    public void delete(String uuid) {
        //определи хеш код и удали
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
