package com.example.truecapp3.services;
import com.example.truecapp3.repositories.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
  @Autowired
  RatingRepository ratingRepository;
}
