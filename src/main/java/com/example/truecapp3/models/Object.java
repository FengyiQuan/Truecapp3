package com.example.truecapp3.models;

import com.example.truecapp3.enums.ObjectCondition;
import com.example.truecapp3.enums.ObjectType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "objects")
@EntityListeners(AuditingEntityListener.class)
public class Object {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  @OneToMany
  private List<Photo> photos;
  @Enumerated(EnumType.STRING)
  private ObjectType objectType;
  private String title;
  private String description;
  @Enumerated(EnumType.STRING)
  private ObjectCondition objectCondition;
  @ManyToOne
  private User owner;

  @ManyToOne
  private Category category;
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;
  @ManyToOne
  private Area productArea;

  public Object() {
    this.photos = new ArrayList<>();
  }


  public Object(List<Photo> photos, ObjectType objectType, String title, String description,
                ObjectCondition objectCondition, User owner, Category category, Date dateCreated,
                Area productArea) {
    this.photos = photos;
    this.objectType = objectType;
    this.title = title;
    this.description = description;
    this.objectCondition = objectCondition;
    this.owner = owner;
    this.category = category;
    this.dateCreated = dateCreated;
    this.productArea = productArea;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Photo> getPhoto() {
    return photos;
  }

  public void setPhoto(List<Photo> photo) {
    this.photos = photo;
  }

  public ObjectType getObjectType() {
    return objectType;
  }

  public void setObjectType(ObjectType objectType) {
    this.objectType = objectType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Area getProductArea() {
    return productArea;
  }

  public void setProductArea(Area productArea) {
    this.productArea = productArea;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public ObjectCondition getObjectCondition() {
    return objectCondition;
  }

  public void setObjectCondition(ObjectCondition objectCondition) {
    this.objectCondition = objectCondition;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}
