package sda.academy.travelagency.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.academy.travelagency.Entity.Hotel;
import sda.academy.travelagency.Exception.HotelNotFoundException;
import sda.academy.travelagency.Service.service.HotelService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<Hotel> getAllHotels()
    {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByHotelId( @PathVariable int id)
    {
        Hotel hotel = hotelService.findByHotelId(id);
        return ResponseEntity.ok(hotel);
    }

    @PostMapping
    public ResponseEntity<?> addHotel(@RequestBody Hotel hotel) {
        try {
            Hotel savedHotel = hotelService.addHotel(hotel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotel(@PathVariable int id, @RequestBody Hotel updatedHotel) {
        try {
            hotelService.updateHotel(id, updatedHotel);
            return ResponseEntity.ok("Hotel updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotel (@PathVariable int id) {
        try {
            hotelService.deleteHotel(id);
            return ResponseEntity.ok("Hotel Deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
