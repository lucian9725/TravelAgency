package sda.academy.travelagency.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "location_id")   // bidirectionala cu Location si se creaza forgein key location_id
                                        // o singura comanda poate avea o singura locatie
    private Location location;

    @Column
    private double amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @Column
    private boolean orderStatus;

    @Column
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @Column
    private int numAdults;

    @Column
    private int numChildren;

}
