package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElem(Resume r, int index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[index] = r;
    }

    @Override
    protected void deleteElem(int index) {
        int numberMovedElements = size - index - 1;
        if (numberMovedElements > 0) {
            System.arraycopy(storage, index + 1, storage, index, numberMovedElements);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
