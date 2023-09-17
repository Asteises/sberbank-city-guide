package ru.asteises.sberbankcityguide.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.mapper.CityMapper;
import ru.asteises.sberbankcityguide.model.City;
import ru.asteises.sberbankcityguide.model.CityDto;
import ru.asteises.sberbankcityguide.parser.CityCsvParserService;
import ru.asteises.sberbankcityguide.service.CityGuideService;
import ru.asteises.sberbankcityguide.service.CityGuideSorting;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CityGuideServiceImpl implements CityGuideService {

    private final CityCsvParserService cvsParserService;
    private final CityGuideSorting sortingService;

    /**
     * Метод возвращает список городов из файла по ссылке.
     *
     * @param path - String
     * @return - List<CityDto>
     */
    @Override
    public List<CityDto> getCities(String path) {
        List<City> cities = getCvsParseCities(path);
        return CityMapper.INSTANCE.toDto(cities);
    }

    @Override
    public List<CityDto> getSortingCities(String path, String sorting) {
        List<City> cities = getCvsParseCities(path);
        cities = sortingService.getSortingCities(cities, sorting);
        return CityMapper.INSTANCE.toDto(cities);
    }

    private List<City> getCvsParseCities(String path) {
        return cvsParserService.parse(path);
    }
}
