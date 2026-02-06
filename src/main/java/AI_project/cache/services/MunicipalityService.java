package AI_project.cache.services;

import AI_project.cache.models.MunicipalityModel;

import java.util.List;

public interface MunicipalityService {

    List<MunicipalityModel> getAll();

    List<MunicipalityModel> getByCity(Long cityId);

    MunicipalityModel create(String name, Long cityId);
}