package ru.asteises.sberbankcityguide.service;

import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.model.CityDto;

import java.util.List;
import java.util.Map;

@Service
public interface CityGuideService {

    List<CityDto> getCities(String path);

    List<CityDto> getSortingCities(String path, String sorting);

    CityDto getMaxPopulationCityDto(String path);

    String getMaxPopulationCityShort(String path);

    Map<String, Long> getCountCitiesByRegion(String path);
}
