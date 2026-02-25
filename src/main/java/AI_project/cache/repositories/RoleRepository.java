package AI_project.cache.repositories;

import AI_project.cache.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByName(String name);
}