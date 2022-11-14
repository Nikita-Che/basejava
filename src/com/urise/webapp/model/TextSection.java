package com.urise.webapp.model;

import java.util.List;

public class TextSection extends Sections { //ACHIEVEMENT, QUALIFICATIONS, PERSONAL, OBJECTIVE
    List<String> textDescription;

    public List<String> getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(List<String> textDescription) {
        this.textDescription = textDescription;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "textDescription=" + textDescription +
                '}';
    }
}
