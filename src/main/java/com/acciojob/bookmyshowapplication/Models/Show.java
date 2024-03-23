package com.acciojob.bookmyshowapplication.Models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows")
@Data //@Getter, @Setter ,@AllArgsConstructor,@NoArgsConstructor are taken care
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

    private LocalDate showDate; //"YYYY-MM-DD"

    private LocalTime showTime; //"HH:MM:SS"

    @ManyToOne
    @JoinColumn
    private Movie movie;

    @ManyToOne
    @JoinColumn
    private Theater theater;

}















































