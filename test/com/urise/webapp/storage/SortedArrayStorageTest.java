package com.urise.webapp.storage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    private static final Storage storage = new SortedArrayStorage();

    public SortedArrayStorageTest() {
        super(storage);
    }
}