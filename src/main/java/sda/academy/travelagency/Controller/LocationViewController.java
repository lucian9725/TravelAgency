package sda.academy.travelagency.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.academy.travelagency.Entity.Location;
import sda.academy.travelagency.Exception.LocationNotFoundException;
import sda.academy.travelagency.Service.service.LocationService;

import java.util.List;

@Controller
@RequestMapping("/view/locations")
public class LocationViewController {

    @Autowired
    private LocationService locationService;

    public LocationViewController(LocationService locationService) {

        this.locationService = locationService;
    }

    @GetMapping
    public String getAllLocations(Model model) {
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations/list";  // templates/locations/list.html
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Model model) {
        model.addAttribute("location", new Location());
        return "locations/add"; // templates/locations/add.html
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addLocation(@ModelAttribute("location") Location location) {
        locationService.addLocation(location);
        return "redirect:/view/locations";
    }



    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return "redirect:/view/locations";
    }


}
