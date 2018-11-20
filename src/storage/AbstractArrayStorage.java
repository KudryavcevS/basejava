package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int SIZE_LIMIT = 10000;

    protected Resume[] storage = new Resume[SIZE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        System.out.println("ERROR: model.Resume " + uuid + " not exist");
        return null;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            storage[index] = r;
            return;
        }
        System.out.println("ERROR: Resume " + r.getUuid() + " not exist");
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
