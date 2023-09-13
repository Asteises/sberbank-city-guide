package ru.asteises.sberbankcityguide.service;

import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.model.CityDto;

import java.util.List;

@Service
public interface CityGuideService {

    List<CityDto> getCities(String path);
}
