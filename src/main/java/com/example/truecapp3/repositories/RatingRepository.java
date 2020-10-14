package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
  @Query("SELECT a FROM Rating a WHERE a.id = :id")
  Rating getRatingById(@Param("id") String id);
}
