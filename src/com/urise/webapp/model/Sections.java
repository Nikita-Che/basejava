package com.urise.webapp.model;

public class Sections {
    ContactsSection contactsSection;
    TextSection textSection;

    public Sections(ContactsSection contactsSection, TextSection textSection) {
        this.contactsSection = contactsSection;
        this.textSection = textSection;
    }

    public Sections() {
    }

    public ContactsSection getContactsSection() {
        return contactsSection;
    }

    public void setContactsSection(ContactsSection contactsSection) {
        this.contactsSection = contactsSection;
    }

    @Override
    public String toString() {
        return "Sections{" +
                "contactsSection=" + contactsSection +
                ", textSection=" + textSection +
                '}';
    }
}
