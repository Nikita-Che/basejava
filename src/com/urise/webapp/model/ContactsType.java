package com.urise.webapp.model;

public enum ContactsType {
    TELEPHONE("Телефон"),
    SKYPE("Скайп"),
    EMAIL("Почта"),
    LINKEINPROFILE("ЛинкедИН"),
    GITHUBPROFILE("ГитХаб"),
    STACKOVEGLOWPROFILE("СтэковерфлоВ"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
