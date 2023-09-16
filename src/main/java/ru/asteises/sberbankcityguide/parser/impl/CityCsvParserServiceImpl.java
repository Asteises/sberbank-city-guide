package ru.asteises.sberbankcityguide.parser.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.model.City;
import ru.asteises.sberbankcityguide.parser.CityCsvParserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
public class CityCsvParserServiceImpl implements CityCsvParserService {

    /**
     * Метод парсит файл по указанному пути.
     *
     * @param path - String
     * @return - List<List<String>>
     */
    @SneakyThrows
    @Override
    public List<City> parse(String path) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                records.add(getRecordFromLine(str));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        return parseForRecords(records);
    }

    /**
     * Метод принимает запись из парсера. Возвращает лист City.
     *
     * @param line - String
     * @return - List<String>
     */
    @Override
    public List<String> getRecordFromLine(String line) {
        return List.of(splitLine(line));
    }

    /**
     * Метод сплитит приходящую стрингу по указанным параметрам. Возвращает массив стрингов.
     *
     * @param line - String
     * @return - String[]
     */
    @Override
    public String[] splitLine(String line) {
        return line.split(";", -1);
    }

    /**
     * Метод принимает записи городов. Обрабатывает каждую запись и возвращает список City.
     *
     * @param records - List<List<String>>
     * @return - List<City>
     */
    @Override
    public List<City> parseForRecords(List<List<String>> records) {
        List<City> cities = new ArrayList<>();
        for (List<String> record : records) {
            cities.add(getCityFromRecord(record));
        }
        return cities;
    }

    /**
     * Метод принимает значения для создания объекта City. Обрабатывает их и возвращает объект City.
     *
     * @param oneCityRecord - List<String>
     * @return - City
     */
    @Override
    public City getCityFromRecord(List<String> oneCityRecord) {
        if (oneCityRecord.size() != 6) {
            throw new RuntimeException("Количество значений не соответствует записи в этой таблице");
        }
        int id = Integer.parseInt(oneCityRecord.get(0));
        String name = oneCityRecord.get(1);
        String region = oneCityRecord.get(2);
        String district = oneCityRecord.get(3);
        int population = Integer.parseInt(oneCityRecord.get(4));
        String foundation = oneCityRecord.get(5);

        City city = City.builder()
                .id(id)
                .name(checkEmptyFields(name))
                .region(checkEmptyFields(region))
                .district(checkEmptyFields(district))
                .population(population)
                .foundation(checkEmptyFields(foundation))
                .build();
        log.info("Create new city: {}", city);
        return city;
    }

    /**
     * Метод проверяет приходящее значение. В случе пустого значения подставляет значение по умолчанию.
     *
     * @param inputValue - String
     * @return - String
     */
    @Override
    public String checkEmptyFields(String inputValue) {
        if (inputValue == null || inputValue.isEmpty()) {
            return "none";
        }
        return inputValue;
    }
}
