package com.acciojob.bookmyshowapplication.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Getter
@Setter
@NoArgsConstructor
@Builder //for which class we want to make object, we will write @Builder on top of that class (it primarily requires @AllArgsConstructor)
@AllArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;

    private String name;

    private String address;

    private Integer noOfScreens;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL) //We are doing here bidirectional mapping
    private List<TheaterSeat> theaterSeatList = new ArrayList<>();
}




































