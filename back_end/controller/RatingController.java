package com.topclass.schedulesystem.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.topclass.schedulesystem.model.Rating;
import com.topclass.schedulesystem.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.text.html.Option;
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
            String result = restTemplate.getForObject(uri,String.class);
            ObjectMapper mapper = new ObjectMapper();
            Rating rating = mapper.readValue(result, Rating.class);

            ratingService.saveRating(rating);

            return Optional.of(rating);
        }


    }
}
