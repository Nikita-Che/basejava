package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume resume = new Resume("uuid1", "vaysa");

        Map<ContactsType, String> contacts = new HashMap<>();
        contacts.put(ContactsType.TELEPHONE, "89505096");
        contacts.put(ContactsType.EMAIL, "vasya@vasya");
        contacts.put(ContactsType.HOMEPAGE, "vasya.ru");
        contacts.put(ContactsType.SKYPE, "vasilisk");
        contacts.put(ContactsType.GITHUBPROFILE, "vasyaGihub");
        contacts.put(ContactsType.LINKEINPROFILE, "VasyaLinkedin");
        contacts.put(ContactsType.STACKOVEGLOWPROFILE, "Vasya Stack");

        Map<SectionType, Sections> sectionsWorker = new HashMap<>();

        TextSection pers = new TextSection();
        List<String> personal = new ArrayList<>();
        personal.add("персональные качества");
        pers.setTextDescription(personal);
        sectionsWorker.put(SectionType.PERSONAL, pers);

        TextSection obj = new TextSection();
        List<String> objective = new ArrayList<>();
        objective.add("позиция");
        obj.setTextDescription(objective);
        sectionsWorker.put(SectionType.OBJECTIVE, obj);


        TextSection ach = new TextSection();
        List<String> achivement = new ArrayList<>();
        achivement.add("достижения");
        ach.setTextDescription(achivement);
        sectionsWorker.put(SectionType.ACHIEVEMENT, ach);

        TextSection qua = new TextSection();
        List<String> qualify = new ArrayList<>();
        qualify.add("квалификация");
        qua.setTextDescription(qualify);
        sectionsWorker.put(SectionType.QUALIFICATIONS, qua);

        TextSection edu = new TextSection();
        List<String> education = new ArrayList<>();
        education.add("образование");
        edu.setTextDescription(education);
        sectionsWorker.put(SectionType.EDUCATION, edu);

        TextSection ex = new TextSection();
        List<String> exp = new ArrayList<>();
        exp.add("опыт работы");
        ex.setTextDescription(exp);
        sectionsWorker.put(SectionType.EXPERIENCE, ex);


        resume.setContacts(contacts);
        resume.setSections(sectionsWorker);
        System.out.println(resume);
    }
}
