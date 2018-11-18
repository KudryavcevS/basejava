import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    public void save(Resume r) {
        int index = getIndex(r.uuid);
        if (index == -1) {
            if (size >= 10000) {
                System.out.println("Storage is full");
                return;
            }
            storage[size] = r;
            size++;
            return;
        }
        System.out.println("ERROR: Resume " + r.uuid + " already exist");
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        System.out.println("ERROR: Resume " + uuid + " not exist");
        return null;
    }

    public void update(Resume r) {
        int index = getIndex(r.uuid);
        if (index > -1) {
            storage[index] = r;
            return;
        }
        System.out.println("ERROR: Resume " + r.uuid + " not exist");
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        }
        System.out.println("ERROR: Resume " + uuid + " not exist");
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}