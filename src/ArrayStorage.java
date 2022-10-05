/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
        size++;
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
        int deletedElementIndex = 0;
        //deleting element
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                storage[i] = null;
                deletedElementIndex = i;
                break;
            }
        }
        //right shift elements
        for (int i = deletedElementIndex; i < storage.length - 1; i++) {
            if (storage[i] == null && storage[i + 1] == null) {
                break;
            } else {
                storage[i] = storage[i + 1];
            }
        }
        //right shift elements old and modified!
//        for (int i = 0; i < storage.length - 1; i++) {
//            if (storage[i] == null && storage[i + 1] != null) {
//                storage[i] = storage[i + 1];
//                storage[i + 1] = null;
//            }
//        }
        size--;
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
        return size;
    }
}
