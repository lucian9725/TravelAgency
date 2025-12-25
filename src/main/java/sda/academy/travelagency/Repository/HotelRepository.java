package sda.academy.travelagency.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sda.academy.travelagency.Entity.Hotel;
import sda.academy.travelagency.Entity.Location;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {


    List<Hotel> findByLocation(Location location);


    @Query(value = " SELECT h FROM Hotel h WHERE h.hotelName =: name",nativeQuery = false)
    List<Hotel> findByHotelName(String name);



}
