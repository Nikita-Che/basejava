package com.urise.webapp.model;

public enum ContactsType {
    PHONE("Тел."),
    MOBILE("Мобильный"),
    HOME_PHONE("Домашний тел."),
    SKYPE("Скайп"),
    EMAIL("Почта"),
    LINKEIN("ЛинкедИН"),
    GITHUB("ГитХаб"),
    STACKOVEFLOW("СтэковерфлоВ"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
