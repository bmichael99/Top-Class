package com.topclass.schedulesystem.service;

import com.topclass.schedulesystem.model.Professor;
import com.topclass.schedulesystem.model.Rating;
import com.topclass.schedulesystem.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService{



    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public boolean ratingExistsbyID(String professor_name){
        return ratingRepository.existsById(professor_name);
    }

    @Override
    public String deleteRating(String professor_name) {
        ratingRepository.deleteById(professor_name);
        return "Deleted " + professor_name + " ratings from database.";
    }

    @Override
    public String deleteallRatings() {
        ratingRepository.deleteAll();
        return "All entries in rating table deleted";
    }

    @Override
    public Optional<Rating> getRating(String professor_name) {
        return ratingRepository.findById(professor_name);
    }

    @Override
    public List<Rating> getManyRatings(List<String> professor_names) {
        List<Rating> allRatings = ratingRepository.findAll();

        // Logging the contents of notTakenClasses
        System.out.println("Professors:");
        professor_names.forEach(System.out::println);

        List<Rating> filteredRatings = allRatings.stream()
                .filter(rating -> professor_names.contains(rating.getProfessor_name()))
                .collect(Collectors.toList());

        // Logging the contents of filteredClasses
        System.out.println("Filtered Professors:");
        filteredRatings.forEach(rating -> System.out.println(rating.getProfessor_name()));

        return filteredRatings;
    }
}
