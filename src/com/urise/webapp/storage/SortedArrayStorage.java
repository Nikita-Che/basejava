package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void save(Resume r) {
        //Смотри задание.
        //Arrays.binarysearch если нет элемента возвращает -2. 2 потенциально место где он мог бы быть. Проверь свдвиг на единицу
        //Вынести в абсстрактный
        //или расставлять по местам по хешкоду и остальное сдвигать
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
        return Arrays.binarySearch(storage,0,size, searchKey);
    }
}
