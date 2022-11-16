package com.urise.webapp;

import com.urise.webapp.model.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) throws MalformedURLException {

        Map<ContactsType, String> contacts = new HashMap<>();
        contacts.put(ContactsType.TELEPHONE, "89505096");
        contacts.put(ContactsType.EMAIL, "vasya@vasya");
        contacts.put(ContactsType.HOMEPAGE, "vasya.ru");
        contacts.put(ContactsType.SKYPE, "vasilisk");
        contacts.put(ContactsType.GITHUBPROFILE, "vasyaGihub");
        contacts.put(ContactsType.LINKEINPROFILE, "VasyaLinkedin");
        contacts.put(ContactsType.STACKOVEGLOWPROFILE, "Vasya Stack");

        String fullName = "Vasya Vasyin";
        String uuid = "uuid";

        String content = "PERSONAL";
        TextSection personalTextSection = new TextSection(content);
        String content1 = "OBJECTIVE";
        TextSection objectiveTextSection = new TextSection(content1);

        List<String> achivementlist = new ArrayList<>();
        achivementlist.add("ACHIEVEMENT");
        ListSection achivementlistSection = new ListSection(achivementlist);

        List<String> qualifications = new ArrayList<>();
        qualifications.add("QUALIFICATIONS");
        ListSection qualiflistSection = new ListSection(qualifications);

        LocalDate startDate = LocalDate.of(2005, 11, 12);
        LocalDate endDate = LocalDate.of(2005, 11, 15);
        Period period = new Period("title", "description", startDate, endDate);
        Period period1 = new Period("title1", "description1", startDate, endDate);
        List<Period> periodList = new ArrayList<>();
        periodList.add(period);
        periodList.add(period1);
        String url = "https://github.com/Nikita-Che";
        URL web = new URL(url);
        Organization organization = new Organization("organization", web, periodList);
        Organization organization1 = new Organization("organization1", web, periodList);
        List<Organization> organizationList = new ArrayList<>();
        organizationList.add(organization);
        organizationList.add(organization1);
        OrganizationSection expOrganizationSection = new OrganizationSection(organizationList);

        OrganizationSection expOrganizationSection1 = new OrganizationSection(organizationList);

        Map<SectionType, AbstractSection> sectionsWorker = new HashMap<>();
        sectionsWorker.put(SectionType.PERSONAL, personalTextSection);
        sectionsWorker.put(SectionType.OBJECTIVE, objectiveTextSection);
        sectionsWorker.put(SectionType.ACHIEVEMENT, achivementlistSection);
        sectionsWorker.put(SectionType.QUALIFICATIONS, qualiflistSection);
        sectionsWorker.put(SectionType.EXPERIENCE, expOrganizationSection);
        sectionsWorker.put(SectionType.EDUCATION, expOrganizationSection1);

        Resume resume = new Resume(uuid, fullName, contacts, sectionsWorker);
        System.out.println(resume);
    }
}
