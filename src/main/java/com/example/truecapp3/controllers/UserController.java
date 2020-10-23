package com.example.truecapp3.controllers;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Transaction;
import com.example.truecapp3.models.User;
import com.example.truecapp3.services.CreditService;
import com.example.truecapp3.services.PhotoService;
import com.example.truecapp3.services.TransactionService;
import com.example.truecapp3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/")
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  CreditService creditService;
  @Autowired
  TransactionService transactionService;

  @Autowired
  PhotoService photoService;

  @PostMapping("/test")
  @ResponseBody
  public void createNewUser() throws ServiceError, MessagingException {
    try {
      User u1 = userService.createNewUser("a", "b", "840073588z@gmail.com", "123456");
      User u2 = userService.createNewUser("1", "2", "123456@gmail.com", "123456");
//      Transaction t = transactionService.createNewTransaction(u1,u2);
    } catch (Exception e) {
      throw e;
    }

  }
}
