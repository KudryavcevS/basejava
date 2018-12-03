package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SectionList extends Section {

    private final List<String> items;

    public SectionList(String... items) {
        this(Arrays.asList(items));
    }

    public SectionList(List<String> items) {
        Objects.requireNonNull(items, "items list is empty");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionList that = (SectionList) o;

        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
