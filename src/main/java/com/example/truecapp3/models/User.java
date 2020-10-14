package com.example.truecapp3.models;


import com.example.truecapp3.enums.UserType;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User implements Serializable {
  private String name;
  private String lastName;
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  private String id;
  private String address;
  private String cellphone;
  @Temporal(TemporalType.DATE)
  private Date birthday;
  private String email;
  private String password;
  @OneToMany
  private List<Object> products;
  @OneToMany
  private List<Object> services;
  @OneToOne
  private Photo profilePic;
  @OneToOne
  private Photo idPic;

  @OneToMany
  private List<Credit> credits;

  @OneToMany
  private List<Rating> ratings;
  private int successfulTradesCount;
  @OneToMany
  private List<Donation> donations;

  private UserType userType;
  @Temporal(TemporalType.TIMESTAMP)
  private Date newUser;
  @Temporal(TemporalType.TIMESTAMP)
  private Date deleteUser;
  @OneToMany
  private List<Notification> notifications;

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public List<Object> getProducts() {
    return products;
  }

  public void setProducts(List<Object> products) {
    this.products = products;
  }

  public List<Object> getServices() {
    return services;
  }

  public void setServices(List<Object> services) {
    this.services = services;
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

  public List<Rating> getRatings() {
    return ratings;
  }

  public void setRatings(List<Rating> ratings) {
    this.ratings = ratings;
  }

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

  public List<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }

  public User(String name, String lastName, String address, String cellphone, Date birthday,
              String email, String password, List<Object> products, List<Object> services,
              Photo profilePic, Photo idPic, List<Credit> credits, List<Rating> ratings,
              int successfulTradesCount, List<Donation> donations, UserType userType, Date newUser,
              Date deleteUser, List<Notification> notifications) {
    this.name = name;
    this.lastName = lastName;
    this.address = address;
    this.cellphone = cellphone;
    this.birthday = birthday;
    this.email = email;
    this.password = password;
    this.products = products;
    this.services = services;
    this.profilePic = profilePic;
    this.idPic = idPic;
    this.credits = credits;
    this.ratings = ratings;
    this.successfulTradesCount = successfulTradesCount;
    this.donations = donations;
    this.userType = userType;
    this.newUser = newUser;
    this.deleteUser = deleteUser;
    this.notifications = notifications;
  }

  public User() {
  }
}
