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
    @ResponseBody
    public Optional<Rating> getRating(@RequestParam String professor_name) throws JsonProcessingException {
        if(ratingService.ratingExistsbyID(professor_name)){
            return ratingService.getRating(professor_name);
        } else {
            String uri = "http://127.0.0.1:5000/get_Professor?professor=" + professor_name.replaceAll(" ", "%20") + "&school=California%20State%20University%20San%20Marcos";

            RestTemplate restTemplate = new RestTemplate();
            String result = "";

            try{
                result = restTemplate.getForObject(uri,String.class);
            } catch(Exception e) {
                System.out.println("No results in RateMyProfessor for " + professor_name);
            }

            if(result == ""){
                Rating rating = new Rating(professor_name,"NA","NA","NA","NA","NA","NA");
                return Optional.of(rating);
            } else {
                ObjectMapper mapper = new ObjectMapper();
                Rating rating = mapper.readValue(result, Rating.class);
                ratingService.saveRating(rating);
                return Optional.of(rating);
            }





        }
    }

    @GetMapping("/getRatings")
    @ResponseBody
    public List<Rating> getRatings(@RequestParam List<String> professor_names) throws JsonProcessingException {
        List<Rating> selectedRatings= new ArrayList<>();
        for (String professorName : professor_names) {
            if (!ratingService.ratingExistsbyID(professorName)) {

                String uri = "http://127.0.0.1:5000/get_Professor?professor=" + professorName.replaceAll(" ", "%20") + "&school=California%20State%20University%20San%20Marcos";

                RestTemplate restTemplate = new RestTemplate();
                String result = "";


                try{
                    result = restTemplate.getForObject(uri,String.class);
                } catch(Exception e) {
                    System.out.println("No results in RateMyProfessor for " + professorName);
                }

                if(result == ""){
                    Rating rating = new Rating(professorName,"NA","NA","NA","NA","NA","NA");
                    selectedRatings.add(rating);
                } else {
                    ObjectMapper mapper = new ObjectMapper();
                    Rating rating = mapper.readValue(result, Rating.class);
                    ratingService.saveRating(rating);
                    if(ratingService.getRating(professorName).isPresent())
                        selectedRatings.add(ratingService.getRating(professorName).get());
                }

            } else {
                selectedRatings.add(ratingService.getRating(professorName).get());
            }

        }
        return selectedRatings;

    }
}
