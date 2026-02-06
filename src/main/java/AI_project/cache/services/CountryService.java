package AI_project.cache.services;

import AI_project.cache.models.CountryModel;

import java.util.List;

public interface CountryService {

    List<CountryModel> getAll();

    CountryModel getById(Long id);

    CountryModel create(CountryModel model);
}