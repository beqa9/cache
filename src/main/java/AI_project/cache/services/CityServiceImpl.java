package AI_project.cache.services;

import AI_project.cache.entities.City;
import AI_project.cache.mappers.CityMapper;
import AI_project.cache.models.CityModel;
import AI_project.cache.repositories.CityRepository;
import AI_project.cache.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "cities")
    public List<CityModel> getAll() {
        return cityMapper.toModelList(cityRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "cities", key = "'country:' + #countryId")
    public List<CityModel> getByCountry(Long countryId) {
        return cityMapper.toModelList(
                cityRepository.findByCountryId(countryId)
        );
    }

    @Override
    @CacheEvict(value = "cities", allEntries = true)
    public CityModel create(String name, Long countryId) {
        City city = new City();
        city.setName(name);
        city.setCountry(
                countryRepository.findById(countryId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Country not found"))
        );

        return cityMapper.toModel(cityRepository.save(city));
    }
}