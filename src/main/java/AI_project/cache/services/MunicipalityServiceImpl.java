package AI_project.cache.services;

import AI_project.cache.entities.Municipality;
import AI_project.cache.mappers.MunicipalityMapper;
import AI_project.cache.models.MunicipalityModel;
import AI_project.cache.repositories.CityRepository;
import AI_project.cache.repositories.MunicipalityRepository;
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
public class MunicipalityServiceImpl implements MunicipalityService {

    private final MunicipalityRepository municipalityRepository;
    private final CityRepository cityRepository;
    private final MunicipalityMapper municipalityMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "municipalities")
    public List<MunicipalityModel> getAll() {
        return municipalityMapper.toModelList(municipalityRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "municipalities", key = "'city:' + #cityId")
    public List<MunicipalityModel> getByCity(Long cityId) {
        return municipalityMapper.toModelList(
                municipalityRepository.findByCityId(cityId)
        );
    }

    @Override
    @CacheEvict(value = "municipalities", allEntries = true)
    public MunicipalityModel create(String name, Long cityId) {
        Municipality municipality = new Municipality();
        municipality.setName(name);
        municipality.setCity(
                cityRepository.findById(cityId)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "City not found"))
        );

        return municipalityMapper.toModel(
                municipalityRepository.save(municipality)
        );
    }
}