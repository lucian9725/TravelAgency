package sda.academy.travelagency.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.academy.travelagency.Entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByCity(String city);

    List<Location> findByCountry(String country);

    List<Location> findByContinent(String continent);

    Optional<Object> findByCityAndCountryAndContinent(String city, String country, String continent);
}
