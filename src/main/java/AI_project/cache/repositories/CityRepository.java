package AI_project.cache.repositories;

import AI_project.cache.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByCountryId(Long countryId);
}