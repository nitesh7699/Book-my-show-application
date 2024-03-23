package com.acciojob.bookmyshowapplication.Models;


import com.acciojob.bookmyshowapplication.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theater_seats")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //for autoGeneration
    private Integer theaterSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @JoinColumn
    @ManyToOne //unidirectional mapping
    private Theater theater; // parent of TheaterSeat is Theater

}

























