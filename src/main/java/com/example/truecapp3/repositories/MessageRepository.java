package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Message;
import com.example.truecapp3.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, String> {

  @Query("SELECT msg FROM Message msg WHERE (msg.sender = :sender AND msg.receiver = :receiver) "
         + "OR (msg.sender = :receiver AND msg.receiver = :sender) ORDER BY sending_time")
  List<Message> findHistoryMessageByUsersOrderByTime(@Param("sender") User sender, @Param("receiver") User receiver);

  @Query(value = "SELECT users.email FROM users WHERE id IN "
                 + "(SELECT messages.receiver_id FROM messages WHERE messages.sender_id = :id UNION "
                 + "SELECT messages.sender_id FROM messages WHERE messages.receiver_id = :id)",
          nativeQuery = true)
  List<String> findAllContactsEmail(@Param("id") String id);

  @Query("SELECT COUNT(msg) FROM Message msg WHERE msg.sender = :sender AND msg.receiver = :receiver AND msg.isUnread = TRUE")
  Integer getAllUnreadMessageCountByUser(@Param("sender") User sender, @Param("receiver") User receiver);

  @Transactional
  @Modifying
  @Query("UPDATE Message msg SET msg.isUnread = FALSE WHERE msg.sender = :sender AND msg.receiver = :receiver")
  void markAllRead(@Param("sender") User sender, @Param("receiver") User receiver);
}