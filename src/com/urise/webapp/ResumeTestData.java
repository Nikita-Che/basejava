package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
//        List<Section> sections = new ArrayList<>();
//        sections.add(new TextSection("дохуя умный", "дохуя квалифицированный"));
//        sections.add(new ContactsSection("8950132", "vozle zabora"));
        Resume resume = new Resume("uuid1", "vaysa");

        Map<SectionType, Sections> sectionsMap = new HashMap<>();
        TextSection textSection = new TextSection();
        textSection.setString("Her");
        sectionsMap.put(SectionType.PERSONAL,textSection);

        List<String> contacts = new ArrayList<>();
        contacts.add("8950050");
        contacts.add("Givet v korobke");

        resume.setSections(sectionsMap);
        resume.setContacts(contacts);
        System.out.println(resume);
    }
}
