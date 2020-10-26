package com.example.truecapp3.services;

import com.example.truecapp3.enums.TransactionState;
import com.example.truecapp3.enums.TransactionType;
import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Area;
import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.Rating;
import com.example.truecapp3.models.Transaction;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionService {
  @Autowired
  TransactionRepository transactionRepository;
  @Autowired
  UserService userService;
  @Autowired
  ObjectService objectService;
  @Autowired
  AreaService areaService;
  @Autowired
  RatingService ratingService;

  public Transaction getExistTransactionById(String id) throws ServiceError {
    Optional<Transaction> response = transactionRepository.findById(id);
    if (response.isPresent()) {
      return response.get();
    } else {
      throw new ServiceError("Transaction does not exist. ");
    }
  }

  public void deleteTransaction(String id) {
    transactionRepository.deleteById(id);
  }

  public boolean isComplete(String id) throws ServiceError {
    Transaction t = getExistTransactionById(id);
    return t.isComplete();
  }

  @Transactional
  public Transaction modifyTransaction(String id, Transaction t) throws ServiceError {
    if (transactionRepository.findById(id).isPresent()) {
      return transactionRepository.save(t);
    } else {
      throw new ServiceError("Transaction does not exist. ");
    }
  }

  @Transactional
  public Transaction createNewTransaction(String sellerId, String receiverId, String sellerObjectId,
                                          String receiverObjectId, String message, TransactionState state,
                                          TransactionType type, String areaName, String provinceOrState) throws ServiceError {
    if (message == null) {
      throw new ServiceError("message cannot be null.");
    }
    userService.checkStatusOfUser(sellerId);
    userService.checkStatusOfUser(receiverId);
    User seller = userService.getActiveUserById(sellerId);
    User receiver = userService.getActiveUserById(receiverId);
    Object sellerObject = objectService.getObjectById(sellerObjectId);
    Object receiverObject = objectService.getObjectById(receiverObjectId);
    Area area = areaService.createArea(areaName, provinceOrState);

    Transaction transaction = new Transaction();
    transaction.setSeller(seller);
    transaction.setReceiver(receiver);
    transaction.setMessage(message);
    transaction.setSellerObject(sellerObject);
    transaction.setReceiverObject(receiverObject);
    transaction.setState(state);
    transaction.setTypeOfTransaction(type);
    transaction.setArea(area);

    return transactionRepository.save(transaction);

  }

  @Transactional
  public Transaction completeTransaction(String transactionId) throws ServiceError {
    Transaction transaction = getExistTransactionById(transactionId);
    if (transaction.isComplete()) {
      transaction.setState(TransactionState.FINISHED);
      userService.incrementSuccessfulTradesCount(transaction.getSeller());
      userService.incrementSuccessfulTradesCount(transaction.getReceiver());
      return transactionRepository.save(transaction);
    } else {
      throw new ServiceError("Transaction is not completed.");
    }
  }

  @Transactional
  public Transaction addDeliveryDate(String transactionId, Date deliveryDate) throws ServiceError {
    if (deliveryDate == null) {
      throw new ServiceError("date cannot be null.");
    } else {
      Transaction transaction = getExistTransactionById(transactionId);
      transaction.setDeliveryDate(deliveryDate);
      return transactionRepository.save(transaction);
    }
  }

  @Transactional
  public Transaction addOfferDate(String transactionId, Date offerDate) throws ServiceError {
    if (offerDate == null) {
      throw new ServiceError("date cannot be null.");
    } else {
      Transaction transaction = getExistTransactionById(transactionId);
      transaction.setOfferDate(offerDate);
      return transactionRepository.save(transaction);
    }

  }

  @Transactional
  public Transaction addCancelDate(String transactionId, Date cancelDate) throws ServiceError {
    if (cancelDate == null) {
      throw new ServiceError("date cannot be null.");
    } else {
      Transaction transaction = getExistTransactionById(transactionId);
      transaction.setOfferDate(cancelDate);
      return transactionRepository.save(transaction);
    }
  }

  @Transactional
  public Transaction addUser1Rating(String transactionId, int star, String msg) throws ServiceError {
    Rating rating = ratingService.createRating(star, msg);
    Transaction transaction = getExistTransactionById(transactionId);
    transaction.setUser1(rating);
    return transactionRepository.save(transaction);
  }

  @Transactional
  public Transaction addUser2Rating(String transactionId, int star, String msg) throws ServiceError {
    Rating rating = ratingService.createRating(star, msg);
    Transaction transaction = getExistTransactionById(transactionId);
    transaction.setUser2(rating);
    return transactionRepository.save(transaction);
  }

}
