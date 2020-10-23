package com.example.truecapp3.repositories;

import com.example.truecapp3.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  @Query("SELECT c FROM User c WHERE c.email = :email")
  User getUserByEmail(@Param("email") String email);


  @Query("SELECT c FROM User c WHERE c.id = :id AND c.deleteUser = NULL")
  User getActiveUserById(@Param("id") String id);

  @Transactional
  @Modifying
  @Query("UPDATE User u SET u.emailVerified = TRUE WHERE u.id = :id")
  void verifySuccessById(@Param("id") String id);

  @Transactional
  @Modifying
  @Query("UPDATE User u SET u.isFirstTime = FALSE WHERE u.id = :id")
  void markFirstTime(@Param("id") String id);
}
