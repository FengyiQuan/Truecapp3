package com.example.truecapp3.models;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "categories")
public class Category {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String name;

  @OneToMany(mappedBy = "category")
  private List<Object> objects;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public List<Object> getObjects() {
    return objects;
  }

  public void setObjects(List<Object> objects) {
    this.objects = objects;
  }

  public Category(String name) {
    this.name = name;
    this.objects = new ArrayList<>();
  }


  public Category() {
    this.objects = new ArrayList<>();
  }


  public Category(String name, List<Object> objects) {
    this.name = name;
    this.objects = objects;
  }
}
