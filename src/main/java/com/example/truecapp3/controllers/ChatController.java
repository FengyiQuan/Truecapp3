//package com.example.truecapp3.controllers;
//
//
//import com.example.truecapp3.models.Message;
//import com.example.truecapp3.models.User;
//import com.example.truecapp3.services.MessageService;
//import com.example.truecapp3.services.UserService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.security.Principal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class ChatController {
//  @Autowired
//  SimpMessagingTemplate simpMessagingTemplate;
//
//  @Autowired
//  MessageService messageService;
//  @Autowired
//  UserService userService;
//
//  // group chat and public topic
//  @MessageMapping("/hello")
//  public void greeting(Message message) {
//    simpMessagingTemplate.convertAndSend("/topic/greetings", message);
//  }
//
//  @MessageMapping("/chat")
//  public void chat(Message message) {
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    String username = ((UserDetails) principal).getUsername();
//    User user = userService.getUserByName(username);
//    message.setSender(user);
//    simpMessagingTemplate.convertAndSendToUser(message.getReceiver().getId(), "/queue/chat", message);
//
//  }
//
//  @GetMapping("/")
//  public String showChatPage(ModelMap model, @RequestParam(value = "sendTo", required = false) String receiver, Principal principal) {
//    String currentUser = principal.getName();
//
//    model.addAttribute("sender", currentUser);
//
//
//    if (receiver != null) {
//      model.addAttribute("receiver", receiver);
//      model.addAttribute("history", messageService.findHistoryMessage(currentUser, receiver));
//    }
//
//
//    Map<String, Integer> contactsAndUnread = new HashMap<>();
//    List<String> contacts = messageService.findAllContactUsers(currentUser);
//    for (String contact : contacts) {
//      contactsAndUnread.put(contact, messageService.getAllUnreadMessageByUser(contact, currentUser));
//    }
//
//    model.addAttribute("contacts", contactsAndUnread);
//    messageService.markAllRead(receiver, currentUser);
//    return "chat";
//  }
//
//  @PostMapping("/api/messages")
//  @ResponseBody
//  public Message createMessage(@RequestBody Message newMessage) {
//    return messageService.createMessage(newMessage);
//  }
//
//  @PostMapping("/api/messages/markAllRead/{receiver}")
//  @ResponseBody
//  public void markUnread(Principal principal, @PathVariable String receiver) {
//    String currentUser = principal.getName();
//    System.out.println(receiver);
//    System.out.println(currentUser);
//
//    messageService.markAllRead(receiver, currentUser);
//  }
//
//}
