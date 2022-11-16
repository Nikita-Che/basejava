package com.urise.webapp.model;

import java.util.Map;
import java.util.Objects;

/**
 * Initial resume class
 */
public class Resume {
    //        implements Comparable<Resume{
    // Unique identifier
    private final String uuid;
    private final String fullName;
    private final Map<ContactsType, String> contacts;
    private final Map<SectionType, AbstractSection> sections;

//    public Resume(String fullName) {
//        this(UUID.randomUUID().toString(), fullName, contacts, sections);
//    }

    public Resume(String uuid, String fullName, Map<ContactsType, String> contacts, Map<SectionType, AbstractSection> sections) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(contacts, "fullName must not be null");
        Objects.requireNonNull(sections, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        this.contacts = contacts;
        this.sections = sections;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactsType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

//    public void setContacts(Map<ContactsType, String> contacts) {
//        this.contacts = contacts;
//    }
//
//    public void setSections(Map<SectionType, AbstractSection> sections) {
//        this.sections = sections;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!Objects.equals(uuid, resume.uuid)) return false;
        return Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }

//    @Override
//    public int compareTo(Resume o) {
//        int cmp = fullName.compareTo(o.fullName);
//        return cmp!=0? cmp:uuid.compareTo(o.uuid);
//    }
}
