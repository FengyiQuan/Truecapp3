package com.example.truecapp3.services;


import com.example.truecapp3.models.Message;
import com.example.truecapp3.repositories.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageService {
  @Autowired
  MessageRepository repository;

  @Autowired
  private NotificationService notificationService;

  public Message createMessage(Message newMessage) {
    notificationService.sendTextMail("You received a new message from"
                                     + newMessage.getSender().getName() + ":"
                                     + newMessage.getContent(), "TruecApp", newMessage.getReceiver().getEmail());
    return repository.save(newMessage);

  }

  public List<Message> findHistoryMessage(String sender, String receiver) {
    return repository.findHistoryMessageByNamesOrderByTime(sender, receiver);
  }

  public List<String> findAllContactUsers(String user) {
    return repository.findAllContactUsers(user);
  }

  public int getAllUnreadMessageByUser(String sender, String receiver) {
    return repository.getAllUnreadMessageByUser(sender, receiver);
  }

  public void markAllRead(String sender, String receiver) {
    repository.markAllRead(sender, receiver);
  }

  public void deleteMsgById(String id) {
    repository.deleteById(id);
  }

}