package ru.asteises.sberbankcityguide;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.asteises.sberbankcityguide.model.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Service
public class CityGuideServiceImpl {

    // String path = "src/main/resources/data/cityguide.csv";


    public static void main(String[] args) {


        // LIST CITY DONE
        getCitiesFromRecord(parseCsv("src/main/resources/data/cityguide.csv"));
    }

    // Парсим весь файл получая лист с листами записей
    public static List<List<String>> parseCsv(String path) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                records.add(getRecordFromLine(str));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static List<City> getCitiesFromRecord(List<List<String>> records) {
        List<City> cities = new ArrayList<>();
        for (List<String> record: records) {
            cities.add(getCityFromRecord(record));
        }
        return cities;
    }

    public static List<String> getRecordFromLine(String line) {
        String[] strings = line.split("\n");
        for(String s: strings) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ';') {
                    System.out.println("Нащли ;");
                }
                sb.append(s.charAt(i));
            }
        }
        System.out.println(Arrays.toString(strings));
        return new ArrayList<>(Arrays.asList(strings));
    }

    public static City getCityFromRecord(List<String> recordData) {
        City newCity = new City();
        newCity.setId(Integer.parseInt(recordData.get(0)));
        newCity.setName(recordData.get(1));
        newCity.setRegion(recordData.get(2));
        newCity.setDistrict(recordData.get(3));
        newCity.setPopulation(Integer.parseInt(recordData.get(4)));
        newCity.setFoundation(recordData.get(5));
        log.info("Create new city: {}", newCity);
        return newCity;
    }
}
