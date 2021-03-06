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

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
  @Autowired
  NotificationService notificationService;

  public Transaction getExistTransactionById(String id) throws ServiceError {
    Optional<Transaction> response = transactionRepository.findById(id);
    if (response.isPresent()) {
      return response.get();
    } else {
      throw new ServiceError("Transaction does not exist. ");
    }
  }

  public Transaction bindTransaction(String transactionId, String userId, Rating rating) throws ServiceError {
    Optional<Transaction> response = transactionRepository.findById(transactionId);
    if (response.isPresent()) {
      Transaction transaction = response.get();
      if (transaction.getSeller().getId().equals(userId)) {
        transaction.setSellerRating(rating);
      } else if (transaction.getReceiver().getId().equals(userId)) {
        transaction.setSellerRating(rating);
      }
      return transactionRepository.save(transaction);
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

  public List<Transaction> getTransactionsByUserId(String userId) {
    return transactionRepository.getTransactionsByUserId(userId);
  }

  @Transactional
  public Transaction startTransaction(String objeto1id, String objeto2id) throws ServiceError, MessagingException {
    //vaidacion de datos

    try {
      if (transactionRepository.getTransactionByObjectId(objeto1id) != null
          || transactionRepository.getTransactionByObjectId(objeto2id) != null) {
        throw new ServiceError("Object is already in some transaction.");
      }
      Object productoEmisor = objectService.getObjectById(objeto2id);
      Object productoReceptor = objectService.getObjectById(objeto1id);
      User usuario1 = userService.getActiveUserById(productoEmisor.getOwner().getId());
      User usuario2 = userService.getActiveUserById(productoReceptor.getOwner().getId());
//        if(usuario1.getListaProductos().contains(productoEmisor)){
//            if(usuario2.getListaProductos().contains(productoReceptor)){
//
//            }else{
//                throw new ErrorServicio("No se puede cancelar esta transacción");
//            }
//        }else{
//        throw new ErrorServicio("No se puede cancelar esta transacción");
//         }


      //...............................
      Transaction transaccion = new Transaction();
      transaccion.setSeller(usuario1);
      transaccion.setReceiver(usuario2);
      transaccion.setSellerObject(productoEmisor);
      transaccion.setReceiverObject(productoReceptor);

      transaccion.setTypeOfTransaction(TransactionType.BARTER);
      transaccion.setState(TransactionState.STARTED);
      transaccion.setArea(usuario1.getArea());

      transactionRepository.save(transaccion);
//      userService.bindTransaction(usuario1, transaccion);
//      userService.bindTransaction(usuario2, transaccion);

      //notificacionServicio.enviar(usuario1.getNombre()+" quiere intercambiar su "+productoEmisor.getTitulo()+" por tu "+productoReceptor.getTitulo(), "Alguien se encuentra interesado en hacer un Trueque", usuario2.getMail());
      //notificacionServicio.enviar(usuario1.getNombre()+" quieres intercambiar "+productoEmisor.getTitulo()+" por un "+productoReceptor.getTitulo(), "Pedido de trueque mandado exitosamente", usuario1.getMail());
      notificationService.enviarIniciarOferta("¡Nuevo Truque!", transaccion);

      return transaccion;


    } catch (ServiceError e) {
      throw new ServiceError("Operación Interrumpida");
    }
  }


  @Transactional
  public Transaction createNewTransaction(String sellerId, String receiverId, String sellerObjectId,
                                          String receiverObjectId, String message,
                                          String areaName, String provinceOrState) throws ServiceError {
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
    transaction.setState(TransactionState.STARTED);
    transaction.setTypeOfTransaction(TransactionType.BARTER);
    transaction.setArea(area);

    return transactionRepository.save(transaction);

  }

  @Transactional
  public Transaction completeTransaction(String transactionId) throws ServiceError {
    Transaction transaction = getExistTransactionById(transactionId);
    if (transaction.isComplete()) {
      userService.incrementSuccessfulTradesCount(transaction.getSeller().getId());
      userService.incrementSuccessfulTradesCount(transaction.getReceiver().getId());
      addDeliveryDate(transactionId, new Date());
      transaction.setState(TransactionState.FINISHED);
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
      transaction.setState(TransactionState.IN_PROCESS);
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
      transaction.setState(TransactionState.IN_PROCESS);
      return transactionRepository.save(transaction);
    }

  }

  @Transactional
  public Transaction addCancelDate(String transactionId, Date cancelDate) throws ServiceError {
    if (cancelDate == null) {
      throw new ServiceError("date cannot be null.");
    } else {
      Transaction transaction = getExistTransactionById(transactionId);
      transaction.setCancelDate(cancelDate);
      transaction.setState(TransactionState.CANCELED);
      return transactionRepository.save(transaction);
    }
  }

  @Transactional
  public Transaction addUser1Rating(String transactionId, int star, String msg) throws ServiceError {
    Rating rating = ratingService.createRating(star, msg);
    Transaction transaction = getExistTransactionById(transactionId);
    transaction.setSellerRating(rating);
    return transactionRepository.save(transaction);
  }

  @Transactional
  public Transaction addUser2Rating(String transactionId, int star, String msg) throws ServiceError {
    Rating rating = ratingService.createRating(star, msg);
    Transaction transaction = getExistTransactionById(transactionId);
    transaction.setReceiverRating(rating);
    return transactionRepository.save(transaction);
  }

}
