package ru.asteises.sberbankcityguide.mapper;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import ru.asteises.sberbankcityguide.model.City;
import ru.asteises.sberbankcityguide.model.CityDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
@Setter
@WebMvcTest(CityMapper.class)
class CityMapperTest {
    private City city1;
    private City city2;
    private CityDto cityDto1;
    private CityDto cityDto2;
    private List<CityDto> cityDtos;
    private List<City> cities;

    @BeforeEach
    protected void init() {
        city1 = City.builder()
                .id(1).name("city_name_1")
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
    void toDto() {
        CityDto target = CityMapper.INSTANCE.toDto(city1);

        assertThat(target.getName()).isEqualTo(cityDto1.getName());
        assertThat(target.getRegion()).isEqualTo(cityDto1.getRegion());
        assertThat(target.getDistrict()).isEqualTo(cityDto1.getDistrict());
        assertThat(target.getPopulation()).isEqualTo(cityDto1.getPopulation());
        assertThat(target.getFoundation()).isEqualTo(cityDto1.getFoundation());
    }

    @Test
    void testToDto() {
        List<CityDto> target = CityMapper.INSTANCE.toDto(cities);

        assertThat(target.get(0).getName()).isEqualTo(cityDtos.get(0).getName());
        assertThat(target.get(0).getRegion()).isEqualTo(cityDtos.get(0).getRegion());
        assertThat(target.get(0).getDistrict()).isEqualTo(cityDtos.get(0).getDistrict());
        assertThat(target.get(0).getPopulation()).isEqualTo(cityDtos.get(0).getPopulation());
        assertThat(target.get(0).getFoundation()).isEqualTo(cityDtos.get(0).getFoundation());

        assertThat(target.get(1).getName()).isEqualTo(cityDtos.get(1).getName());
        assertThat(target.get(1).getRegion()).isEqualTo(cityDtos.get(1).getRegion());
        assertThat(target.get(1).getDistrict()).isEqualTo(cityDtos.get(1).getDistrict());
        assertThat(target.get(1).getPopulation()).isEqualTo(cityDtos.get(1).getPopulation());
        assertThat(target.get(1).getFoundation()).isEqualTo(cityDtos.get(1).getFoundation());
    }
}