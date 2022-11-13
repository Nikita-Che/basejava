package com.urise.webapp.model;

public class ContactsSection extends Sections {
        String contacts;

    public ContactsSection(ContactsSection contactsSection, TextSection textSection) {
        super(contactsSection, textSection);
    }

    @Override
    public String toString() {
        return "ContactsSection{" +
                "string='" + contacts + '\'' +
                '}';
    }
}
