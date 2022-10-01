import java.util.Arrays;
import java.util.Collection;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
    }

    void save(Resume r) {
        storage[0] = r;
    }

    Resume get(String uuid) {
        return storage[0];
    }

    void delete(String uuid) {
        storage[0] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return storage.length;
    }
}
