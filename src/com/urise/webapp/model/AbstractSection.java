package com.urise.webapp.model;

public class AbstractSection {
    private OrganizationSection organizationSection;
    private TextSection textSection;
    private ListSection listSection;

    public AbstractSection(OrganizationSection organizationSection, TextSection textSection, ListSection listSection) {
        this.organizationSection = organizationSection;
        this.textSection = textSection;
        this.listSection = listSection;
    }

    public AbstractSection() {
    }

    public OrganizationSection getOrganizationSection() {
        return organizationSection;
    }

    public void setOrganizationSection(OrganizationSection organizationSection) {
        this.organizationSection = organizationSection;
    }

    public TextSection getTextSection() {
        return textSection;
    }

    public void setTextSection(TextSection textSection) {
        this.textSection = textSection;
    }

    public ListSection getListSection() {
        return listSection;
    }

    public void setListSection(ListSection listSection) {
        this.listSection = listSection;
    }
}
