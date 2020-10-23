package com.example.truecapp3.services;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Transaction;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  @Autowired
  TransactionRepository transactionRepository;
  @Autowired
  UserService userService;

  public Transaction createNewTransaction(String sellerId, String receiverId) throws ServiceError {
    userService.checkStatusOfUser(sellerId);
    userService.checkStatusOfUser(receiverId);
    User seller = userService.getActiveUserById(sellerId);
    User receiver = userService.getActiveUserById(receiverId);

    Transaction transaction = new Transaction();

  }
}
