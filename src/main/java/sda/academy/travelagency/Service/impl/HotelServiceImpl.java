package sda.academy.travelagency.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.academy.travelagency.Entity.Hotel;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Exception.HotelNotFoundException;
import sda.academy.travelagency.Exception.LocationNotFoundException;
import sda.academy.travelagency.Repository.HotelRepository;
import sda.academy.travelagency.Repository.LocationRepository;
import sda.academy.travelagency.Service.service.HotelService;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private LocationRepository locationRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, LocationRepository locationRepository) {
        this.hotelRepository = hotelRepository;
        this.locationRepository = locationRepository;
    }

    public List<Hotel> getAllHotels()
    {
        return hotelRepository.findAll();
    }
/*
    public Hotel findByHotelId(int hotelId)
    {
        return hotelRepository.findByHotelId(hotelId);
    }

 */

    @Override
    public Hotel addHotel(Hotel hotel) {
        int locationId = hotel.getLocation().getId();

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location with id " + locationId + " does not exist"));

        hotel.setLocation(location); // te asiguri că folosești entitatea completă
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(int hotelId) {
        Hotel existingHotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new HotelNotFoundException("Hotel with id" + hotelId + " not found"));
        hotelRepository.delete(existingHotel);
    }

    @Override
    public Hotel updateHotel(int id, Hotel updatedHotel) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id " + id + " not found"));

        // Verificare locație
        if (updatedHotel.getLocation() != null) {
            int locationId = updatedHotel.getLocation().getId();
            Location location = locationRepository.findById(locationId)
                    .orElseThrow(() -> new LocationNotFoundException("Location with id " + locationId + " does not exist"));
            existingHotel.setLocation(location);
        }

        existingHotel.setHotelName(updatedHotel.getHotelName());
        existingHotel.setDescription(updatedHotel.getDescription());
        existingHotel.setStandard(updatedHotel.getStandard());

        return hotelRepository.save(existingHotel);
    }


    public Hotel findByHotelId(int id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
    }


}
