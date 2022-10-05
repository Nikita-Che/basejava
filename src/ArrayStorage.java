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
        storage[size] = r;
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
        //deleting element
        int deletedElementIndex = 0;
        boolean isDeleted = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                storage[i] = null;
                deletedElementIndex = i;
                isDeleted = true;
                size--;
                break;
            }
        }
        //right shift elements
        for (int i = deletedElementIndex; i < size; i++) {
            if (isDeleted) {
                storage[i] = storage[i + 1];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, resumes.length);
        return resumes;
    }

    int size() {
        return size;
    }
}
