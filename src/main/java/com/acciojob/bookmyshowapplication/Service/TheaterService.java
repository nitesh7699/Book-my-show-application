package com.acciojob.bookmyshowapplication.Service;


import com.acciojob.bookmyshowapplication.Enums.SeatType;
import com.acciojob.bookmyshowapplication.Models.Theater;
import com.acciojob.bookmyshowapplication.Models.TheaterSeat;
import com.acciojob.bookmyshowapplication.Repository.TheaterRepository;
import com.acciojob.bookmyshowapplication.Repository.TheaterSeatsRepository;
import com.acciojob.bookmyshowapplication.Requests.AddTheaterRequest;
import com.acciojob.bookmyshowapplication.Requests.AddTheaterSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatsRepository theaterSeatsRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest){

        //Ideally i should convert this AddRequest (DTO) to an Entity

        //How to do this ??
        //Use a Constructor to create an Object : Generally constructors are not available

        //OLD WAY ->
        //Theater theater = new Theater(); // we have this entity object
        //theater.setName(addTheaterRequest.getName());
        //theater.setAddress(addTheaterRequest.getAddress());
        //theater.setNoOfScreens(addTheaterRequest.getNoOfScreens());

        //Modern way to create an object is ->
        Theater theater = Theater.builder().address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
                .name(addTheaterRequest.getName())
                .build();

        //Save the Entity to the DB
        theater = theaterRepository.save(theater);
        return "The theater is with a theaterId "+ theater.getTheaterId();
    }

    public String addTheaterSeats(AddTheaterSeatsRequest addTheaterSeatsRequest){

        int noOfClassicSeats = addTheaterSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = addTheaterSeatsRequest.getNoOfPremiumSeats();

        Integer theaterId = addTheaterSeatsRequest.getTheaterId();
        Theater theater = theaterRepository.findById(theaterId).get();

        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        int classicSeatsCounter = 1;
        char ch = 'A';
        int rowNo =  1;
        while(classicSeatsCounter <= noOfClassicSeats){
            String seatNo = rowNo +""+ch+"";
            TheaterSeat theaterSeat = TheaterSeat.builder()
                                     .seatNo(seatNo)
                                     .seatType(SeatType.CLASSIC)
                                     .theater(theater)
                                     .build();

            theaterSeatList.add(theaterSeat);
            ch++;
            if(classicSeatsCounter%5 ==0){
                rowNo+=1;
                ch='A';
            }
            classicSeatsCounter++;
        }

        int premiumSeatsCounter = 1;
        ch = 'A';
        if(classicSeatsCounter%5 != 1) {
            rowNo = rowNo + 1;
        }
        while(premiumSeatsCounter <= noOfPremiumSeats){
            String seatNo = rowNo +""+ch+"";
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .theater(theater) //setting the unidirectional
                    .seatType(SeatType.PREMIUM)
                    .build();

            theaterSeatList.add(theaterSeat);
            ch++; //A,B,C,D,E,A,B,.....
            if(premiumSeatsCounter%5==0){
                rowNo+=1;
                ch = 'A';
            }
            premiumSeatsCounter++;
        }

        //in the theater entity, there is an attribute .set which we can use to Set
        theater.setTheaterSeatList(theaterSeatList);

        //save all in one Go
        //theaterSeatsRepository.saveAll(theaterSeatList); //---->> we can skip saving the theaterSeats,
        //Theater seats will get automatically saved
        //coz of cascading property written in the parent table

        //save also since we have made changes in Theater entity
        theaterRepository.save(theater);

        return "Theater seats have been generated!!!";
    }
}

























































