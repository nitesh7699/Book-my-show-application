package com.acciojob.bookmyshowapplication.Models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;

    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private String theaterNameAndAddress;

    private Integer totalAmtPaid;

    @JoinColumn
    @ManyToOne //Many tickets -> One User (so ManyToOne)
    private User user;
}


























