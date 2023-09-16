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
            throw new SortEnumNameException("Sort name is empty or null");
        }
        for (SortEnum value: values()) {
            if (value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new SortEnumNameException("Wrong name for sort enum");
    }

    public String getName() {
        return name;
    }
}
