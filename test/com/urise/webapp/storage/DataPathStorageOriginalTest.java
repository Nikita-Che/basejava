package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.DataStreamSerializer;

import static com.urise.webapp.TestData.STORAGE_DIR;

public class DataPathStorageOriginalTest extends AbstractStorageTest {
    public DataPathStorageOriginalTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new DataStreamSerializer()));
    }
}