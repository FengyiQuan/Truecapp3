//package com.example.truecapp3.models;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="reputations")
//public class Reputation {
//  @Id
//  @GeneratedValue(generator = "uuid")
//  @GenericGenerator(name = "uuid", strategy = "uuid2")
//  private String id;
//  @OneToOne
//  private User user;
//  @OneToMany(mappedBy = "rating")
//  private List<Rating> ratings;
//
//  public String getId() {
//    return id;
//  }
//
//  public void setId(String id) {
//    this.id = id;
//  }
//
//  public User getUser() {
//    return user;
//  }
//
//  public void setUser(User user) {
//    this.user = user;
//  }
//
//  public List<Rating> getRatings() {
//    return ratings;
//  }
//
//  public void setRatings(List<Rating> ratings) {
//    this.ratings = ratings;
//  }
//
//  public Reputation(User user, List<Rating> ratings) {
//    this.user = user;
//    this.ratings = ratings;
//  }
//
//  public Reputation() {
//  }
//}
