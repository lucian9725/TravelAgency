package sda.academy.travelagency.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id") // <- spune lui Hibernate că această coloană se numește altfel
    private Integer id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column
    private String continent;

    // Relație bidirecțională cu tururile
    @JsonIgnore
    @OneToMany(mappedBy = "whereTo", cascade = CascadeType.ALL)
    private List<Tour> tours;

    // Relație cu hotelurile
    @JsonIgnore
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Hotel> hotels;

}
