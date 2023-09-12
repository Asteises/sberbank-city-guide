package ru.asteises.sberbankcityguide.parser;

import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.model.City;

import java.util.List;

@Service
public interface CityParserService {

    List<City> parse(String path);

    List<String> getRecordFromLine(String line);
}
