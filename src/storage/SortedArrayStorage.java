package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) System.out.println("ERROR: Resume " + r.getUuid() + " already exist");
        else if (size == SIZE_LIMIT) System.out.println("Storage is full");
        else if (size == 0) storage[0] = r;
        else {
            for (int i = 0; i < size; i++) {
                if (storage[i].compareTo(r) > 0) {
                    for (int y = size; y > i; y--) {
                        storage[y] = storage[y - 1];
                    }
                    storage[i] = r;
                    size++;
                    return;
                }
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) System.out.println("ERROR: Resume " + uuid + " not exist");
        else {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
