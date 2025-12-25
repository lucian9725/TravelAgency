package sda.academy.travelagency.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.academy.travelagency.Entity.Role;

@Repository  // e o adnotare in plus fiindca oricum Interfata mea e un bean!
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}