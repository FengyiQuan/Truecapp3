package com.example.truecapp3.repositories;


import com.example.truecapp3.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
  @Query("SELECT a FROM Category a WHERE a.id = :id")
  List<Category> getCategoryByID(@Param("id") String id);



}
