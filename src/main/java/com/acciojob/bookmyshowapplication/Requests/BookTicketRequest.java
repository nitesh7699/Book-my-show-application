package com.acciojob.bookmyshowapplication.Requests;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookTicketRequest { //these are all the attributes that i require when i book a ticket (DTO)

    private String movieName;
    private LocalDate showDate;
    private LocalTime showTime;
    private List<String> requestedSeats;
    private Integer theaterId;
    private String mobNo;
}
