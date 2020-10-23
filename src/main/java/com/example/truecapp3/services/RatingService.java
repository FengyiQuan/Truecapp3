package com.example.truecapp3.services;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Rating;
import com.example.truecapp3.repositories.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RatingService {
  @Autowired
  RatingRepository ratingRepository;

  public Rating createRating(int star, String msg) throws ServiceError {
    if (msg == null || msg.isEmpty() || star < 0 || star > 5) {
      throw new ServiceError("Invalid input.");
    } else {
      Rating rating = new Rating();
      rating.setMessage(msg);
      rating.setStars(star);
      return ratingRepository.save(rating);
    }
  }

  public Rating findRatingById(String id) throws ServiceError {
    Rating rate = ratingRepository.getOne(id);
    if (rate == null) {
      throw new ServiceError("Rating does not exist.");
    }
    return rate;
  }

  public void deleteRatingById(String id) {
    ratingRepository.deleteById(id);
  }

  public Rating updateRatingById(String id, Rating updatedRating) throws ServiceError {
    if (ratingRepository.findById(id).isPresent()) {
      return ratingRepository.save(updatedRating);
    } else {
      throw new ServiceError("Rating does not exist.");
    }
  }
}
