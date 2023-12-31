package ru.asteises.sberbankcityguide.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asteises.sberbankcityguide.model.CityDto;
import ru.asteises.sberbankcityguide.service.CityGuideService;
import ru.asteises.sberbankcityguide.util.endpoints.Endpoints;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(Endpoints.API)
public class CityGuideController {

    private final CityGuideService cityGuideService;

    @PostMapping(Endpoints.ADD)
    public ResponseEntity<List<CityDto>> addCitiesToGuide(@RequestParam String path) {
        log.info("Получили путь к файлу: {}", path);
        return new ResponseEntity<>(cityGuideService.getCities(path), HttpStatus.OK);
    }

    @PostMapping(Endpoints.ADD_SORT)
    public ResponseEntity<List<CityDto>> addCitiesToGuideSort(@RequestParam String path, @RequestParam String sorting) {
        log.info("Получили путь к файлу: {}", path);
        log.info("Получили вариант сортировки: {}", sorting);
        return new ResponseEntity<>(cityGuideService.getSortingCities(path, sorting), HttpStatus.OK);
    }

    @GetMapping(Endpoints.GET_MAX_POPULATION_CITY)
    public ResponseEntity<CityDto> getMaxPopulationCity(@RequestParam String path) {
        log.info("Получили путь к файлу: {}", path);
        return new ResponseEntity<>(cityGuideService.getMaxPopulationCityDto(path), HttpStatus.OK);
    }

    @GetMapping(Endpoints.GET_MAX_POPULATION_CITY_SHORT)
    public ResponseEntity<String> getMaxPopulationCityShort(@RequestParam String path) {
        return new ResponseEntity<>(cityGuideService.getMaxPopulationCityShort(path), HttpStatus.OK);
    }

    @GetMapping(Endpoints.GET_COUNT_CITIES_BY_REGION)
    public ResponseEntity<Map<String, Long>> getCountCitiesByRegion(@RequestParam String path) {
        log.info("Получили путь к файлу: {}", path);
        return new ResponseEntity<>(cityGuideService.getCountCitiesByRegion(path), HttpStatus.OK);
    }
}
