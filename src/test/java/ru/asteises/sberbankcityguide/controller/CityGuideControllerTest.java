package ru.asteises.sberbankcityguide.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MissingServletRequestParameterException;
import ru.asteises.sberbankcityguide.exception.exc.SortEnumNameException;
import ru.asteises.sberbankcityguide.exception.exc.WrongFilePathException;
import ru.asteises.sberbankcityguide.model.CityDto;
import ru.asteises.sberbankcityguide.service.CityGuideService;
import ru.asteises.sberbankcityguide.util.endpoints.Endpoints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Getter
@Setter
@WebMvcTest(CityGuideController.class)
class CityGuideControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityGuideService service;

    private CityDto cityDto1;
    private List<CityDto> cityDtos;
    private Map<String, Long> citiesCountByRegion;

    @BeforeEach
    protected void init() {
        cityDto1 = new CityDto(
                "name_1",
                "region_1",
                "district_1",
                1000,
                "foundation_1");

        CityDto cityDto2 = new CityDto(
                "name_2",
                "region_2",
                "district_2",
                2000,
                "foundation_2");

        cityDtos = List.of(cityDto1, cityDto2);

        citiesCountByRegion = new HashMap<>();
        citiesCountByRegion.put("region_1", 10L);
        citiesCountByRegion.put("region_2", 20L);
    }

    @SneakyThrows
    @Test
    void addCitiesToGuide_Ok() {
        Mockito.when(service.getCities(Mockito.anyString())).thenReturn(cityDtos);

        mockMvc.perform(post(Endpoints.API + Endpoints.ADD)
                        .param("path", "src/main/resources/data/cityguide.csv"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("name_1"))
                .andExpect(jsonPath("$[0].region").value("region_1"))
                .andExpect(jsonPath("$[0].district").value("district_1"))
                .andExpect(jsonPath("$[0].population").value(1000))
                .andExpect(jsonPath("$[0].foundation").value("foundation_1"))
                .andExpect(jsonPath("$[1].name").value("name_2"))
                .andExpect(jsonPath("$[1].region").value("region_2"))
                .andExpect(jsonPath("$[1].population").value(2000))
                .andExpect(jsonPath("$[1].foundation").value("foundation_2"));
    }

    @SneakyThrows
    @Test
    void addCitiesToGuide_missParameter() {
        mockMvc.perform(post(Endpoints.API + Endpoints.ADD)
                        .param("path", (String) null))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException));
    }

    @SneakyThrows
    @Test
    void addCitiesToGuide_wrongParameter() {
        Mockito.when(service.getCities(Mockito.anyString())).thenThrow(WrongFilePathException.class);

        mockMvc.perform(post(Endpoints.API + Endpoints.ADD)
                        .param("path", "/"))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof WrongFilePathException));
    }

    @SneakyThrows
    @Test
    void addCitiesToGuideSort_Ok() {
        Mockito.when(service.getSortingCities(Mockito.anyString(), Mockito.anyString())).thenReturn(cityDtos);

        mockMvc.perform(post(Endpoints.API + Endpoints.ADD_SORT)
                        .param("path", "src/main/resources/data/cityguide.csv")
                        .param("sorting", "name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("name_1"))
                .andExpect(jsonPath("$[0].region").value("region_1"))
                .andExpect(jsonPath("$[0].district").value("district_1"))
                .andExpect(jsonPath("$[0].population").value(1000))
                .andExpect(jsonPath("$[0].foundation").value("foundation_1"))
                .andExpect(jsonPath("$[1].name").value("name_2"))
                .andExpect(jsonPath("$[1].region").value("region_2"))
                .andExpect(jsonPath("$[1].population").value(2000))
                .andExpect(jsonPath("$[1].foundation").value("foundation_2"));
    }

    @SneakyThrows
    @Test
    void addCitiesToGuideSort_wrongSortEnumName() {
        Mockito.when(service.getSortingCities(Mockito.anyString(), Mockito.anyString())).thenThrow(SortEnumNameException.class);

        mockMvc.perform(post(Endpoints.API + Endpoints.ADD_SORT)
                        .param("path", "src/main/resources/data/cityguide.csv")
                        .param("sorting", "none"))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SortEnumNameException));
    }

    @SneakyThrows
    @Test
    void getMaxPopulationCity_Ok() {
        Mockito.when(service.getMaxPopulationCityDto(Mockito.anyString())).thenReturn(cityDto1);

        mockMvc.perform(get(Endpoints.API + Endpoints.GET_MAX_POPULATION_CITY)
                .param("path","src/main/resources/data/cityguide.csv"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(cityDto1.getName()));
    }

    @SneakyThrows
    @Test
    void getMaxPopulationCityShort_Ok() {
        String excepted = "490 = 11514330";
        Mockito.when(service.getMaxPopulationCityShort(Mockito.anyString())).thenReturn(excepted);

        mockMvc.perform(get(Endpoints.API + Endpoints.GET_MAX_POPULATION_CITY_SHORT)
                .param("path", "src/main/resources/data/cityguide.csv"))
                .andExpect(status().isOk())
                .andExpect(content().string(excepted));
    }

    @SneakyThrows
    @Test
    void getCountCitiesByRegion_Ok() {
        Mockito.when(service.getCountCitiesByRegion(Mockito.anyString())).thenReturn(citiesCountByRegion);

        mockMvc.perform(get(Endpoints.API + Endpoints.GET_COUNT_CITIES_BY_REGION)
                .param("path", "src/main/resources/data/cityguide.csv"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasKey("region_1")));
    }
}