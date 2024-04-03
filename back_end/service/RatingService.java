package com.topclass.schedulesystem.service;

import com.topclass.schedulesystem.model.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    public Rating saveRating(Rating rating);

    public List<Rating> getAllRatings();

    public boolean ratingExistsbyID(String professor_name);

    public Optional<Rating> getRating(String professor_name);
}
