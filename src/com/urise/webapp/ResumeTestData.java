package com.urise.webapp;

import com.urise.webapp.model.ContactsSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import com.urise.webapp.model.TextSection;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        List<Section> sections = new ArrayList<>();
        sections.add(new TextSection("дохуя умный", "дохуя квалифицированный"));
        sections.add(new ContactsSection("8950132", "vozle zabora"));
        Resume resume = new Resume("uuid1", "vaysa",sections);

        System.out.println(resume);
    }
}
