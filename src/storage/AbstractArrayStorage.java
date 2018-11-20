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

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > 0) System.out.println("ERROR: Resume " + r.getUuid() + " already exist");
        else if (size == SIZE_LIMIT) System.out.println("Storage is full");
        else {
            insertElem(r, index);
            size++;
        }
    }

    protected abstract void insertElem(Resume r, int index);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Resume " + uuid + " not exist");
            return null;
        }
        else return storage[index];
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) System.out.println("ERROR: Resume " + r.getUuid() + " not exist");
        else storage[index] = r;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) System.out.println("ERROR: Resume " + uuid + " not exist");
        else {
            deleteElem(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void deleteElem(int index);

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
