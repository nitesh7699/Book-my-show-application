package com.acciojob.bookmyshowapplication.Repository;

import com.acciojob.bookmyshowapplication.Models.Ticket;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {




}
