package ru.asteises.sberbankcityguide.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityGuideController {

    @PostMapping("/add")
    public ResponseEntity<List<String>> addCitiesToGuide(String path) {
        return null;
    }
}
