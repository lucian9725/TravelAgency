package sda.academy.travelagency.Controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.academy.travelagency.Entity.Hotel;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Entity.Tour;
import sda.academy.travelagency.Exception.LocationNotFoundException;
import sda.academy.travelagency.Service.service.LocationService;
import sda.academy.travelagency.Service.service.TourService;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {


    @Autowired
    private LocationService locationService;
    private TourService tourService;


    @GetMapping
    public List<Location> getAllLocations()
    {
        return locationService.getAllLocations();
    }

    @PostMapping
    public ResponseEntity<?> addLocation(@RequestBody Location location) {
        try {
            Location saved = locationService.addLocation(location);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }



     @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation (@PathVariable int id) {
        try {
            locationService.deleteLocation(id);
            return ResponseEntity.ok("Location Deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLocation(@PathVariable int id, @RequestBody Location updatedLocation) {
        try {
            locationService.updateLocation(id, updatedLocation);
            return ResponseEntity.ok("Location updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
