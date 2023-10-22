package com.ashok.transaction.service;

import com.ashok.transaction.Manager.AddressManager;
import com.ashok.transaction.Manager.PersonManager;
import com.ashok.transaction.entity.Address;
import com.ashok.transaction.entity.Person;
import com.ashok.transaction.exception.MyCustomException;
import com.ashok.transaction.model.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  private final AddressManager addressManager;

  private final PersonManager personManager;

  private final PersonService personService;

  @Autowired
  public AddressService(AddressManager addressManager, PersonManager personManager, PersonService personService) {
    this.addressManager = addressManager;
    this.personManager = personManager;
    this.personService = personService;
  }



  public Address save(AddressDto address) throws MyCustomException {
    Person person = new Person();
    person.setAge(30);
    person.setFirstName(address.getPersonDetails().getFirstName());
    person.setLastName(address.getPersonDetails().getLastName());
    Person updatedPerson = personService.save(person);
    Address toBeUpdatedAddress = new Address();


    if (!updatedPerson.getFirstName().equals("test")) {
      throw new RuntimeException();
    }

    toBeUpdatedAddress.setCity(address.getCity());
    toBeUpdatedAddress.setStreet(address.getStreet());
    toBeUpdatedAddress.setPersonIdRef(updatedPerson.getId());
    toBeUpdatedAddress.setPostalCode(address.getPostalCode());


    Address address1 = addressManager.save(toBeUpdatedAddress);
    return address1;
//    if(address1.equals("ss")){
//        return address1;
//        }
//    throw new MyCustomRuntimeException("s");
  }

  // Implement your business logic for Address here if needed
}
