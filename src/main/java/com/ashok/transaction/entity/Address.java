package com.ashok.transaction.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "address")
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonNaming(SnakeCaseStrategy.class)
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "person_id_ref")
  private Long personIdRef;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "postal_code")
  private String postalCode;

  // getters, setters, and constructors
}
