package sda.academy.travelagency.Service.service;

import sda.academy.travelagency.Entity.HomeLocation;
import sda.academy.travelagency.Entity.Hotel;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Entity.Tour;

import java.util.List;

public interface LocationService {
    public List<Location> getAllLocations();

    public Location addLocation(Location location);

    public void deleteLocation(int locationId);

    public Location updateLocation(int id, Location updatedLocation);


    Location getLocationById(Integer whereFromId);

    HomeLocation getHomeLocationById(Integer whereFromId);

}
