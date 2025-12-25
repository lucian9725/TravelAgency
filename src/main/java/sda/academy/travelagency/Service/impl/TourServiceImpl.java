package sda.academy.travelagency.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.academy.travelagency.Dto.TourResponseDTO;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Entity.Tour;
import sda.academy.travelagency.Exception.TourNotFoundException;
import sda.academy.travelagency.Repository.LocationRepository;
import sda.academy.travelagency.Repository.TourRepository;
import sda.academy.travelagency.Service.service.LocationService;
import sda.academy.travelagency.Service.service.TourService;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private LocationService locationService;
    private TourService tourService;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private TourRepository tourRepository;

    public TourServiceImpl(LocationRepository locationRepository, TourRepository tourRepository) {
        this.locationRepository = locationRepository;
        this.tourRepository = tourRepository;
    }


    public Tour addTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public List<Tour> getAllTours()
    {
        return tourRepository.findAll();
    }

    public Tour findByTourId(Integer id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));
    }

    public Tour updateTour(Integer id, Tour updatedTour) {
        Tour existingTour = tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException("Tour with id " + id + " not found"));

        existingTour.setWhereFrom(updatedTour.getWhereFrom());
        existingTour.setWhereTo(updatedTour.getWhereTo());
        existingTour.setDepartureDate(updatedTour.getDepartureDate());
        existingTour.setDateOfReturn(updatedTour.getDateOfReturn());
        existingTour.setNumberOfDay(updatedTour.getNumberOfDay());
        existingTour.setPriceForAdults(updatedTour.getPriceForAdults());
        existingTour.setPriceForChildren(updatedTour.getPriceForChildren());
        existingTour.setNumberOfAdults(updatedTour.getNumberOfAdults());
        existingTour.setNumberOfChildren(updatedTour.getNumberOfChildren());

        return tourRepository.save(existingTour); // salvăm EXISTENTUL cu id deja setat
    }

    public TourResponseDTO findTourByCities(String fromCity, String toCity) {
        Tour tour = tourRepository.findByWhereFrom_CityAndWhereTo_City(fromCity, toCity)
                .orElseThrow(() -> new TourNotFoundException(
                        "No tour found from " + fromCity + " to " + toCity));

        return new TourResponseDTO(
                tour.getWhereFrom().getCity(),
                tour.getWhereTo().getCity(),
                tour.getDepartureDate(),
                tour.getDateOfReturn(),
                tour.getNumberOfDay(),
                tour.getPriceForAdults(),
                tour.getPriceForChildren()
        );
    }

    @Override
    public void deleteTour(Integer id) {
        tourRepository.deleteById(id);
    }

}
