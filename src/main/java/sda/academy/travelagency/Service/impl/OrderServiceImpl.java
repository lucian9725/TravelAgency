package sda.academy.travelagency.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.academy.travelagency.Entity.Order;
import sda.academy.travelagency.Entity.Tour;
import sda.academy.travelagency.Exception.OrderNotFoundException;
import sda.academy.travelagency.Exception.TourNotFoundException;
import sda.academy.travelagency.Repository.OrderRepository;
import sda.academy.travelagency.Repository.TourRepository;
import sda.academy.travelagency.Service.service.OrderService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final TourRepository tourRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(TourRepository tourRepository, OrderRepository orderRepository) {
        this.tourRepository = tourRepository;
        this.orderRepository = orderRepository;
    }

    public Order buyTour(Integer tourId, int numAdults, int numChildren, String customerName) {

        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        if (numAdults > tour.getNumberOfAdults())
            throw new RuntimeException("Not enough adult seats available");

        if (numChildren > tour.getNumberOfChildren())
            throw new RuntimeException("Not enough children seats available");

        double totalAmount = numAdults * tour.getPriceForAdults()
                + numChildren * tour.getPriceForChildren();

        Order order = new Order();
        order.setTour(tour);
        order.setNumAdults(numAdults);
        order.setNumChildren(numChildren);
        order.setCustomerName(customerName);
        order.setAmount(totalAmount);
        order.setOrderDate(LocalDate.now());

        // actualizare locuri
        tour.setNumberOfAdults(tour.getNumberOfAdults() - numAdults);
        tour.setNumberOfChildren(tour.getNumberOfChildren() - numChildren);

        tourRepository.save(tour);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderByTour(Integer tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new TourNotFoundException("Tour with id " + tourId + " not found"));

        List<Order> orders = orderRepository.findByTour(tour);

        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No orders found for tour with id " + tourId);
        }

        return orders;
    }


    public List<Order> getOrderByCustomer(String name) {
        return orderRepository.findByCustomerName(name);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }



}
