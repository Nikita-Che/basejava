package com.urise.webapp.model;

public class Section {
    TextSection textSection;
    ContactsSection contactsSection;

    public TextSection getTextSection() {
        return textSection;
    }

    public void setTextSection(TextSection textSection) {
        this.textSection = textSection;
    }

    public ContactsSection getContactsSection() {
        return contactsSection;
    }

    public void setContactsSection(ContactsSection contactsSection) {
        this.contactsSection = contactsSection;
    }

    @Override
    public String toString() {
        return "Section{" +
                "textSection=" + textSection +
                ", contactsSection=" + contactsSection +
                '}';
    }
}
