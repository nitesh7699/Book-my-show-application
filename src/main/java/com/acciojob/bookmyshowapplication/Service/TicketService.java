package com.acciojob.bookmyshowapplication.Service;


import com.acciojob.bookmyshowapplication.Exceptions.SeatUnavailableException;
import com.acciojob.bookmyshowapplication.Models.*;
import com.acciojob.bookmyshowapplication.Repository.*;
import com.acciojob.bookmyshowapplication.Requests.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket bookTicket(BookTicketRequest bookTicketRequest) throws Exception{

        //1. Calculate the total cost of the tickets

        //1.1 Find the MovieEntity and TheaterEntity
        Movie movie = movieRepository.findMovieByMovieName(bookTicketRequest.getMovieName());
        Theater theater = theaterRepository.findById(bookTicketRequest.getTheaterId()).get();

        //1.1 Find the ShowEntity with this date and time
        Show show = showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(bookTicketRequest.getShowDate(),
                bookTicketRequest.getShowTime(), movie, theater);

        Integer showId = show.getShowId();
        List<ShowSeat> showSeatList = showSeatRepository.findShowSeats(showId);

        //Calculate the total Amt and if all seats mentioned are available or not
        int totalAmount = 0;
        Boolean areAllSeatsAvailable = Boolean.TRUE;
        for(String seatNo: bookTicketRequest.getRequestedSeats()){ //["5A", "5B", "5C"]
            for(ShowSeat showSeat: showSeatList){
                if(showSeat.getSeatNo().equals(seatNo)){
                    if(showSeat.getIsAvailable() == Boolean.FALSE){
                        areAllSeatsAvailable = Boolean.FALSE;
                        break;
                    }
                    totalAmount = totalAmount + showSeat.getPrice();
                }
            }
        }

        if(areAllSeatsAvailable == Boolean.FALSE){
            throw new SeatUnavailableException("The requested Seats are unavailable");
        }

        //2. make the seat booked(mark it) : Only is the seats are available, otherwise throw the exception
        for(String seatNo: bookTicketRequest.getRequestedSeats()){ //["5A", "5B", "5C"]
            for(ShowSeat showSeat: showSeatList){
                if(showSeat.getSeatNo().equals(seatNo)){
                    showSeat.setIsAvailable(Boolean.FALSE); //seats has been booked already by some user(marked it)
                }
            }
        }

        //getting the user entity from the mobNo.
        User user = userRepository.findUserByMobNo(bookTicketRequest.getMobNo()); //see here (where condition is on mobNo)

        //3. Save the Ticket Entity
        Ticket ticket = Ticket.builder().user(user)
                .movieName(bookTicketRequest.getMovieName())
                .showDate(bookTicketRequest.getShowDate())
                .theaterNameAndAddress(theater.getName()+" "+theater.getAddress())
                .showTime(bookTicketRequest.getShowTime())
                .totalAmtPaid(totalAmount)
                .build();


        ticket = ticketRepository.save(ticket);

        //4. Generate and return back the Actual ticket response
        return ticket;
    }
}













































