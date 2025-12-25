package sda.academy.travelagency.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String hotelName;

    @Column
    private int standard;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


}
