package sda.academy.travelagency.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.academy.travelagency.Dto.TourResponseDTO;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Entity.Tour;
import sda.academy.travelagency.Service.service.LocationService;
import sda.academy.travelagency.Service.service.TourService;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping
    public List<Tour> getAllTours()
    {
        return tourService.getAllTours();
    }



    @PostMapping
    public ResponseEntity<?> addTour(@RequestBody Tour tour) {
        try {
            Tour saved = tourService.addTour(tour);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findByTourId(@PathVariable int id) {
        try {
            Tour tour = tourService.findByTourId(id);
            return ResponseEntity.ok(tour);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tour with id " + id + " not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTour(@PathVariable Integer id,
                                             @RequestBody Tour updatedTour) {
        tourService.updateTour(id, updatedTour);
        return ResponseEntity.ok("Tour updated successfully");
    }

    @GetMapping("/search")
    public TourResponseDTO getTourByCities(
            @RequestParam String fromCity,
            @RequestParam String toCity
    ) {
        return tourService.findTourByCities(fromCity, toCity);
    }

}
