package sda.academy.travelagency.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "where_from_id")
    private Location whereFrom;

    @ManyToOne
    @JoinColumn(name = "where_to_id")
    private Location whereTo;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfReturn;

    @Column
    private int numberOfDay;

    @Column
    private double priceForAdults;

    @Column
    private double priceForChildren;

    @Column
    private int numberOfAdults;

    @Column
    private int numberOfChildren;

    @OneToMany(mappedBy = "tour")
    @JsonIgnore
    private List<Order> orders;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;



}
