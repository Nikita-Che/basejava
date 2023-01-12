package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.JsonStreamSerializer;

import static com.urise.webapp.TestData.STORAGE_DIR;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(),new JsonStreamSerializer()));
    }
}