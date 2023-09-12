package ru.asteises.sberbankcityguide.CityGuideService;

import ru.asteises.sberbankcityguide.model.CityDto;

import java.util.List;

public interface CityGuideService {

    List<CityDto> getCities(String path);
}
