package sda.academy.travelagency.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BuyOrderRequest {
    private Integer tourId;
    private int numAdults;
    private int numChildren;
    private String customerName;
}
