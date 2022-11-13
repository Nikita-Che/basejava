package com.urise.webapp.model;

public class TextSection extends Sections {
    String textSection;

    public TextSection(ContactsSection contactsSection, TextSection textSection) {
        super(contactsSection, textSection);
    }

    public void setTextSection(String textSection) {
        this.textSection = textSection;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "string='" + textSection + '\'' +
                '}';
    }
}
