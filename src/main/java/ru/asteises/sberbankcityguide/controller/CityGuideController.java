package ru.asteises.sberbankcityguide.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.sberbankcityguide.model.CityDto;
import ru.asteises.sberbankcityguide.service.CityGuideService;
import ru.asteises.sberbankcityguide.util.endpoints.Endpoints;

import java.util.List;

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
}
