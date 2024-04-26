package com.topclass.schedulesystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
public class Rating {

    @Id
    @JsonProperty("name")
    private String professor_name;

    @JsonProperty("department")
    private String department;

    @JsonProperty("difficulty")
    private String difficulty;

    @JsonProperty("rating")
    private String rating;

    @JsonProperty("school")
    private String school;

    @JsonProperty("totalratings")
    private String total_ratings;

    @JsonProperty("would_take_again")
    private String would_take_again;

    @JsonProperty("topTags")
    @ElementCollection
    private List<String> topTags;

    public Rating() {
    }

    public Rating(String professor_name, String department, String difficulty, String rating, String school, String total_ratings, String would_take_again, List<String> topTags) {
        this.professor_name = professor_name;
        this.department = department;
        this.difficulty = difficulty;
        this.rating = rating;
        this.school = school;
        this.total_ratings = total_ratings;
        this.would_take_again = would_take_again;
        this.topTags = topTags;
    }

    public String getProfessor_name() {
        return professor_name;
    }

    public void setProfessor_name(String professor_name) {
        this.professor_name = professor_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTotal_ratings() {
        return total_ratings;
    }

    public void setTotal_ratings(String total_ratings) {
        this.total_ratings = total_ratings;
    }

    public String getWould_take_again() {
        return would_take_again;
    }

    public void setWould_take_again(String would_take_again) {
        this.would_take_again = would_take_again;
    }

    public List<String> getTopTags() {
        return topTags;
    }

    public void setTopTags(List<String> topTags) {
        this.topTags = topTags;
    }


}
