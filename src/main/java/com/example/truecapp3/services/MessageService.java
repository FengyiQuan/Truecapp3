package com.example.truecapp3.services;


import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Message;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageService {
  @Autowired
  MessageRepository repository;
  @Autowired
  UserService userService;


  public Message createMessage(Message newMessage) {
    return repository.save(newMessage);
  }

  public Message createMessage(User sender, User receiver, String content) {
    Message newMessage = new Message(sender, content, receiver);
    return repository.save(newMessage);
  }

  public List<Message> findHistoryMessage(String senderEmail, String receiverEmail) throws ServiceError {
    User sender;
    User receiver;
    try {
      sender = userService.getActiveUserByEmail(senderEmail);
      receiver = userService.getActiveUserByEmail(receiverEmail);
    } catch (ServiceError error) {
      throw new ServiceError("email does not found.");
    }

    return repository.findHistoryMessageByUsersOrderByTime(sender, receiver);
  }


  public List<String> findAllContactsEmail(String id) {
    return repository.findAllContactsEmail(id);
  }

  public int getAllUnreadMessageByUser(String senderEmail, String receiverEmail) throws ServiceError {
    User sender;
    User receiver;
    try {
      sender = userService.getActiveUserByEmail(senderEmail);
      receiver = userService.getActiveUserByEmail(receiverEmail);
    } catch (ServiceError error) {
      throw new ServiceError("email does not found.");
    }
    return repository.getAllUnreadMessageCountByUser(sender, receiver);
  }

  public void markAllRead(String senderEmail, String receiverEmail) throws ServiceError {
    User sender;
    User receiver;
    try {
      sender = userService.getActiveUserByEmail(senderEmail);
      receiver = userService.getActiveUserByEmail(receiverEmail);
    } catch (ServiceError error) {
      throw new ServiceError("email does not found.");
    }
    repository.markAllRead(sender, receiver);
  }

  public void deleteMsgById(String id) {
    repository.deleteById(id);
  }

}