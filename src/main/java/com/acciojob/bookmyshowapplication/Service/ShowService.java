package com.acciojob.bookmyshowapplication.Service;


import com.acciojob.bookmyshowapplication.Enums.SeatType;
import com.acciojob.bookmyshowapplication.Models.*;
import com.acciojob.bookmyshowapplication.Repository.MovieRepository;
import com.acciojob.bookmyshowapplication.Repository.ShowRepository;
import com.acciojob.bookmyshowapplication.Repository.ShowSeatRepository;
import com.acciojob.bookmyshowapplication.Repository.TheaterRepository;
import com.acciojob.bookmyshowapplication.Requests.AddShowRequest;
import com.acciojob.bookmyshowapplication.Requests.AddShowSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String addShows(AddShowRequest showRequest){

        //I need to get the movie Entity and Theater entity :

        Movie movie = movieRepository.findMovie(showRequest.getMovieName());

        Theater theater = theaterRepository.findById(showRequest.getTheaterId()).get();

        //Build an object of the Show Entity and save it to the DB
        Show show = Show.builder()
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .movie(movie) //setting the Movie entity
                .theater(theater) //setting the Theater entity
                .build();

        show = showRepository.save(show);
        return "The show has been saved to the DB with showId " + show;
    }

    public String addShowSeats(AddShowSeatsRequest showSeatsRequest){

        Integer showId = showSeatsRequest.getShowId();
        Show show = showRepository.findById(showId).get();

        Theater theater = show.getTheater();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        //Your goal is generation of show seat list
        List<ShowSeat> showSeatList = new ArrayList<>();

        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat = ShowSeat.builder()
                    .seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .isAvailable(Boolean.TRUE)
                    .show(show)
                    .build();

            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatsRequest.getPriceOfClassicSeats());
            }
            else{
                showSeat.setPrice(showSeatsRequest.getPriceOfPremiumSeats());
            }

            showSeatList.add(showSeat);
        }

        showSeatRepository.saveAll(showSeatList);
        return "Show seats have been generated for the given showId";

    }

}












































































































