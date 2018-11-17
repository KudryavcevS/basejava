public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int currentSize = size();
        for (int i = 0; i < currentSize; i++) {
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
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int currentSize = size();
        for (int i = 0; i < currentSize; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[currentSize - 1];
                storage[currentSize - 1] = null;
                break;
            }
        }
    }

    Resume[] getAll(){
        Resume[] allResume = new Resume[size()];
        for (int i = 0; i < allResume.length; i++) {
            allResume[i] = storage[i];
        }
        return allResume;
    }

    int size() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return storage.length;
    }
}