package sda.academy.travelagency.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.academy.travelagency.Entity.Order;
import sda.academy.travelagency.Entity.Tour;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerName(String customerName);

    List<Order> findByOrderStatus(boolean status);

    List<Order> findByOrderDate(LocalDate orderDate);

    List<Order> findOrderById(Integer id);

    List<Order> findByTour(Tour tour);
}
