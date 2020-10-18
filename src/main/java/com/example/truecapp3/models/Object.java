package com.example.truecapp3.models;

import com.example.truecapp3.enums.ObjectType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "objects")
public class Object {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  @OneToMany
  private List<Photo> photos;
  private ObjectType objectType;
  private String title;
  private String description;

  @ManyToOne
  private Category category;
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;
  @ManyToOne
  private Area productArea;

  public Object() {
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

  public Object(List<Photo> photos, ObjectType objectType, String title, String description,
                Category category, Date dateCreated, Area productArea) {
    this.photos = photos;
    this.objectType = objectType;
    this.title = title;
    this.description = description;
    this.category = category;
    this.dateCreated = dateCreated;
    this.productArea = productArea;
  }

}
