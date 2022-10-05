/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
            break;
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {   //deleting element
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                storage[i] = null;
                break;
            }
        }

        for (int i = 0; i < storage.length - 1; i++) {   //right shift elements
            if (storage[i] == null) {
                storage[i] = storage[i + 1];
                storage[i + 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int index = 0;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                index = i;
                break;
            }
        }  //finding index

        Resume[] resumes = new Resume[index];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = storage[i];
        }

        return resumes;
    }

    int size() {
        int resumeCount = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                resumeCount++;
            }
        }
        return resumeCount;
    }
}
