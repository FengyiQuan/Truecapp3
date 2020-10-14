package com.example.truecapp3.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "messages")
@EntityListeners(AuditingEntityListener.class)
public class Message {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  @OneToOne
  private User sender;
  @Column(nullable = false)
  private String content;
  @OneToOne
  private User receiver;
  @CreatedDate
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date sendingTime;

  @Column(columnDefinition = "boolean default true")
  private boolean isUnread = true;

  public Message() {
  }

  public Message(User sender, String content, User receiver) {
    this.sender = sender;
    this.content = content;
    this.receiver = receiver;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(User receiver) {
    this.receiver = receiver;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getSendingTime() {
    return sendingTime;
  }

  public void setSendingTime(Date sendingTime) {
    this.sendingTime = sendingTime;
  }

  public boolean isUnread() {
    return isUnread;
  }

  public void setUnread(boolean unread) {
    isUnread = unread;
  }
}
