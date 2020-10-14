package com.example.truecapp3.services;

import com.example.truecapp3.enums.CreditType;
import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Credit;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.CreditRepository;
import com.example.truecapp3.repositories.NotificationRepository;
import com.example.truecapp3.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CreditService {


  @Autowired
  private CreditRepository creditRepository;


}
