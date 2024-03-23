package com.acciojob.bookmyshowapplication.Controllers;


import com.acciojob.bookmyshowapplication.Models.Movie;
import com.acciojob.bookmyshowapplication.Service.MovieService;
import com.acciojob.bookmyshowapplication.Requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie){
        String response = movieService.addMovie(movie);
        return response;
    }

//    @PutMapping("/updateMovie")
//    public String updateMovieAttributes(@RequestParam("movieId") Integer movieId,
//                                        @RequestParam("duration") double duration,
//                                        @RequestParam("rating") double rating){
//
//        //Not a good way, as it is exposing the values of the parameters
//
//    }

    @PutMapping("/updateMovieAttributes")
    public String updateMovieAttributes(@RequestBody UpdateMovieRequest movieRequest){ //here we are passing only the custom attributes that the client needs

        //You will make sure that only relevant attributes are exposed to the client

        String result = movieService.updateMovieAttributes(movieRequest);
        return result;

    }
}


























