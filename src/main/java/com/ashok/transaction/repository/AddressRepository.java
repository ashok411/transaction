package com.ashok.transaction.repository;

import com.ashok.transaction.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository("address_repository")
public class AddressRepository extends GenericRepository<Address, String> implements
    IAddressRepository {

  public AddressRepository() {
    super(Address.class);
  }


  }