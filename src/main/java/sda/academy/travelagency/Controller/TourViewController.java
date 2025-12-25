package sda.academy.travelagency.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.academy.travelagency.Dto.BuyOrderRequest;
import sda.academy.travelagency.Entity.HomeLocation;
import sda.academy.travelagency.Entity.Hotel;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Entity.Tour;
import sda.academy.travelagency.Exception.TourNotFoundException;
import sda.academy.travelagency.Service.service.LocationService;
import sda.academy.travelagency.Service.service.OrderService;
import sda.academy.travelagency.Service.service.TourService;
import sda.academy.travelagency.Service.service.HotelService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("/tours-view")
public class TourViewController {

    private final TourService tourService;
    private final OrderService orderService;
    private final LocationService locationService;
    private final HotelService hotelService;

    public TourViewController(TourService tourService, OrderService orderService,
                              LocationService locationService, HotelService hotelService) {
        this.tourService = tourService;
        this.orderService = orderService;
        this.locationService = locationService;
        this.hotelService = hotelService;
    }

    @GetMapping
    public String getAllTours(Model model) {
        model.addAttribute("tours", tourService.getAllTours());
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("hotels", hotelService.getAllHotels()); // 🔹 adăugat
        model.addAttribute("buyOrderRequest", new BuyOrderRequest());
        return "tours-view";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addTour(
            @RequestParam Integer whereFromId,
            @RequestParam Integer whereToId,
            @RequestParam Integer hotelId, // 🔹 adăugat
            @RequestParam String departureDate,
            @RequestParam String dateOfReturn,
            @RequestParam int numberOfDay,
            @RequestParam double priceForAdults,
            @RequestParam double priceForChildren,
            @RequestParam int numberOfAdults,
            @RequestParam int numberOfChildren,
            Model model
    ) {
        try {
            Location from = locationService.getLocationById(whereFromId);
            Location to = locationService.getLocationById(whereToId);
            Hotel hotel = hotelService.findByHotelId(hotelId); // 🔹 obținere hotel

            Tour tour = new Tour();
            tour.setWhereFrom(from);
            tour.setWhereTo(to);
            tour.setHotel(hotel); // 🔹 setare hotel


            try {
                tour.setDepartureDate(LocalDate.parse(departureDate));
                tour.setDateOfReturn(LocalDate.parse(dateOfReturn));
            } catch (DateTimeParseException e) {
                model.addAttribute("errorMessage", "Format dată invalid. Folosește YYYY-MM-DD.");
                model.addAttribute("tours", tourService.getAllTours());
                model.addAttribute("locations", locationService.getAllLocations());
                model.addAttribute("hotels", hotelService.getAllHotels());
                return "tours-view";
            }

            tour.setNumberOfDay(numberOfDay);
            tour.setPriceForAdults(priceForAdults);
            tour.setPriceForChildren(priceForChildren);
            tour.setNumberOfAdults(numberOfAdults);
            tour.setNumberOfChildren(numberOfChildren);

            tourService.addTour(tour);
            model.addAttribute("successMessage", "Tur adăugat cu succes!");
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Eroare la adăugare tur: " + ex.getMessage());
        }

        model.addAttribute("tours", tourService.getAllTours());
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("buyOrderRequest", new BuyOrderRequest());
        return "tours-view";
    }

    @PostMapping("/buy")
    public String buyTour(
            @RequestParam Integer tourId,
            @RequestParam int numAdults,
            @RequestParam int numChildren,
            @RequestParam String customerName,
            Model model
    ) {
        try {
            orderService.buyTour(tourId, numAdults, numChildren, customerName);
            model.addAttribute("successMessage", "Comanda a fost plasată cu succes!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Eroare la cumpărare: " + e.getMessage());
        }

        model.addAttribute("tours", tourService.getAllTours());
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("buyOrderRequest", new BuyOrderRequest());
        return "tours-view";
    }

    @PostMapping("/delete/{tourId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTour(@PathVariable Integer tourId, Model model) {
        try {
            tourService.deleteTour(tourId);
            model.addAttribute("successMessage", "Tur șters cu succes!");
        } catch (TourNotFoundException e) {
            model.addAttribute("errorMessage", "Turul nu a fost găsit: " + e.getMessage());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Eroare la ștergerea turului: " + e.getMessage());
        }

        model.addAttribute("tours", tourService.getAllTours());
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("buyOrderRequest", new BuyOrderRequest());
        return "tours-view";
    }
}
