package com.example.truecapp3.models;


import com.example.truecapp3.enums.CreditType;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="credits")
public class Credit {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Enumerated(EnumType.ORDINAL)
  private CreditType creditType;
  private String description;
  @Temporal(TemporalType.TIMESTAMP)
  private Date admissionDate;
  @Temporal(TemporalType.TIMESTAMP)
  private Date expenditureDate;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CreditType getCreditType() {
    return creditType;
  }

  public void setCreditType(CreditType creditType) {
    this.creditType = creditType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getAdmissionDate() {
    return admissionDate;
  }

  public void setAdmissionDate(Date admissionDate) {
    this.admissionDate = admissionDate;
  }

  public Date getExpenditureDate() {
    return expenditureDate;
  }

  public void setExpenditureDate(Date expenditureDate) {
    this.expenditureDate = expenditureDate;
  }

  public Credit() {
  }

  public Credit(CreditType creditType, String description, Date admissionDate, Date expenditureDate) {
    this.creditType = creditType;
    this.description = description;
    this.admissionDate = admissionDate;
    this.expenditureDate = expenditureDate;
  }
}
