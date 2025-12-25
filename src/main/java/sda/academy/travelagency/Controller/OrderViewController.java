package sda.academy.travelagency.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.academy.travelagency.Dto.BuyOrderRequest;
import sda.academy.travelagency.Entity.Order;
import sda.academy.travelagency.Service.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/orders-view")
public class OrderViewController {

    private final OrderService orderService;

    public OrderViewController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public String showOrders(Model model) {
        List<Order> orders = orderService.getAllOrders(); // trebuie să existe în service
        model.addAttribute("orders", orders);
        model.addAttribute("buyOrderRequest", new BuyOrderRequest());
        return "orders-view";
    }


    @PostMapping("/buy")
    public String buyTour(@ModelAttribute BuyOrderRequest request, Model model) {
        try {
            orderService.buyTour(
                    request.getTourId(),
                    request.getNumAdults(),
                    request.getNumChildren(),
                    request.getCustomerName()
            );
            model.addAttribute("successMessage", "Comanda a fost plasată cu succes!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Eroare la plasarea comenzii: " + e.getMessage());
        }

        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("buyOrderRequest", new BuyOrderRequest());
        return "orders-view";
    }
}
