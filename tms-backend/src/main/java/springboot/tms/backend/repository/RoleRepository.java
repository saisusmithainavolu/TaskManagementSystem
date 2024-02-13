package springboot.tms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.tms.backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
