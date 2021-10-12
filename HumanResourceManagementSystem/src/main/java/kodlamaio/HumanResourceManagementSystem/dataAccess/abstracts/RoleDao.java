package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.abstracts.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {

    Role findByRoleName(String roleName);
    boolean existsByRoleName(String roleName);

}
