package sda.academy.travelagency.Service.service;

import sda.academy.travelagency.Dto.TourResponseDTO;
import sda.academy.travelagency.Entity.Tour;

import java.util.List;

public interface TourService {

    public Tour addTour(Tour tour);
    public List<Tour> getAllTours();
    public Tour findByTourId(Integer id);
    public Tour updateTour(Integer id, Tour tour);
    TourResponseDTO findTourByCities(String fromCity, String toCity);

    void deleteTour(Integer tourId);
}
