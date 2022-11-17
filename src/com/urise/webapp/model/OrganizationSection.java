package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {  //EXPERIENCE, EDUCATION
    private final List<Organization> organizationList;

    public OrganizationSection(List<Organization> organizationList) {
        Objects.requireNonNull(organizationList,"organizationList must not be null");
        this.organizationList = organizationList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizationList.equals(that.organizationList);
    }

    @Override
    public int hashCode() {
        return organizationList.hashCode();
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizationList=" + organizationList +
                '}';
    }
}
