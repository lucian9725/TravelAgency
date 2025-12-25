package sda.academy.travelagency.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sda.academy.travelagency.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}