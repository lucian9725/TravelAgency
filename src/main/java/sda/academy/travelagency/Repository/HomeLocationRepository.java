package sda.academy.travelagency.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.academy.travelagency.Entity.HomeLocation;
import sda.academy.travelagency.Entity.Location;

import java.util.List;
import java.util.Optional;

public interface HomeLocationRepository extends  JpaRepository<HomeLocation,Integer> {

    List<HomeLocation> findByCity(String city);

    List<HomeLocation> findByCountry(String country);

    List<HomeLocation> findByContinent(String continent);

    Optional<Object> findByCityAndCountryAndContinent(String city, String country, String continent);

}
