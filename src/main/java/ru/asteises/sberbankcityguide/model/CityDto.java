package ru.asteises.sberbankcityguide.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {

    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;
}
