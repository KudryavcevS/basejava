package model;

import java.util.Objects;
import java.util.RandomAccess;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

    private final String uuid;
    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid is null");
        Objects.requireNonNull(fullName, "fullName is null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public int compareTo(Resume o) {
        if (this.getFullName().equals(o.getFullName()) && !this.getUuid().equals(o.getUuid())) return this.getUuid().compareTo(o.getUuid());
        return this.getFullName().compareTo(o.getFullName());
    }
}
