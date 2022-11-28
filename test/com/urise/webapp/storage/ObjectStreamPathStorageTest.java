package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.StrategieImpl;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(),new StrategieImpl()));
    }
}