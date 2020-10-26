package com.example.truecapp3.controllers;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Photo;
import com.example.truecapp3.models.Transaction;

import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.User;
import com.example.truecapp3.services.CreditService;
import com.example.truecapp3.services.ObjectService;
import com.example.truecapp3.services.PhotoService;
import com.example.truecapp3.services.TransactionService;
import com.example.truecapp3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  ObjectService objectService;

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
//
//  @GetMapping("fotoProducto/{id}")
//  public ResponseEntity<byte[]> fotoProducto(@PathVariable String id) throws ServiceError{
//
//    Object producto = objectService.getObjectById(id);
//
//    Photo foto = producto.getPhoto().get(0);
//    final HttpHeaders headers = new HttpHeaders();
//
//    headers.setContentType(MediaType.IMAGE_JPEG);
//
//    return new ResponseEntity<>(foto.getContent(),headers, HttpStatus.OK);
//  }
}
