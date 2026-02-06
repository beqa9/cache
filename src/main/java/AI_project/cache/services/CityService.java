package AI_project.cache.services;

import AI_project.cache.models.CityModel;

import java.util.List;

public interface CityService {

    List<CityModel> getAll();

    List<CityModel> getByCountry(Long countryId);

    CityModel create(String name, Long countryId);
}