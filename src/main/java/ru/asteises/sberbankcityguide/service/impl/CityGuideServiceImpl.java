package ru.asteises.sberbankcityguide.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.mapper.CityMapper;
import ru.asteises.sberbankcityguide.model.City;
import ru.asteises.sberbankcityguide.model.CityDto;
import ru.asteises.sberbankcityguide.parser.CityParserService;
import ru.asteises.sberbankcityguide.service.CityGuideService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CityGuideServiceImpl implements CityGuideService {

    private final CityParserService parser;

    /**
     * Метод парсит файл по ссылке. Возвращает список CityDto.
     *
     * @param path - String
     * @return - List<CityDto>
     */
    public List<CityDto> getCities(String path) {
        List<City> cities = parser.parse(path);
        return CityMapper.INSTANCE.toDto(cities);
    }
}
