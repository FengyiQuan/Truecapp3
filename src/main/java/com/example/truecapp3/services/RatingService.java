package com.example.truecapp3.services;

import com.example.truecapp3.models.Rating;
import com.example.truecapp3.repositories.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RatingService {
  @Autowired
  RatingRepository ratingRepository;

  public void createRating(Rating rating) {
    ratingRepository.save(rating);
  }

  public Rating findRatingById(String id) {
    return ratingRepository.getOne(id);
  }

  public void deleteRatingById(String id) {
    ratingRepository.deleteById(id);
  }

  public Rating updateRatingById(String id, Rating updatedRating) {
    if (ratingRepository.findById(id).isPresent()) {
      return ratingRepository.save(updatedRating);
    } else {
      return null;
    }
  }
}
