package storage;

import model.Resume;

import java.util.Arrays;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElem(Resume r, int index) {
        int insertIndex = 0 - index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void deleteElem(int index) {
        int numberMovedElements = size - index - 1;
        if (numberMovedElements > 0) {
            System.arraycopy(storage, index + 1, storage, index, numberMovedElements);
        }
    }

    @Override
    protected Object getIndexKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }
}
