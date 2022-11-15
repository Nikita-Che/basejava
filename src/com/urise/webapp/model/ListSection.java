package com.urise.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection { //ACHIEVEMENT, QUALIFICATIONS,
    List<String> strings;

    public ListSection(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "strings=" + strings +
                '}';
    }
}
