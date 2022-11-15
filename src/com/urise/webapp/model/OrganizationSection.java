package com.urise.webapp.model;

import java.util.List;

public class OrganizationSection extends AbstractSection {  //EXPERIENCE, EDUCATION
    List<Organization> organizationList;

    public OrganizationSection(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizationList=" + organizationList +
                '}';
    }
}
