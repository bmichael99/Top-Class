package com.topclass.schedulesystem.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.topclass.schedulesystem.model.Rating;
import com.topclass.schedulesystem.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rating")
@CrossOrigin
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/add")
    public String add(@RequestBody Rating rating){
        ratingService.saveRating(rating);
        return "New rating is added";
    }

    @GetMapping("/getAll")
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/getRating")

    public Optional<Rating> getRating(@RequestParam String professor_name) throws JsonProcessingException {

        //Check if there is already a rating for this professor, return that rating instead of calling API
        if(ratingService.ratingExistsbyID(professor_name)){
            return ratingService.getRating(professor_name);
        } else {

            //Call to API
            String uri = "http://127.0.0.1:5000/get_Professor?professor=" + professor_name.replaceAll(" ", "%20") + "&school=California%20State%20University%20San%20Marcos";

            RestTemplate restTemplate = new RestTemplate();
            String result = "";

            try{
                result = restTemplate.getForObject(uri,String.class); //store result from ratemyprofessor
            } catch(Exception e) {
                System.out.println("No results in RateMyProfessor for " + professor_name);
            }
            List<String> tags = new ArrayList<String>();
            tags.add("NA");
            if(result == ""){ //return NA if there is no result from ratemyprofessor
                Rating rating = new Rating(professor_name,"NA","NA","NA","NA","NA","NA", tags);
                return Optional.of(rating);
            } else { //Store data from the API into the ratings table so that we do not need to repeat the same API calls.
                ObjectMapper mapper = new ObjectMapper();
                Rating rating = mapper.readValue(result, Rating.class); //Map JSON to Rating object
                ratingService.saveRating(rating); //Store rating into database
                return Optional.of(rating);
            }





        }
    }

    @GetMapping("/getRatings")
    @ResponseBody
    public List<Rating> getRatings(@RequestParam List<String> professor_names) throws JsonProcessingException {
        List<Rating> selectedRatings= new ArrayList<>();
        for (String professorName : professor_names) {
            //Check if rating exists in database for a specific professor
            if (!ratingService.ratingExistsbyID(professorName)) {

                //Call API to get relevant rating data from RateMyProfessor
                String uri = "http://127.0.0.1:5000/get_Professor?professor=" + professorName.replaceAll(" ", "%20") + "&school=California%20State%20University%20San%20Marcos";

                RestTemplate restTemplate = new RestTemplate();
                String result = "";


                try{
                    result = restTemplate.getForObject(uri,String.class);
                } catch(Exception e) {
                    System.out.println("No results in RateMyProfessor for " + professorName);
                }

                List<String> tags = new ArrayList<String>();
                tags.add("NA");
                if(result == ""){ //return NA if there is no result from ratemyprofessor
                    Rating rating = new Rating(professorName,"NA","NA","NA","NA","NA","NA", tags);
                    selectedRatings.add(rating);
                } else { //Rating data exists from ratemyprofessor for professorName
                    System.out.println(result);
                    ObjectMapper mapper = new ObjectMapper();
                    Rating rating = mapper.readValue(result, Rating.class);
                    ratingService.saveRating(rating); //Save rating data to database

                    if(ratingService.getRating(professorName).isPresent())
                        selectedRatings.add(ratingService.getRating(professorName).get()); //add rating data to return list
                }

            } else { //add rating data from our database to return list if rating already exists within database
                selectedRatings.add(ratingService.getRating(professorName).get());
            }

        }
        return selectedRatings;

    }

    @GetMapping("/deleteAll")
    public String deleteAllRatings() {
        return ratingService.deleteallRatings();
    }
}
