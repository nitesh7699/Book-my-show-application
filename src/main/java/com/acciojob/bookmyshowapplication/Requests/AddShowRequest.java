package com.acciojob.bookmyshowapplication.Requests;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddShowRequest { //DTOs are custom written objects for encapsulation perspective, they are lighter weight and
    //it is acting like an encapsulation for the outer world

    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName;
    private Integer theaterId;

}



































































