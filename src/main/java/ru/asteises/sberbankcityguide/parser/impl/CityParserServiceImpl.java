package ru.asteises.sberbankcityguide.parser.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.model.City;
import ru.asteises.sberbankcityguide.parser.CityParserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
public class CityParserServiceImpl implements CityParserService {

    /**
     * Метод парсит файл по указанному пути.
     *
     * @param path - String
     * @return - List<List<String>>
     */
    @Override
    public List<City> parse(String path) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                records.add(getRecordFromLine(str));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parseForRecords(records);
    }

    @Override
    public List<String> getRecordFromLine(String line) {
        String[] strings = line.split("\n");
        return new ArrayList<>(Arrays.asList(strings));
    }

    private List<City> parseForRecords(List<List<String>> records) {
        List<City> cities = new ArrayList<>();
        for (List<String> record : records) {
            cities.add(getCityFromRecord(record));
        }
        return cities;
    }

    private City getCityFromRecord(List<String> oneCityRecord) {
        String s = oneCityRecord.get(0);
        int[] data = parseColumnsDelimiterIndex(s);
        return createCityFromData(s, data);
    }

    private int[] parseColumnsDelimiterIndex(String string) {
        int[] delimiterIndex = new int[5];
        int idx = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == ';') {
                delimiterIndex[idx] = i;
                idx++;
            }
        }
        return delimiterIndex;
    }

    private City createCityFromData(String record, int[] delimiterIndex) {
        City city = new City();

        int id = Integer.parseInt(record.substring(0, delimiterIndex[0]));
        city.setId(id);

        String name = record.substring(delimiterIndex[0] + 1, delimiterIndex[1]);
        city.setName(checkEmptyFields(name));

        String region = record.substring(delimiterIndex[1] + 1, delimiterIndex[2]);
        city.setRegion(checkEmptyFields(region));

        String district = record.substring(delimiterIndex[2] + 1, delimiterIndex[3]);
        city.setDistrict(checkEmptyFields(district));

        int population = Integer.parseInt(record.substring(delimiterIndex[3] + 1, delimiterIndex[4]));
        city.setPopulation(population);

        String foundation = record.substring(delimiterIndex[4] + 1);
        city.setFoundation(checkEmptyFields(foundation));

        log.info("Create new city: {}", city);
        return city;
    }

    private String checkEmptyFields(String string) {
        if (string.isEmpty()) {
            return "none";
        }
        return string;
    }
}
