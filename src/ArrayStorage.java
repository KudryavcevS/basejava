public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int currentSize = size();
        for (int i = 0; i < currentSize; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (contains(r)) {
            System.out.println("Resume already exist");
            return;
        }
        if (size() == 10000){
            System.out.println("Storage is full");
            return;
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        if (!contains(uuid)) {
            System.out.println("Resume not exist");
            return null;
        }
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (!contains(uuid)) {
            System.out.println("Resume not exist");
            return;
        }
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

    void update(Resume r){
        if (!contains(r)) {
            System.out.println("Resume not exist");
            return;
        }
        int currentSize = size();
        String uuid = r.toString();
        for (int i = 0; i < currentSize; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = r;
                break;
            }
        }
    }

    private boolean contains(String uuid){
        int currentSize = size();
        for (int i = 0; i < currentSize; i++) {
            if (storage[i].toString().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private boolean contains(Resume r){
        return contains(r.toString());
    }
}