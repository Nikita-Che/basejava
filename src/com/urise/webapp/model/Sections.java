package com.urise.webapp.model;

public class Sections {
    @Override
    public String toString() {
        return "Sections{" +
                "contactsSection=" + contactsSection +
                ", textSection=" + textSection +
                '}';
    }

    ContactsSection contactsSection;
    TextSection textSection;
}
