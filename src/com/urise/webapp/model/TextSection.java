package com.urise.webapp.model;

public class TextSection extends Sections {
    String string;

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "string='" + string + '\'' +
                '}';
    }
}
