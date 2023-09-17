package ru.asteises.sberbankcityguide.util.enums.sort;

import ru.asteises.sberbankcityguide.exception.exc.SortEnumNameException;

public enum SortEnum {

    NAME("name"),
    DISTRICT("district");

    private final String name;

    SortEnum(String name) {
        this.name = name;
    }

    public static SortEnum getSortByUpperCaseName(String name) {
        if (name == null || name.isEmpty()) {
            throw new SortEnumNameException("Не указано имя сортировки, будет применена сортировка по умолчанию");
        }
        for (SortEnum value: values()) {
            if (value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new SortEnumNameException("Неверное имя сортировки, будет применена сортировка по умолчанию");
    }

    public String getName() {
        return name;
    }
}