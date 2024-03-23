package com.acciojob.bookmyshowapplication.Repository;

import com.acciojob.bookmyshowapplication.Models.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterSeatsRepository extends JpaRepository<TheaterSeat, Integer> {
}
