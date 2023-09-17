package ru.asteises.sberbankcityguide.parser.impl;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.asteises.sberbankcityguide.model.City;
import ru.asteises.sberbankcityguide.model.CityDto;
import ru.asteises.sberbankcityguide.parser.CityCsvParserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
@Setter
@ExtendWith(MockitoExtension.class)
class CityCsvParserServiceImplTest {

    @Autowired
    private CityCsvParserService service;

    private String path;
    private City city1;
    private City city2;
    private CityDto cityDto1;
    private CityDto cityDto2;
    private List<CityDto> cityDtos;
    private List<City> cities;

    @BeforeEach
    protected void init() {
        service = new CityCsvParserServiceImpl();

        path = "src/test/resources/data/test.csv";

        city1 = City.builder()
                .id(1)
                .name("city_name_1")
                .region("city_region_1")
                .district("city_district_1")
                .population(1000)
                .foundation("city_foundation_1")
                .build();

        city2 = City.builder()
                .id(2)
                .name("city_name_2")
                .region("city_region_2")
                .district("city_district_2")
                .population(2000)
                .foundation("city_foundation_2")
                .build();

        cities = List.of(city1, city2);

        cityDto1 = new CityDto(
                "city_name_1",
                "city_region_1",
                "city_district_1",
                1000,
                "city_foundation_1");

        cityDto2 = new CityDto(
                "city_name_2",
                "city_region_2",
                "city_district_2",
                2000,
                "city_foundation_2");

        cityDtos = List.of(cityDto1, cityDto2);
    }

    @Test
    void parse() {
        List<City> except = cities;
        List<City> actual = service.parse(path);

        assertThat(except.size()).isEqualTo(actual.size());
        assertThat(except.get(0)).isEqualTo(actual.get(0));
        assertThat(except.get(1)).isEqualTo(actual.get(1));
    }

    @Test
    void getRecordFromLine() {
    }

    @Test
    void splitLine() {
    }

    @Test
    void parseForRecords() {
    }

    @Test
    void getCityFromRecord() {
    }

    @Test
    void checkEmptyFields() {
    }
}