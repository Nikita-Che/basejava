package com.urise.webapp.util;

import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.TextSection;
import org.junit.jupiter.api.Test;

import static com.urise.webapp.TestData.RESUME_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {
    @Test
    void read() {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        assertEquals(RESUME_1, resume);
    }

    @Test
    void write() {
        AbstractSection section1 = new TextSection("objective");
        String json = JsonParser.write(section1, AbstractSection.class);
        System.out.println("______________________________________________________");
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        assertEquals(section2, section2);
    }

}