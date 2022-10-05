/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size == 0) {
            storage[0] = r;
        } else {
            storage[size] = r;
        }

        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int deletedElementIndex = 0;
        //deleting element
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                storage[i] = null;
                deletedElementIndex = i;
                break;
            }
        }
        //right shift elements
        for (int i = deletedElementIndex; i < size; i++) {
            storage[i] = storage[i + 1];
        }

        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
