package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

  @Query("SELECT msg FROM Message msg WHERE (msg.sender = :sender AND msg.receiver = :receiver) "
         + "OR (msg.sender = :receiver AND msg.receiver = :sender) ORDER BY sending_time")
  List<Message> findHistoryMessageByNamesOrderByTime(@Param("sender") String sender, @Param("receiver") String receiver);

  @Query(value = "SELECT messages.receiver FROM messages WHERE messages.sender = :user "
                 + "UNION SELECT messages.sender FROM messages WHERE messages.receiver = :user",
          nativeQuery = true)
  List<String> findAllContactUsers(@Param("user") String user);

  @Query("SELECT COUNT(msg) FROM Message msg WHERE msg.sender = :sender AND msg.receiver = :receiver AND msg.isUnread = TRUE")
  Integer getAllUnreadMessageByUser(@Param("sender") String sender, @Param("receiver") String receiver);

  @Transactional
  @Modifying
  @Query("UPDATE Message msg SET msg.isUnread = FALSE WHERE msg.sender = :sender AND msg.receiver = :receiver")
  void markAllRead(@Param("sender") String sender, @Param("receiver") String receiver);
}