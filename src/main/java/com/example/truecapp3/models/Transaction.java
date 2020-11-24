package com.example.truecapp3.models;

import com.example.truecapp3.enums.TransactionState;
import com.example.truecapp3.enums.TransactionType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  @ManyToOne
  private User seller;
  @ManyToOne
  private User receiver;
  @ManyToOne
  private Area area;
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date transactionDate;
  @Temporal(TemporalType.TIMESTAMP)
  private Date deliveryDate;
  @Temporal(TemporalType.TIMESTAMP)
  private Date offerDate;
  @Temporal(TemporalType.TIMESTAMP)
  private Date cancelDate;

  @OneToOne
  private Object sellerObject;
  @OneToOne
  private Object receiverObject;
  private String message;
  @OneToOne
  private Rating sellerRating;
  @OneToOne
  private Rating receiverRating;
  @Enumerated(EnumType.STRING)
  private TransactionState state;
  @Enumerated(EnumType.STRING)
  private TransactionType typeOfTransaction;


  public boolean isComplete() {
    return seller != null && receiver != null && area != null && transactionDate != null &&
           deliveryDate != null && offerDate != null && sellerObject != null
           && receiverObject != null && message != null && state != null
           && typeOfTransaction != null;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public User getSeller() {
    return seller;
  }

  public void setSeller(User seller) {
    this.seller = seller;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(User receiver) {
    this.receiver = receiver;
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }


  public Date getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Rating getSellerRating() {
    return sellerRating;
  }

  public void setSellerRating(Rating sellerRating) {
    this.sellerRating = sellerRating;
  }

  public Rating getReceiverRating() {
    return receiverRating;
  }

  public void setReceiverRating(Rating receiverRating) {
    this.receiverRating = receiverRating;
  }

  public TransactionState getState() {
    return state;
  }

  public void setState(TransactionState state) {
    this.state = state;
  }

  public Date getOfferDate() {
    return offerDate;
  }

  public void setOfferDate(Date offerDate) {
    this.offerDate = offerDate;
  }

  public Date getCancelDate() {
    return cancelDate;
  }

  public void setCancelDate(Date cancelDate) {
    this.cancelDate = cancelDate;
  }

  public TransactionType getTypeOfTransaction() {
    return typeOfTransaction;
  }

  public void setTypeOfTransaction(TransactionType typeOfTransaction) {
    this.typeOfTransaction = typeOfTransaction;
  }

  public Object getSellerObject() {
    return sellerObject;
  }

  public void setSellerObject(Object sellerObject) {
    this.sellerObject = sellerObject;
  }

  public Object getReceiverObject() {
    return receiverObject;
  }

  public void setReceiverObject(Object receiverObject) {
    this.receiverObject = receiverObject;
  }

  public Transaction(User seller, User receiver, Area area, Date transactionDate, Object sellerObject,
                     Object receiverObject, Date deliveryDate, String message, Rating sellerRating, Rating receiverRating,
                     TransactionState state, Date offerDate, Date cancelDate, TransactionType typeOfTransaction) {
    this.seller = seller;
    this.receiver = receiver;
    this.area = area;
    this.transactionDate = transactionDate;
    this.sellerObject = sellerObject;
    this.receiverObject = receiverObject;
    this.deliveryDate = deliveryDate;
    this.message = message;
    this.sellerRating = sellerRating;
    this.receiverRating = receiverRating;
    this.state = state;
    this.offerDate = offerDate;
    this.cancelDate = cancelDate;
    this.typeOfTransaction = typeOfTransaction;
  }

  public Transaction() {
  }

}
