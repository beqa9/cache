package AI_project.cache.services;

import AI_project.cache.entities.Country;
import AI_project.cache.mappers.CountryMapper;
import AI_project.cache.models.CountryModel;
import AI_project.cache.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "countries")
    public List<CountryModel> getAll() {
        return countryMapper.toModelList(countryRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "countries", key = "#id")
    public CountryModel getById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
        return countryMapper.toModel(country);
    }

    @Override
    @CachePut(value = "countries", key = "#result.id")
    @CacheEvict(value = "countries", allEntries = true)
    public CountryModel create(CountryModel model) {
        Country country = new Country();
        country.setName(model.name());
        country.setCode(model.code());
        return countryMapper.toModel(countryRepository.save(country));
    }
}
