package com.urise.webapp.model;

public class TextSection extends AbstractSection{
    String  achievement;
    String  qualifications;

    public TextSection(String achievement, String qualifications) {
        this.achievement = achievement;
        this.qualifications = qualifications;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "achievement='" + achievement + '\'' +
                ", qualifications='" + qualifications + '\'' +
                '}';
    }
}
