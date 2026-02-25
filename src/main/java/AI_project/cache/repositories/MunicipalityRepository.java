package AI_project.cache.repositories;

import AI_project.cache.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipalityRepository extends BaseRepository<Municipality> {

    List<Municipality> findByCityId(Long cityId);
}