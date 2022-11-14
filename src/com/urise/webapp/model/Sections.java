package com.urise.webapp.model;

public class Sections {
    OrganizationSection organizationSection;
    TextSection textSection;

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

    @Override
    public String toString() {
        return "Sections{" +
                "organizationSection=" + organizationSection +
                ", textSection=" + textSection +
                '}';
    }
}
