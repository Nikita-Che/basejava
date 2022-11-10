package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.TextSection;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "vaysa");
        resume.setTextSection(new TextSection("дохуя умный", "дохуя квалифицированный"));

        System.out.println(resume);
        resume.printAll();
    }
}
