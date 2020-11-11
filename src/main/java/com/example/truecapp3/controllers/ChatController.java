package com.example.truecapp3.controllers;


import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Message;
import com.example.truecapp3.models.User;
import com.example.truecapp3.services.MessageService;
import com.example.truecapp3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {
  @Autowired
  SimpMessagingTemplate simpMessagingTemplate;
  @Autowired
  MessageService messageService;
  @Autowired
  UserService userService;

  // group chat and public topic
  @MessageMapping("/hello")
  public void greeting(Message message) {
    simpMessagingTemplate.convertAndSend("/topic/greetings", message);
  }

  @MessageMapping("/chat")
  public void chat(Message message, ModelMap model, Principal principal) {
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    String email = ((UserDetails) principal).getUsername();
    String email = principal.getName();
    try {
      message.setSender(userService.getActiveUserByEmail(email));
    } catch (ServiceError error) {
      model.put("error", error.getMessage());
    }
    simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getEmail(), "/queue/chat", message);

  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping("/chat")
  public String showChatPage(ModelMap model, @RequestParam(value = "sendTo", required = false) String receiverEmail) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String currentUserEmail = ((UserDetails) principal).getUsername();

    User currentUser;
    User receiverUser = new User();


    try {
      currentUser = userService.getActiveUserByEmail(currentUserEmail);
    } catch (Exception error) {
      model.put("error", error.getMessage());
      return "error";
    }
    final boolean validReceiverEmail = receiverEmail != null && !receiverEmail.equals("")
                                       && !receiverEmail.equals(currentUserEmail);
    if (validReceiverEmail) {
      try {
        receiverUser = userService.getActiveUserByEmail(receiverEmail);
      } catch (Exception ignored) {
      }
    }

    String currentUserId = currentUser.getId();
    model.addAttribute("sender", currentUser);


    if (receiverUser != null) {
      model.addAttribute("receiver", receiverUser);
      try {
        model.addAttribute("history", messageService.findHistoryMessage(currentUserEmail, receiverEmail));
      } catch (ServiceError error) {
        model.put("error", error.getMessage());
      }
    }

    Map<String, Integer> contactsAndUnread = new HashMap<>();
    List<String> contacts = new ArrayList<>();
    if (validReceiverEmail) {
      contacts.add(receiverEmail);
    }

    contacts.addAll(messageService.findAllContactsEmail(currentUserId));
    for (String contact : contacts) {
      try {
        contactsAndUnread.put(contact, messageService.getAllUnreadMessageByUser(contact, currentUserEmail));
      } catch (ServiceError error) {
        model.put("error", error.getMessage());
      }
    }

    model.addAttribute("contacts", contactsAndUnread);
    try {
      messageService.markAllRead(receiverEmail, currentUserEmail);
    } catch (ServiceError error) {
      model.put("error", error.getMessage());
    }
    return "chat";
  }

  @PostMapping("/api/messages")
  @ResponseBody
  public Message createMessage(@RequestBody Message newMessage) {
    return messageService.createMessage(newMessage);
  }
//  @PostMapping("/api/messages/{sendTo}")
//  public Message createMessage(ModelMap model,@RequestParam String newMessage, @PathVariable String sendTo) {
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    String currentUserEmail = ((UserDetails) principal).getUsername();
//
//    User currentUser;
//    try {
//      currentUser = userService.getActiveUserByEmail(currentUserEmail);
//    } catch (Exception error) {
//      model.put("error", error.getMessage());
//      return "error";
//    }
//    return messageService.createMessage(newMessage);
//  }

  @PostMapping("/api/messages/markAllRead/{receiver}")
  @ResponseBody
  public void markUnread(@PathVariable String receiver, ModelMap model) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String email = ((UserDetails) principal).getUsername();

    try {
      messageService.markAllRead(receiver, email);
    } catch (ServiceError error) {
      model.put("error", error.getMessage());
    }
  }


}
