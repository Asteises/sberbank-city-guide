package ru.asteises.sberbankcityguide.service;

import ru.asteises.sberbankcityguide.model.City;

import java.util.List;

public interface CityGuideSorting {

    List<City> getSortingCities(List<City> cities, String sorting);

    void getCitiesSortByName(List<City> cities);

    void getCitiesSortByDistrictAndName(List<City> cities);
}
