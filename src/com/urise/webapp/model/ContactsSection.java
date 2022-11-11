package com.urise.webapp.model;

public class ContactsSection extends Section{
    String phone;
    String adress;

    public ContactsSection(String phone, String adress) {
        this.phone = phone;
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "ContactsSection{" +
                "phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
