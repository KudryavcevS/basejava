package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SectionOrg extends Section {

    private final List<Organization> organizations;

    public SectionOrg(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public SectionOrg(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "organizations list is empty");
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionOrg that = (SectionOrg) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
