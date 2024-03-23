package com.acciojob.bookmyshowapplication.Requests;

import lombok.Data;

@Data
public class AddShowSeatsRequest {

    private Integer showId;
    private Integer priceOfClassicSeats;
    private Integer priceOfPremiumSeats;
}
