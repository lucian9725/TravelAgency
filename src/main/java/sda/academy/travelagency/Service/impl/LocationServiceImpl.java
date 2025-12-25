package sda.academy.travelagency.Service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.academy.travelagency.Entity.HomeLocation;
import sda.academy.travelagency.Entity.Hotel;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Entity.Tour;
import sda.academy.travelagency.Exception.HotelNotFoundException;
import sda.academy.travelagency.Exception.LocationNotFoundException;
import sda.academy.travelagency.Repository.HomeLocationRepository;
import sda.academy.travelagency.Repository.LocationRepository;
import sda.academy.travelagency.Repository.TourRepository;
import sda.academy.travelagency.Service.service.LocationService;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;
    private TourRepository tourRepository;
    private HomeLocationRepository homeLocationRepository;

    public LocationServiceImpl(HomeLocationRepository homeLocationRepository, TourRepository tourRepository, LocationRepository locationRepository) {
        this.homeLocationRepository = homeLocationRepository;
        this.tourRepository = tourRepository;
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations()
    {
        return locationRepository.findAll();
    }

    public Location addLocation(Location location) {
        // verificăm dacă există deja o locație identică
        locationRepository.findByCityAndCountryAndContinent(
                location.getCity(),
                location.getCountry(),
                location.getContinent()
        ).ifPresent(existing -> {
            throw new RuntimeException("Location already exists: " );
        });

        return locationRepository.save(location);
    }

    public void deleteLocation(int locationId) {
        Location existingLocation = locationRepository.findById(locationId)
                .orElseThrow(()-> new LocationNotFoundException("Location with id" + locationId + " not found"));
        locationRepository.delete(existingLocation);
    }


    public Location updateLocation(int id, Location updatedLocation) {
        Location existingLocation =  locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location with id " + id + " not found"));
        existingLocation.setContinent(updatedLocation.getContinent());
        existingLocation.setCountry(updatedLocation.getCountry());
        existingLocation.setCity(updatedLocation.getCity());

        return locationRepository.save(existingLocation);
    }

    @Override
    public Location getLocationById(Integer id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location with id " + id + " not found"));
    }

    @Override
    public HomeLocation getHomeLocationById(Integer id) {
        return homeLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location with id " + id + " not found"));
    }







}
