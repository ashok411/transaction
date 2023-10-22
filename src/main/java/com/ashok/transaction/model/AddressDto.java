package com.ashok.transaction.model;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

  private String street;


  private String city;

  private String postalCode;

  private PersonDto personDetails;

}
