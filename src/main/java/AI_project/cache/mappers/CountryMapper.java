package AI_project.cache.mappers;

import AI_project.cache.entities.Country;
import AI_project.cache.models.CountryModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryModel toModel(Country country);

    List<CountryModel> toModelList(List<Country> countries);
}
