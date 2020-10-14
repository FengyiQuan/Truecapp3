package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Photo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
  @Query("SELECT a FROM Photo a WHERE a.id = :id")
  Photo getPhotoById(@Param("id") String id);



}
