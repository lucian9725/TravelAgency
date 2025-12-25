package sda.academy.travelagency.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TourResponseDTO {
    private String fromCity;
    private String toCity;
    private LocalDate departureDate;
    private LocalDate dateOfReturn;
    private int numberOfDay;
    private double priceForAdults;
    private double priceForChildren;
}

