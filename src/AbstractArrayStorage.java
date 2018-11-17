public abstract class AbstractArrayStorage {
    Resume[] storage;

    abstract void clear();

    abstract void save(Resume r);

    abstract Resume get(String uuid);

    abstract void delete(String uuid);

    abstract Resume[] getAll();

    abstract int size();

    abstract void update(Resume r);
}
