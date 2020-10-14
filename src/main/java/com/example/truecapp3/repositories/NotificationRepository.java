package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
  @Query("SELECT a FROM Notification a WHERE a.id = :id")
  List<Notification> getNotificationById(@Param("id") String id);
}
