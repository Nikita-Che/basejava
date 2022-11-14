package com.urise.webapp.model;

public class OrganizationSection extends Sections {  //EXPERIENCE, EDUCATION
    String nameOfOrganiation;
    String possition;
    String responsibilities;

    String nameOfuniversity;
    String cource;

    String timeOf;

    public String getNameOfOrganiation() {
        return nameOfOrganiation;
    }

    public void setNameOfOrganiation(String nameOfOrganiation) {
        this.nameOfOrganiation = nameOfOrganiation;
    }

    public String getPossition() {
        return possition;
    }

    public void setPossition(String possition) {
        this.possition = possition;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getNameOfuniversity() {
        return nameOfuniversity;
    }

    public void setNameOfuniversity(String nameOfuniversity) {
        this.nameOfuniversity = nameOfuniversity;
    }

    public String getCource() {
        return cource;
    }

    public void setCource(String cource) {
        this.cource = cource;
    }

    public String getTimeOf() {
        return timeOf;
    }

    public void setTimeOf(String timeOf) {
        this.timeOf = timeOf;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "nameOfOrganiation='" + nameOfOrganiation + '\'' +
                ", possition='" + possition + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                ", nameOfuniversity='" + nameOfuniversity + '\'' +
                ", cource='" + cource + '\'' +
                ", timeOf='" + timeOf + '\'' +
                '}';
    }
}
