package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.XmlStreamSerializer;

import static com.urise.webapp.TestData.STORAGE_DIR;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(),new XmlStreamSerializer()));
    }
}