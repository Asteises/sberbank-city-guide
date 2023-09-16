package ru.asteises.sberbankcityguide.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.model.City;
import ru.asteises.sberbankcityguide.service.CityGuideSorting;
import ru.asteises.sberbankcityguide.util.enums.sort.SortEnum;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class CityGuideSortingImpl implements CityGuideSorting {

    @Override
    public List<City> getSortingCities(List<City> cities, String sorting) {
        SortEnum sortEnum = SortEnum.getSortByUpperCaseName(sorting);
        switch (sortEnum) {
            case NAME -> getCitiesSortByName(cities);
            case DISTRICT -> getCitiesSortByDistrictAndName(cities);
            default -> log.info("Данный вид сортировки не предусмотрен: {}. Будет применена сортировка по умолчанию ", sorting);
        }
        return cities;
    }

    @Override
    public void getCitiesSortByName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getName));
    }

    @Override
    public void getCitiesSortByDistrictAndName(List<City> cities) {
        cities
                .sort(Comparator.comparing(City::getDistrict)
                        .thenComparing(City::getName));
    }
}
