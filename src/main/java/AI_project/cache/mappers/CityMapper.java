package AI_project.cache.mappers;

import AI_project.cache.entities.City;
import AI_project.cache.models.CityModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "countryId", source = "country.id")
    @Mapping(target = "countryName", source = "country.name")
    CityModel toModel(City city);

    List<CityModel> toModelList(List<City> cities);
}