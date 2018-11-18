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
//        if (contains(r.uuid)) {
//            System.out.println("Resume already exist");
//            return;
//        }
        if (size >= 10000) {
            System.out.println("Storage is full");
            return;
        }
        storage[size] = r;
        size++;
    }


    Resume get(String uuid) {
//        if (!contains(uuid)) {
//            System.out.println("Resume not exist");
//            return null;
//        }
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
//        if (!contains(uuid)) {
//            System.out.println("Resume not exist");
//            return;
//        }
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            }
        }
    }

    Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        for (int i = 0; i < size; i++) {
            allResume[i] = storage[i];
        }
        return allResume;
    }

    int size() {
        return size;
    }


    void update(Resume r) {
//        if (!contains(r.uuid)) {
//            System.out.println("Resume not exist");
//            return;
//        }
        String uuid = r.toString();
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = r;
                break;
            }
        }
    }

//    private boolean contains(String uuid) {
//        for (int i = 0; i < size; i++) {
//            if (storage[i].toString().equals(uuid)) {
//                return true;
//            }
//        }
//        return false;
//    }
}