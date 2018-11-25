package model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    private final String uuid;
    private final String fullname;

    public Resume(){
        this(UUID.randomUUID().toString(), null);
    }

    public Resume(String uuid){
        this.uuid =uuid;
        fullname = null;
    }

    public Resume(String uuid, String fullname){
        this.uuid = uuid;
        this.fullname = fullname;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() { return uuid; }

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
        return this.uuid.compareTo(o.getUuid());
    }
}
