package sda.academy.travelagency.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.academy.travelagency.Entity.Hotel;
import sda.academy.travelagency.Service.service.HotelService;
import sda.academy.travelagency.Service.service.LocationService;

import java.util.List;

@Controller
@RequestMapping("/view/hotels")
public class HotelViewController {

    @Autowired
    private HotelService hotelService;
    private LocationService locationService;

    public HotelViewController(HotelService hotelService, LocationService locationService) {
        this.hotelService = hotelService;
        this.locationService = locationService;
    }

    // Pagina cu toate hotelurile
    @GetMapping
    public String getAllHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "hotels/list-hotel"; // -> Thymeleaf template: hotels-list.html
    }



    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("locations", locationService.getAllLocations()); // <- adăugăm locațiile
        return "hotels/add-hotel";
    }


    // Salvarea hotelului din formular
    @PostMapping("/add")
    public String saveHotel(@ModelAttribute("hotel") Hotel hotel) {
        hotelService.addHotel(hotel);
        return "redirect:/view/hotels"; // Redirecționează la lista hotelurilor
    }

    // Ștergere hotel
    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable int id) {
        hotelService.deleteHotel(id);
        return "redirect:/view/hotels";
    }
}
