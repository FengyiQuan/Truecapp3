package com.example.truecapp3.models;


import com.example.truecapp3.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
  private String name;
  private String lastName;
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  private String id;
  private String street;
  private String aptNumber;
  @ManyToOne
  private Area area;
  private String cellphone;
  @Temporal(TemporalType.DATE)
  private Date birthday;
  private String email;
  @JsonIgnore
  private String password;
  @OneToMany(mappedBy = "owner")
  @JsonIgnore
  private List<Object> objects;
  @OneToOne
  @JsonIgnore
  private Photo profilePic;
  @OneToOne
  @JsonIgnore
  private Photo idPic;
  @OneToMany
  @JsonIgnore
  private List<Credit> credits;
  private int currentCreditsCount;
  private int successfulTradesCount;
  @OneToMany
  @JsonIgnore
  private List<Donation> donations;
  @Enumerated(EnumType.STRING)
  private UserType userType;
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date newUser;
  @Temporal(TemporalType.TIMESTAMP)
  private Date deleteUser;
  @OneToMany
  @JsonIgnore
  private List<Notification> notifications;
  private boolean emailVerified;
  private boolean isFirstTime;

  // true if this user is ok to make deal
  public boolean checkStatus() {
    return emailVerified && idPic != null && deleteUser == null;
  }

  public User(String name, String lastName, String street, String aptNumber, Area area, String cellphone, Date birthday, String email, String password, List<Object> objects, Photo profilePic, Photo idPic, List<Credit> credits, int currentCreditsCount, List<Rating> ratings, int successfulTradesCount, List<Donation> donations, UserType userType, Date newUser, Date deleteUser,  List<Notification> notifications, boolean emailVerified, boolean isFirstTime) {
    this.name = name;
    this.lastName = lastName;
    this.street = street;
    this.aptNumber = aptNumber;
    this.area = area;
    this.cellphone = cellphone;
    this.birthday = birthday;
    this.email = email;
    this.password = password;
    this.objects = objects;
    this.profilePic = profilePic;
    this.idPic = idPic;
    this.credits = credits;
    this.currentCreditsCount = currentCreditsCount;
    this.successfulTradesCount = successfulTradesCount;
    this.donations = donations;
    this.userType = userType;
    this.newUser = newUser;
    this.deleteUser = deleteUser;
    this.notifications = notifications;
    this.emailVerified = emailVerified;
    this.isFirstTime = isFirstTime;
  }

  public User() {
    this.isFirstTime = true;
    this.emailVerified = false;
    this.objects = new ArrayList<>();
    this.credits = new ArrayList<>();
    this.currentCreditsCount = 0;
    this.successfulTradesCount = 0;
    this.donations = new ArrayList<>();
    this.notifications = new ArrayList<>();
    this.userType = UserType.CLIENT;
  }


  public int getCurrentCreditsCount() {
    return currentCreditsCount;
  }

  public void setCurrentCreditsCount(int currentCreditsCount) {
    this.currentCreditsCount = currentCreditsCount;
  }

  public boolean isEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  public boolean isFirstTime() {
    return isFirstTime;
  }

  public void setFirstTime(boolean firstTime) {
    isFirstTime = firstTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getCellphone() {
    return cellphone;
  }

  public void setCellphone(String cellphone) {
    this.cellphone = cellphone;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Object> getObjects() {
    return objects;
  }

  public void setObjects(List<Object> objects) {
    this.objects = objects;
  }

  public Photo getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(Photo profilePic) {
    this.profilePic = profilePic;
  }

  public Photo getIdPic() {
    return idPic;
  }

  public void setIdPic(Photo idPic) {
    this.idPic = idPic;
  }

  public List<Credit> getCredits() {
    return credits;
  }

  public void setCredits(List<Credit> credits) {
    this.credits = credits;
  }

//  public List<Rating> getRatings() {
//    return ratings;
//  }
//
//  public void setRatings(List<Rating> ratings) {
//    this.ratings = ratings;
//  }

  public int getSuccessfulTradesCount() {
    return successfulTradesCount;
  }

  public void setSuccessfulTradesCount(int successfulTradesCount) {
    this.successfulTradesCount = successfulTradesCount;
  }

  public List<Donation> getDonations() {
    return donations;
  }

  public void setDonations(List<Donation> donations) {
    this.donations = donations;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public Date getNewUser() {
    return newUser;
  }

  public void setNewUser(Date newUser) {
    this.newUser = newUser;
  }

  public Date getDeleteUser() {
    return deleteUser;
  }

  public void setDeleteUser(Date deleteUser) {
    this.deleteUser = deleteUser;
  }



  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }


  public List<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getAptNumber() {
    return aptNumber;
  }

  public void setAptNumber(String aptNumber) {
    this.aptNumber = aptNumber;
  }
}
