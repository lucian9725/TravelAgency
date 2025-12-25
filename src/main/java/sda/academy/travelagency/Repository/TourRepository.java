package sda.academy.travelagency.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.academy.travelagency.Entity.HomeLocation;
import sda.academy.travelagency.Entity.Hotel;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Entity.Tour;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Integer> {

    List<Tour> findByWhereFrom(Location location);

    List<Tour> findByWhereTo(Location location);

    List<Tour> findByDepartureDateBetween(LocalDate start, LocalDate end);

    Optional<Tour> findByWhereFrom_CityAndWhereTo_City(String fromCity, String toCity);


}
