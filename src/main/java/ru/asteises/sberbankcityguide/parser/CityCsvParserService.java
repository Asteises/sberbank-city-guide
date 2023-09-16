package ru.asteises.sberbankcityguide.parser;

import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.model.City;

import java.util.List;

@Service
public interface CityCsvParserService {

    List<City> parse(String path);

    List<String> getRecordFromLine(String line);

    String[] splitLine(String line);

    List<City> parseForRecords(List<List<String>> records);

    City getCityFromRecord(List<String> oneCityRecord);

    String checkEmptyFields(String inputValue);
}
