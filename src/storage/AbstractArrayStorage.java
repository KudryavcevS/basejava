package storage;

import model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int SIZE_LIMIT = 10000;

    protected Resume[] storage = new Resume[SIZE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }
}
