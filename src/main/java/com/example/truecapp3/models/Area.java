package com.example.truecapp3.models;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "areas")
public class Area {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String areaName;
  private String provinceOrState;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public String getProvinceOrState() {
    return provinceOrState;
  }

  public void setProvinceOrState(String provinceOrState) {
    this.provinceOrState = provinceOrState;
  }


  public Area() {
  }

  public Area(String areaName, String provinceOrState) {
    this.areaName = areaName;
    this.provinceOrState = provinceOrState;
  }

  public Area(String areaName, String provinceOrState, List<Object> objects) {
    this.areaName = areaName;
    this.provinceOrState = provinceOrState;
  }


}
