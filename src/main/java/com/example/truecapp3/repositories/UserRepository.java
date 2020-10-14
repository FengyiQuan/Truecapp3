package com.example.truecapp3.repositories;

import com.example.truecapp3.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  @Query("SELECT c FROM User c WHERE c.id = :id")
  User getUserById(@Param("id") String id);


  @Query("SELECT c FROM User c WHERE c.email = :email")
  User getUserByEmail(@Param("email") String email);
}
