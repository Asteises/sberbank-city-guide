package ru.asteises.sberbankcityguide.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.asteises.sberbankcityguide.model.City;
import ru.asteises.sberbankcityguide.model.CityDto;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD)
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDto toDto(City city);

    List<CityDto> toDto(List<City> cities);
}
