package com.urise.webapp.model;

public class TextSection extends AbstractSection { //PERSONAL, OBJECTIVE
    String content;

    public TextSection(String content) {
        this.content=content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "content='" + content + '\'' +
                '}';
    }
}
