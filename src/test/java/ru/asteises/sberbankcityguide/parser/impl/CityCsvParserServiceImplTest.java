package ru.asteises.sberbankcityguide.parser.impl;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.asteises.sberbankcityguide.exception.exc.WrongCountTableColumns;
import ru.asteises.sberbankcityguide.exception.exc.WrongFilePathException;
import ru.asteises.sberbankcityguide.model.City;
import ru.asteises.sberbankcityguide.model.CityDto;
import ru.asteises.sberbankcityguide.parser.CityCsvParserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void parse_Ok() {
        List<City> except = cities;
        List<City> actual = service.parse(path);

        assertThat(except.size()).isEqualTo(actual.size());
        assertThat(except.get(0)).isEqualTo(actual.get(0));
        assertThat(except.get(1)).isEqualTo(actual.get(1));
    }

    @Test
    void parse_FileNotFound() {
        Exception exception = assertThrows(
                WrongFilePathException.class,
                () -> service.parse("/"));

        String expectedMessage = "Файл не найден";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void splitLine_Ok() {
        String line = "1;name_1;region_1;district_1;1000;foundation_1";

        String[] excepted = {"1", "name_1", "region_1", "district_1", "1000", "foundation_1"};
        String[] actual = service.splitLine(line);

        assertThat(excepted).isEqualTo(actual);
        assertThat(excepted.length).isEqualTo(actual.length);
        assertThat(excepted[0]).isEqualTo(actual[0]);
    }

    @Test
    void getCityFromRecord_Ok() {
        List<String> cityRecord = List.of(
                String.valueOf(city1.getId()),
                city1.getName(),
                city1.getRegion(),
                city1.getDistrict(),
                String.valueOf(city1.getPopulation()),
                city1.getFoundation());

        City excepted = city1;
        City actual = service.getCityFromRecord(cityRecord);

        assertThat(excepted).isEqualTo(actual);
        assertThat(excepted.getId()).isEqualTo(actual.getId());
        assertThat(excepted.getName()).isEqualTo(actual.getName());
    }

    @Test
    void getCityFromRecord_wrongCountTableColumns() {
        List<String> cityRecord = List.of(
                String.valueOf(city1.getId()),
                city1.getName(),
                city1.getRegion(),
                city1.getDistrict(),
                String.valueOf(city1.getPopulation()));

        Exception exception = assertThrows(
                WrongCountTableColumns.class,
                () -> service.getCityFromRecord(cityRecord));

        String exceptedMessage = "Количество значений не соответствует записи в этой таблице";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exceptedMessage));
    }

    @Test
    void checkEmptyFields_emptyValue() {
        String inputValue = "";

        String excepted = "none";
        String actual = service.checkEmptyFields(inputValue);

        assertThat(excepted).isEqualTo(actual);
    }

    @Test
    void checkEmptyFields_Ok() {
        String inputValue = "region";

        String excepted = "region";
        String actual = service.checkEmptyFields(inputValue);

        assertThat(excepted).isEqualTo(actual);
    }
}