package com.urise.webapp.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {
    //        implements Comparable<Resume{
    // Unique identifier
    private final String uuid;
    private final String fullName;

    List<String> contacts;
    Map<SectionType, Sections> sections;

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public void setSections(Map<SectionType, Sections> sections) {
        this.sections = sections;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
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
