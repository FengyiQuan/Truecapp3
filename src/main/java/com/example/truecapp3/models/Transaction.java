package com.example.truecapp3.models;

import com.example.truecapp3.enums.TransactionState;
import com.example.truecapp3.enums.TransactionType;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transactions")
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
  @Temporal(TemporalType.TIMESTAMP)
  private Date transactionDate;
  @OneToOne
  private Object object;
  @Temporal(TemporalType.TIMESTAMP)
  private Date deliveryDate;
  private String message;
  @OneToOne
  private Rating user1;
  @OneToOne
  private Rating user2;
  private TransactionState state;
  @Temporal(TemporalType.TIMESTAMP)
  private Date offerDate;
  @Temporal(TemporalType.TIMESTAMP)
  private Date cancelDate;
  private TransactionType typeOfTransaction;

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

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
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

  public Rating getUser1() {
    return user1;
  }

  public void setUser1(Rating user1) {
    this.user1 = user1;
  }

  public Rating getUser2() {
    return user2;
  }

  public void setUser2(Rating user2) {
    this.user2 = user2;
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

  public Transaction(User seller, User receiver, Area area, Date transactionDate, Object object,
                     Date deliveryDate, String message, Rating user1, Rating user2,
                     TransactionState state, Date offerDate, Date cancelDate,
                     TransactionType typeOfTransaction) {
    this.seller = seller;
    this.receiver = receiver;
    this.area = area;
    this.transactionDate = transactionDate;
    this.object = object;
    this.deliveryDate = deliveryDate;
    this.message = message;
    this.user1 = user1;
    this.user2 = user2;
    this.state = state;
    this.offerDate = offerDate;
    this.cancelDate = cancelDate;
    this.typeOfTransaction = typeOfTransaction;
  }

  public Transaction() {
  }
}
