package sda.academy.travelagency.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.academy.travelagency.Dto.BuyOrderRequest;
import sda.academy.travelagency.Entity.Order;
import sda.academy.travelagency.Exception.OrderNotFoundException;
import sda.academy.travelagency.Service.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/buy")
    public ResponseEntity<Order> buyTour(@RequestBody BuyOrderRequest request) {
        Order order = orderService.buyTour(request.getTourId(), request.getNumAdults(), request.getNumChildren(), request.getCustomerName());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<List<Order>> getOrdersByTour(@PathVariable Integer tourId) {
        List<Order> orders = orderService.getOrderByTour(tourId);
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No orders found for tour with id " + tourId);
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{name}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable String name) {
        List<Order> orders = orderService.getOrderByCustomer(name);
        return ResponseEntity.ok(orders);
    }
}