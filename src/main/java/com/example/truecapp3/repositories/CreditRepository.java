package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, String> {
  @Query("SELECT a FROM Credit a WHERE a.id = :id")
  List<Credit> getCreditById(@Param("id")String id);
}
