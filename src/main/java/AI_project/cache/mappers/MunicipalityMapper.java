package AI_project.cache.mappers;

import AI_project.cache.entities.Municipality;
import AI_project.cache.models.MunicipalityModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MunicipalityMapper {

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "cityName", source = "city.name")
    MunicipalityModel toModel(Municipality municipality);

    List<MunicipalityModel> toModelList(List<Municipality> municipalities);
}