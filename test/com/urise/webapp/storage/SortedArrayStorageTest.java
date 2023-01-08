package urise.webapp.storage;

import com.urise.webapp.storage.AbstractArrayStorageTest;
import com.urise.webapp.storage.SortedArrayStorage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}