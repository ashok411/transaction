package com.ashok.transaction.Manager;

import com.ashok.transaction.entity.Address;
import com.ashok.transaction.exception.MyCustomException;
import com.ashok.transaction.repository.IAddressRepository;
import javax.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("addressManager")
@Transactional
@NoArgsConstructor
public class AddressManager {

  @Autowired
  private IAddressRepository addressRepository;


  public Address save(Address address) throws MyCustomException {
    return addressRepository.save(address);
  }
}
