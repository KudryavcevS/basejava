package storage;

import model.Resume;
import java.util.Arrays;


public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) System.out.println("ERROR: Resume " + r.getUuid() + " already exist");
        else if (size == SIZE_LIMIT) System.out.println("Storage is full");
        else {
            storage[size] = r;
            size++;
        }
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

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}