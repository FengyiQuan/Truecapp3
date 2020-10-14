package com.example.truecapp3.controllers;

import com.example.truecapp3.services.CreditService;
import com.example.truecapp3.services.PhotoService;
//import com.example.truecapp3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
//  @Autowired
//  UserService userService;
  @Autowired
  CreditService creditService;

  @Autowired
  PhotoService photoService;
}
